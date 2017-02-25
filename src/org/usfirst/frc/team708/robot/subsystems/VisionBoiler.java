package org.usfirst.frc.team708.robot.subsystems;

import org.opencv.core.Rect;
import edu.wpi.cscore.AxisCamera;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team708.robot.AutoConstants;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.visionProcessor.GripPipelineBoiler;
import org.usfirst.frc.team708.robot.util.Math708;

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 *@authors Viet & Sue
 *This subsystem is specific to the 2017 Game FIRST Steamworks Boiler Goal
 */
public class VisionBoiler extends Subsystem {
	
	// Camera Variables
	private double fovDegrees = AutoConstants.USB_BOILER_FOV_DEGREES;		// Field of View of the Camera
	private double bPipelineSize;											// Number of Contours in the Pipline- 0 = target not in view
	private int bImageWidth = AutoConstants.USB_BOILER_IMG_WIDTH;			// Width of image
	private int bImageHeight = AutoConstants.USB_BOILER_IMG_HEIGHT;			// Height of image
	
	// Image OpenCV Image Processing Variables
	private VisionThread visionThreadBoiler;				// vision processing thread - processes grip code
	private final Object imgLockBoiler = new Object();	// vision boiler object

	private UsbCamera usbCameraBoiler;			// USB Camera
	private AxisCamera axisCameraBoiler;			// Axis Camera

    private CvSource outputStreamBoiler;			// Output stream to the Dashboard

	
	// Targeting variables
	private int brectX = 0; 		// the 4 values used which define the full rectangle around the target
	private int brectY = 0;
	private int brectWidth = 0;
	private int brectHeight = 0;
	
	private int bminX = 0;		// the 4 values used to create maximum rectangle around the target (used when evaluating each of the contour images)
	private int bminY = 0;
	private int bmaxX = 0;
	private int bmaxY = 0;
	
	private boolean boilerHasTarget		= false;		// flag indicating whether the robot sees the target
	private boolean boilerIsCentered 		= false;		// flag indicating whether the robot sees the center of the target
	private boolean boilerIsAtDistance 	= false;		// flag indicating whether the robot is at the correct distance from the target			
	private boolean boilerIsAtHeight 		= false;		// Determine if the robot is at height (eyy, that's the name of the boolean!)
								
	private int boilerTargetHeight = AutoConstants.BOILER_TARGET_HEIGHT;		//actual height of the boilers tape
	private int boilerTargetWidth = AutoConstants.BOILER_TARGET_WIDTH;		//actual width of the boilers tape
	
	private double trueCenter = bImageWidth/2; 									// horizontal value of the center of the target 

//	private double boilerDistanceToStop 	= 0.0;		// distance to stop at in front of lift target
	private double boilerCurrentCenter 	= 0.0; 		// horizontal value of where robot is looking
	private double boilerCurrentDistance	= 0.0; 		// distance robot is from the target
	private double boilerStopAtHeight 	= 0.0;		// distance to stop at based on height
	private double boilerStopAtDistance 	= 0.0;		// distance to stop at based on sonar

	private double thresholdX = AutoConstants.X_THRESHOLD_CENTER;					// threshold for determining center of the target
	private double thresholdDistance = AutoConstants.DISTANCE_TARGET_THRESHOLD; 	// threshold for determining threshold for stopping at the lift peg
	private double minThresholdX = AutoConstants.X_THRESHOLD_HAS_TARGET_MIN;	// threshold for determining min value for whether the robot sees the target
	private double maxThresholdX = AutoConstants.X_THRESHOLD_HAS_TARGET_MAX;	// threshold for determining max value for whether the robot sees the target
	
	// Sweep Variables
	private boolean boilerInSweep = false;		// flag indicating whether the robot is sweeping left and right looking for the target
	private double  boilerSweepDirection = 0.0;	// value indicating the direction of the sweep -1 = right; 1 = left
	private int boilerSweepCounter = 0;			// value indicating when the sweep will change direction
	
	
	// drive variables
	private double boilerRotateDiff = 0;			// for smartdashboard - how far away from center 
    private double boilerMoveDiff = 0;			// for smartdashboard - how far away from target
	double boilerRotate;							// speed of the rotate being returned to the command
	double boilerMove; 							// speed of the move forward being returned to the command

    
	// Vision Processing 
	public VisionBoiler() {
		super("Vision Processor");


		// define the Cameras:
		axisCameraBoiler=CameraServer.getInstance().addAxisCamera("cam1", "10.7.8.11");

//		usbCameraBoiler=CameraServer.getInstance().startAutomaticCapture("cam0", 0);
//		axisCamera.setResolution(imageWidth, imageHeight);
		
	   
	    // define the output stream on the smart dashboard
		outputStreamBoiler = CameraServer.getInstance().putVideo("Target Boiler", bImageWidth, bImageHeight);
		
		
		// Vision thread which processes the image contours
	    visionThreadBoiler = new VisionThread(axisCameraBoiler, new GripPipelineBoiler(), boilerPipeline -> {
	    	bPipelineSize = boilerPipeline.filterContoursOutput().size();
	    	
	    	// if the grip pipeline filter "filterContoursOutput" sees the target
	    	// loop through each contour image  
	    	// grab the bounding rectangle values of each contour 
	    	// to create the biggest rectangle around the 2 vertical retroreflective tapes 
	    	// on either side of the lift peg
	        if (!boilerPipeline.filterContoursOutput().isEmpty()) {
	        	
	        	for (int i = 0; i < boilerPipeline.filterContoursOutput().size(); i++) {
	        		Rect r = Imgproc.boundingRect(boilerPipeline.filterContoursOutput().get(i));
	        		
	        		// set the min/max values to match the values form the 1st image
	        		if (i == 0) {
	        			bminX = r.x;
	        			bminY = r.y;
	        			bmaxX = r.x + r.width;
	        			bmaxY = r.y + r.height;
	        		}
	        		
	        		// compare each value to the min/max and replace if a better one is found
	        		if (r.x < bminX) {
	        			bminX = r.x;
	        		}
	        		if (r.y < bminY) {
	        			bminY = r.y;
	        		}
	        		if (r.x + r.width > bmaxX) {
	        			bmaxX = r.x + r.width;
	        		}
	        		if (r.y + r.height> bmaxY) {
	        			bmaxY = r.y + r.height;
	        		}	        		
	        	}
	        	
//				// this is a second method of looping through the contours in the filterContoursOutput Array of Mat Images	        	
//	        	for (MatOfPoint contour : pipeline.filterContoursOutput()) {
//	        		Rect r = Imgproc.boundingRect(contour);
//	        		if (r.x < minX) {
//	        			minX = r.x;
//			        }
//	        	}
	        	

	        	
	            synchronized (imgLockBoiler) {
	                boilerCurrentCenter = bminX + ((bmaxX - bminX) / 2);
	                
		             // set values for the smartdashboard
		             brectX = bminX;
		             brectY = bminY;
		             brectWidth = bmaxX - bminX;
		             brectHeight = bmaxY - bminY;
	
		             // note - this formula was pulled from 1640's github code - need to find the specific reference
			         // from 1640
		             //Equation to determine the distance from a target (d) given the width in pixels of a vision target in the camera image (w): 
			         //   	d = (TARGET_WIDTH*CAMERA_IMAGE_WIDTH)/(2*tan(FOV_ANGLE/2)*w) 
			         //   	i.e. d and w are inversely related.
		      //	test the fovDegrees values      
		      //    boilerCurrentDistance = boilerTargetWidth * bImageWidth / (2*(Math.tan(Math.toRadians(fovDegrees))/2)*brectWidth);
		            
		            // display the current image on the driver station 
		             
		            if (Constants.DEBUG){
		            	outputStreamBoiler.putFrame(boilerPipeline.hsvThresholdOutput()); 	               
		            }  
	            }
	            
	        }
	        
	        //  the target is not in the camera (ie, pipeline is empty)
	        else {
	        	boilerHasTarget = false;
	        	bminX = 0;
	        	bminY = 0;
	        	bmaxX = 0;
	        	bmaxY = 0;
	        } 
	       
	    });
	    visionThreadBoiler.start();
	}
	
	/*
	 * GetClosestLocation
	 * Determine which shooting location is closer to the robot
	 * Will not use right now
	 */
//	public double getClosestLocation() {
//		if(Robot.drivetrain.getSonarDistance() >= AutoConstants.DISTANCE_TO_BOILER_LOCATION2/2) {
//			return AutoConstants.DISTANCE_TO_BOILER_LOCATION2;
//		}
//		else {
//			return AutoConstants.DISTANCE_TO_BOILER_LOCATION1;
//		}
//	}

	/*
	 * ProcessData
	 * Method to interpret the camera data received above
	 */
	public void boilerProcessData() {
		try {
			
			// use the sonar to get the distance from the target (backup plan if camera distance not available)
			
//UPDATE THIS TO FIX DISTANCE
			boilerCurrentDistance = Robot.drivetrain.getSonarDistance() - 22;
			putBoilerCurrentDistance(boilerCurrentDistance);
		    
			// if robot sees the target (current X between its min and max)
			if ((boilerCurrentCenter > minThresholdX) && (boilerCurrentCenter < maxThresholdX)) {
				boilerHasTarget = true;
			} 
			else {
				boilerHasTarget = false;
			}
			
		} catch (TableKeyNotDefinedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * isCentered
	 * Method to determine whether the robot sees the center of the target (within the threshold value)
	 */
	public boolean boilerIsCentered() {
		
		// if the robot sees the target 
		// determine whether the horizontal value the robot sees is within the threshold defining the center of the target
		// set isCentered according to whether the robot is aligned with the center of the target
		if (boilerHasTarget) 
		{
			
			double difference = trueCenter - (boilerCurrentCenter);			
			if (Math.abs(difference) <= thresholdX) {
				boilerIsCentered = true;
			}
			else if (Math.abs(difference) > thresholdX) {
				boilerIsCentered = false;
			}
			boilerRotateDiff = difference;
		}
		else{
			boilerIsCentered = false;
		}
			return boilerIsCentered;
	}
	
	/*
	 * getRotate
	 * Method to determine whether the robot is at the center of the target so it can drive towards target
	 */
	public double boilerGetRotate() {
		double difference=0;
		
		// currently we are only running 1 cycle of the sweep and stopping
		// if in the future additional sweeps are required, this is where the reset should occur
//		if (sweepCounter > 400){
//			sweepCounter = 0;
//		}
		
		// if robot sees target and is centered - no need to rotate the robot
		if (boilerHasTarget && boilerIsCentered) 
		{
			boilerRotate = 0.0;
		}
		
		// if the robot sees the target but is not centered
		// check to see whether the robot is within the threshold
		// rotate based on the math function
		else if (boilerHasTarget && !boilerIsCentered){
			difference = trueCenter - (boilerCurrentCenter);

			boilerRotate = Math708.getSignClippedPercentError(boilerCurrentCenter, trueCenter, AutoConstants.DRIVE_ROTATE_MIN, AutoConstants.DRIVE_ROTATE_MAX);
		    boilerRotate = .3;
			
			if (Math.abs(difference) > thresholdX) {
				if (boilerCurrentCenter < trueCenter){
					boilerRotate = Math.abs(boilerRotate);
				}
				else {
					boilerRotate = Math.abs(boilerRotate) * -1;
				}
			}
		}
		
		// if the robot does not have the target
		// begin the sweep
		// sweep is defined as rotating the robot right, left, right in predefined counts 
		// if in the sweep the robot does not find the target, it stops after 3 sweeps
		// otherwise it will jump back into the hasTarget logic identified above
		else if (!boilerHasTarget){
			if (Math.abs(boilerSweepDirection) < .1){
				boilerSweepDirection = AutoConstants.SWEEP_DIRECTION_RIGHT;
				boilerRotate = -AutoConstants.SWEEP_ROTATE;
			}
			else if (boilerSweepDirection > AutoConstants.SWEEP1_MIN){
				if ((boilerSweepCounter >= AutoConstants.SWEEP1_MIN && boilerSweepCounter <= AutoConstants.SWEEP1_MAX)
				|| (boilerSweepCounter >= AutoConstants.SWEEP3_MIN && boilerSweepCounter <= AutoConstants.SWEEP3_MAX)){
				
					boilerRotate = -AutoConstants.SWEEP_ROTATE;
					if (boilerSweepCounter== AutoConstants.SWEEP1_MAX || boilerSweepCounter== AutoConstants.SWEEP3_MAX){
						boilerSweepDirection = AutoConstants.SWEEP_DIRECTION_LEFT;
					}
				}
			}
			else {
				if (boilerSweepCounter >= AutoConstants.SWEEP2_MIN && boilerSweepCounter <= AutoConstants.SWEEP2_MAX)
					boilerRotate = AutoConstants.SWEEP_ROTATE;
					if (boilerSweepCounter== AutoConstants.SWEEP2_MAX){
						boilerSweepDirection = AutoConstants.SWEEP_DIRECTION_RIGHT;
				}
			}
				
			boilerSweepCounter++;
		}
		boilerRotateDiff = difference;
		
		return boilerRotate;
	}
	
	/*
	 * getMove
	 * Method to determine if the robot is close enough to target so it can stop
	 */

	public double boilerGetMove() {	
		// if the robot sees the target
		// Method to determine whether the robot is at the correct distance to the target so stop
		if (boilerHasTarget) 
		{
			//maxY is used as height of the target
//			double difference = boilerDistanceToStop - bmaxY;		
			double difference = boilerCurrentDistance - boilerStopAtDistance;
			boilerMove = Math708.getSignClippedPercentError(boilerCurrentDistance, boilerStopAtDistance, AutoConstants.DRIVE_MOVE_MIN, AutoConstants.DRIVE_MOVE_MAX); 
			boilerMove = .3;
			
			if (difference < 0)
			{
				boilerMove = boilerMove *-1;
			}
			
			//Check if target is at correct distance within threshold
			if (Math.abs(difference) <= thresholdDistance) {
				boilerMove = 0.0;
				boilerIsAtDistance = true;
			} else {
				boilerIsAtDistance = false;
			}
			boilerMoveDiff = difference;
		} else {
 			boilerMove = 0.0;
		}

		return boilerMove;
	}
	



	
	/**
	 * GETTERS and PUTTERS to return the private variables
	 * @return
	 */
	
	/*
	 * isAtDistance
	 * Method to determine whether the robot is at the distance from the target based on the threshold value
	 */

	public boolean boilerIsAtDistance() {
		double difference = boilerStopAtDistance - getBoilerCurrentDistance();			
		SmartDashboard.putNumber("difference in IsAtDistance", difference);
		//Check if target is at correct level within threshold
		if (Math.abs(difference) <= thresholdDistance) {
			boilerIsAtDistance = true;
		} else {
			boilerIsAtDistance = false;
		}
		return boilerIsAtDistance;
	}
	
	/*
	 * isAtHeight
	 * Method to determine whether the robot is at the distance from the target based on the threshold value
	 */
// VIET update this
//	public boolean isAtHeight() {
//		double difference = stopAtHeight - maxY;			
//		//Check if target is at correct level within threshold
//		if (Math.abs(difference) <= thresholdHeight) {
//			isAtHeight = true;
//		} else {
//			isAtHeight = false;
//		}
//		return isAtHeight;
//	}
	
	public boolean boilerIsHasTarget() {
		return boilerHasTarget;
	}
	
	
	public void putBoilerCurrentCenter(double cc) {
		boilerCurrentCenter = cc;
	}
	
	
	public void putBoilerHasTarget(boolean ht) {
		boilerHasTarget = ht;
	}
	
	public int getBoilerCounter() {
		return boilerSweepCounter;
	}
	
	public double getBoilerCurrentDistance() {
		return boilerCurrentDistance;
	}
	
	public void putBoilerCounter(int ct) {
		boilerSweepCounter = ct;
	}
	
	public void putBoilerIsCentered(boolean ic) {
		boilerIsCentered = ic;
	}
	
	public void putBoilerStopAtDistance (double sad) {
		boilerStopAtDistance = sad;
		// :(
	}
	
	public void putBoilerCurrentDistance (double cd) {
		boilerCurrentDistance = cd;
		// :(
	}

	
	public void putBoilerAtDistance(boolean ad) {
		boilerIsAtDistance = ad;
	}
	
	public boolean boilerIsInSweep() {
		if (boilerHasTarget) {
			boilerInSweep = false;
			boilerSweepCounter=1;
		}
		else {
			boilerInSweep = true;
		}
		return boilerInSweep;
	}

	public void sendToDashboard() {
		if (Constants.BOILER_DEBUG) {
			SmartDashboard.putNumber("b-True Center", trueCenter);
			SmartDashboard.putBoolean("b-Has Target", boilerIsHasTarget());
			SmartDashboard.putBoolean("b-IsAtDistance", boilerIsAtDistance());
			SmartDashboard.putNumber("b-Center of Target", boilerCurrentCenter);
			SmartDashboard.putNumber("b-Rotation", boilerRotate);
			SmartDashboard.putNumber("b-Rotate Difference", boilerRotateDiff);
			SmartDashboard.putNumber("b-Distance Difference", boilerMoveDiff);
			SmartDashboard.putNumber("b-Sweep Counter", boilerSweepCounter);
			SmartDashboard.putNumber("b-SweepDirection", boilerSweepDirection);
			SmartDashboard.putBoolean("b-isCentered", boilerIsCentered());
			SmartDashboard.putNumber("b-rectX", brectX);
			SmartDashboard.putNumber("b-maxY", bmaxY);
			SmartDashboard.putNumber("b-rectY", brectY);
			SmartDashboard.putNumber("b-rectWidth", brectWidth);
			SmartDashboard.putNumber("b-rectHeight", brectHeight);
			SmartDashboard.putNumber("b-Distance To Target", boilerCurrentDistance);
			SmartDashboard.putNumber("b-pipelineSize", bPipelineSize);
			SmartDashboard.putNumber("b-stop at distance", boilerStopAtDistance);

		}
	}

    public void initDefaultCommand() {
		if (Constants.DEBUG) {
		}    	
    }
}


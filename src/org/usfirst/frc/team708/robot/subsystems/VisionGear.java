package org.usfirst.frc.team708.robot.subsystems;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team708.robot.AutoConstants;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.visionProcessor.GripPipelineGear;
import org.usfirst.frc.team708.robot.util.Math708;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 *@authors Viet & Sue
 *This subsystem is specific to the 2017 Game FIRST Steamworks Gear
 */
public class VisionGear extends Subsystem {
	
	// Camera Variables
	private double fovDegrees = AutoConstants.USB_FOV_DEGREES;			// Field of View of the Camera
	private double pipelineSize;										// Number of Contours in the Pipline- 0 = target not in view
	private int imageWidth = AutoConstants.USB_IMG_WIDTH;				// Width of image
	private int imageHeight = AutoConstants.USB_IMG_HEIGHT;			// Height of image
	
	// Image OpenCV Image Processing Variables
	private VisionThread visionThread;				// vision processing thread - processes grip code
	private final Object imgLock = new Object();	// vision Gear object

	private AxisCamera axisCamera;			// Axis Camera
	private UsbCamera usbCamera;			// USB Camera
    private CvSource outputStream;			// Output stream to the Dashboard

	
	// Targeting variables
	private int rectX = 0; 		// the 4 values used which define the full rectangle around the target
	private int rectY = 0;
	private int rectWidth = 0;
	private int rectHeight = 0;
	
	private int minX = 0;		// the 4 values used to create maximum rectangle around the target (used when evaluating each of the contour images)
	private int minY = 0;
	private int maxX = 0;
	private int maxY = 0;
	
	private boolean hasTarget = false;		// flag indicating whether the robot sees the target
	private boolean isCentered = false;		// flag indicating whether the robot sees the center of the target
	private boolean isAtDistance = false;	// flag indicating whether the robot is at the correct distance from the target			
	
								
	private int TargetHeight = AutoConstants.GEAR_TARGET_HEIGHT;	//Target height
	private int TargetWidth = AutoConstants.GEAR_TARGET_WIDTH;		//Target width
	
	private double trueCenter = imageWidth/2; 									// horizontal value of the center of the target 
	private double distanceToStop = AutoConstants.DISTANCE_TO_GEAR; 	// distance to stop at in front of gear
	private double currentCenter = 0.0; 											// horizontal value of where robot is looking
	private double currentDistance = 0.0; 									// distance robot is from the target

	private double thresholdX = AutoConstants.X_THRESHOLD_CENTER;					// threshold for determining center of the target
	private double thresholdDistance = AutoConstants.DISTANCE_TARGET_THRESHOLD; 	// threshold for determining threshold for stopping at the gear
//VIET NEED MIN/MAX FOR GEAR AND CENTER
	private double minThresholdX = AutoConstants.X_THRESHOLD_HAS_TARGET_MIN;	// threshold for determining min value for whether the robot sees the target
	private double maxThresholdX = AutoConstants.X_THRESHOLD_HAS_TARGET_MAX;	// threshold for determining max value for whether the robot sees the target

	
	// Sweep Variables
	private boolean inSweep = false;		// flag indicating whether the robot is sweeping left and right looking for the target
	private double  sweepDirection = 0.0;	// value indicating the direction of the sweep -1 = right; 1 = left
	private int sweepCounter = 0;			// value indicating when the sweep will change direction
	
	
	// drive variables
	private double RotateDiff = 0;			// for smartdashboard - how far away from center 
    private double MoveDiff = 0;			// for smartdashboard - how far away from target
	double rotate;							// speed of the rotate being returned to the command
	double move; 							// speed of the move forward being returned to the command

    
	// Vision Processing 
	public VisionGear() {
		super("Vision Processor");


		// define the Cameras:
		usbCamera=CameraServer.getInstance().startAutomaticCapture("cam3", 0);
//	 	axisCamera=CameraServer.getInstance().addAxisCamera("cam1", "10.7.8.11");
//		axisCamera.setResolution(imageWidth, imageHeight);
		
	   
	    // define the output stream on the smart dashboard
		outputStream = CameraServer.getInstance().putVideo("Target", imageWidth, imageHeight);
		
		
		// Vision thread which processes the image contours
	    visionThread = new VisionThread(usbCamera, new GripPipelineGear(), pipeline -> {
	    	pipelineSize = pipeline.filterContoursOutput().size();
	    	
	    	// if the grip pipeline filter "filterContoursOutput" sees the target
	    	// loop through each contour image  
	    	// grab the bounding rectangle values of each contour 
	    	// to create the biggest rectangle around the gear
	        if (!pipeline.filterContoursOutput().isEmpty()) {
	        	
	        	for (int i = 0; i < pipeline.filterContoursOutput().size(); i++) {
	        		Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(i));
	        		
	        		// set the min/max values to match the values form the 1st image
	        		if (i == 0) {
	        			minX = r.x;
	        			minY = r.y;
	        			maxX = r.x + r.width;
	        			maxY = r.y + r.height;
	        		}
	        		
	        		// compare each value to the min/max and replace if a better one is found
	        		if (r.x < minX) {
	        			minX = r.x;
	        		}
	        		if (r.y < minY) {
	        			minY = r.y;
	        		}
	        		if (r.x + r.width > maxX) {
	        			maxX = r.x + r.width;
	        		}
	        		if (r.y + r.height> maxY) {
	        			maxY = r.y + r.height;
	        		}	        		
	        	}
	        	
//				// this is a second method of looping through the contours in the filterContoursOutput Array of Mat Images	        	
//	        	for (MatOfPoint contour : pipeline.filterContoursOutput()) {
//	        		Rect r = Imgproc.boundingRect(contour);
//	        		if (r.x < minX) {
//	        			minX = r.x;
//			        }
//	        	}
	        	

	        	
	            synchronized (imgLock) {
	                currentCenter = minX + ((maxX - minX) / 2);
	                
		             // set values for the smartdashboard
		             rectX = minX;
		             rectY = minY;
		             rectWidth = maxX - minX;
		             rectHeight = maxY - minY;
	
		             // note - this formula was pulled from 1640's github code - need to find the specific reference
			         // from 1640
		             //Equation to determine the distance from a target (d) given the width in pixels of a vision target in the camera image (w): 
			         //   	d = (TARGET_WIDTH*CAMERA_IMAGE_WIDTH)/(2*tan(FOV_ANGLE/2)*w) 
			         //   	i.e. d and w are inversely related.
		             currentDistance = TargetWidth * imageWidth / (2*(Math.tan(Math.toRadians(fovDegrees))/2)*rectWidth);
		             
		            // display the current image on the driver station 
		             
		            if (Constants.DEBUG){
		            	outputStream.putFrame(pipeline.hslThresholdOutput()); 	               
		            }  
	            }
	            
	        }
	        
	        //  the target is not in the camera (ie, pipeline is empty)
	        else {
	        	hasTarget = false;
	        	minX = 0;
	        	minY = 0;
	        	maxX = 0;
	        	maxY = 0;
	        } 
	       
	    });
	    visionThread.start();
	}
	
	
	/*
	 * ProcessData
	 * Method to interpret the camera data received above
	 */
	public void processData() {
		try {
			
			// use the sonar to get the distance from the target (backup plan if camera distance not available)
//			currentDistance=Robot.drivetrain.getSonarDistance();
		    
			// if robot sees the target (current X between its min and max)
			if ((currentCenter > minThresholdX) && (currentCenter < maxThresholdX)) {
				hasTarget = true;
			} 
			else {
				hasTarget = false;
			}
			
		} catch (TableKeyNotDefinedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * isCentered
	 * Method to determine whether the robot sees the center of the target (within the threshold value)
	 */
	public boolean isCentered() {
		
		// if the robot sees the target 
		// determine whether the horizontal value the robot sees is within the threshold defining the center of the target
		// set isCentered according to whether the robot is aligned with the center of the target
		if (hasTarget) 
		{
			
			double difference = trueCenter - (currentCenter);			
			if (Math.abs(difference) <= thresholdX) {
				isCentered = true;
			}
			else if (Math.abs(difference) > thresholdX) {
				isCentered = false;
			}
			RotateDiff = difference;
		}
		else{
			isCentered = false;
		}
			return isCentered;
	}
	
	/*
	 * getRotate
	 * Method to determine whether the robot is at the center of the target so it can drive towards target
	 */
	public double getRotate() {
		double difference=0;
		
		// currently we are only running 1 cycle of the sweep and stopping
		// if in the future additional sweeps are required, this is where the reset should occur
//		if (sweepCounter > 400){
//			sweepCounter = 0;
//		}
		
		// if robot sees target and is centered - no need to rotate the robot
		if (hasTarget && isCentered) 
		{
			rotate = 0.0;
		}
		
		// if the robot sees the target but is not centered
		// check to see whether the robot is within the threshold
		// rotate based on the math function
		else if (hasTarget && !isCentered){
			difference = trueCenter - (currentCenter);

			rotate = Math708.getSignClippedPercentError(currentCenter, trueCenter, AutoConstants.DRIVE_ROTATE_MIN, AutoConstants.DRIVE_ROTATE_MAX);
		
			
			if (Math.abs(difference) > thresholdX) {
				if (currentCenter < trueCenter){
					rotate = Math.abs(rotate);
				}
				else {
					rotate = Math.abs(rotate) * -1;
				}
			}
		}
		
		// if the robot does not have the target
		// begin the sweep
		// sweep is defined as rotating the robot right, left, right in predefined counts 
		// if in the sweep the robot does not find the target, it stops after 3 sweeps
		// otherwise it will jump back into the hasTarget logic identified above
		else if (!hasTarget){
			if (Math.abs(sweepDirection) < .1){
				sweepDirection = AutoConstants.SWEEP_DIRECTION_RIGHT;
				rotate = -AutoConstants.SWEEP_ROTATE;
			}
			else if (sweepDirection > AutoConstants.SWEEP1_MIN){
				if ((sweepCounter >= AutoConstants.SWEEP1_MIN && sweepCounter <= AutoConstants.SWEEP1_MAX)
				|| (sweepCounter >= AutoConstants.SWEEP3_MIN && sweepCounter <= AutoConstants.SWEEP3_MAX)){
				
					rotate = -AutoConstants.SWEEP_ROTATE;
					if (sweepCounter== AutoConstants.SWEEP1_MAX || sweepCounter== AutoConstants.SWEEP3_MAX){
						sweepDirection = AutoConstants.SWEEP_DIRECTION_LEFT;
					}
				}
			}
			else {
				if (sweepCounter >= AutoConstants.SWEEP2_MIN && sweepCounter <= AutoConstants.SWEEP2_MAX)
					rotate = AutoConstants.SWEEP_ROTATE;
					if (sweepCounter== AutoConstants.SWEEP2_MAX){
						sweepDirection = AutoConstants.SWEEP_DIRECTION_RIGHT;
				}
			}
				
			sweepCounter++;
		}
		RotateDiff = difference;
		return rotate;
	}
	
	/*
	 * getMove
	 * Method to determine if the robot is close enough to target so it can stop
	 */

	public double getMove() {
		
		// if the robot sees the target
		// Method to determine whether the robot is at the correct distance to the target so stop
		if (hasTarget) 
		{
			double difference = distanceToStop - currentDistance;			
			move = Math708.getSignClippedPercentError(currentDistance, distanceToStop, AutoConstants.DRIVE_MOVE_MIN, AutoConstants.DRIVE_MOVE_MAX); 

			//Check if target is at correct distance within threshold
			if (Math.abs(difference) <= thresholdDistance) {
				move = 0.0;
				isAtDistance = true;
			} else {
				isAtDistance = false;
			}
			MoveDiff = difference;
		} else {
 			move = 0.0;
		}
		
		return move;
	}
	/*
	 * isAtDistance
	 * Method to determine whether the robot is at the distance from the target based on the threshold value
	 */
	public boolean isAtDistance() {
		double difference = distanceToStop - currentDistance;			
		//Check if target is at correct level within threshold
		if (Math.abs(difference) <= thresholdDistance) {
			isAtDistance = true;
		} else {
			isAtDistance = false;
		}
		return isAtDistance;
	}
	
	/**
	 * GETTERS and PUTTERS to return the private variables
	 * @return
	 */
	public boolean isHasTarget() {
		return hasTarget;
	}
	
	
	public void putCurrentCenter(double cc) {
		currentCenter = cc;
	}
	
	
	public void putHasTarget(boolean ht) {
		hasTarget = ht;
	}

	
	public int getCounter() {
		return sweepCounter;
	}
	
	
	public void putCounter(int ct) {
		sweepCounter = ct;
	}
	
	public void putIsCentered(boolean ic) {
		isCentered = ic;
	}
	
	
	public void putAtDistance(boolean ay) {
		isAtDistance = ay;
	}
	
	
	public boolean isInSweep() {
		if (hasTarget) {
			inSweep = false;
			sweepCounter=1;
		}
		else {
			inSweep = true;
		}
		return inSweep;
	}

	public void sendToDashboard() {
		if (Constants.DEBUG) {
			SmartDashboard.putBoolean("Has Target", isHasTarget());
			SmartDashboard.putBoolean("Is At Distance", isAtDistance());
			SmartDashboard.putNumber("Current Distance", currentDistance);
			SmartDashboard.putNumber("Center of Target", currentCenter);
			SmartDashboard.putNumber("Rotation", rotate);
			SmartDashboard.putNumber("Rotate Difference", RotateDiff);
			SmartDashboard.putNumber("Distance Difference", MoveDiff);
			SmartDashboard.putNumber("Sweep Counter", sweepCounter);
			SmartDashboard.putNumber("SweepDirection", sweepDirection);
			SmartDashboard.putBoolean("isCentered", isCentered());
			SmartDashboard.putNumber("rectX", rectX);
			SmartDashboard.putNumber("rectY", rectY);
			SmartDashboard.putNumber("rectWidth", rectWidth);
			SmartDashboard.putNumber("rectHeight", rectHeight);
			SmartDashboard.putNumber("Distance To Target", currentDistance);
			SmartDashboard.putNumber("pipelineSize", pipelineSize);
		}
	}

    public void initDefaultCommand() {
		if (Constants.DEBUG) {
		}    	
    }
}


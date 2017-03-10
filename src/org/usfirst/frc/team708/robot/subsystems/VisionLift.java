package org.usfirst.frc.team708.robot.subsystems;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team708.robot.AutoConstants;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.visionProcessor.GripPipelineLift;
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
 *This subsystem is specific to the 2017 Game FIRST Steamworks Lift Peg
 */

public  class VisionLift extends Subsystem {

	// Camera Variables
	private double fovDegrees = AutoConstants.USB_LIFT_FOV_DEGREES;		// Field of View of the Camera
	private double liftPipelineSize;									// Number of Contours in the Pipline- 0 = target not in view
	private int liftImageWidth = AutoConstants.USB_LIFT_IMG_WIDTH;		// Width of image from camera stream
	private int liftImageHeight = AutoConstants.USB_LIFT_IMG_HEIGHT;	// Height of image from camera stream
	
	// Image OpenCV Image Processing Variables
	private VisionThread visionThreadLift;				// vision processing thread - processes grip code for lift
	private final Object imgLockLift = new Object();	// vision Lift object

//	private AxisCamera axisCamera;			// Axis Camera
	private UsbCamera usbCameraLiftGear;			// USB Camera
    private CvSource outputStreamLift;			// Output stream to the Dashboard

	
	// Targeting variables
	private int lrectX = 0; 		// the 4 values used which define the full rectangle around the target
	private int lrectY = 0;
	private int grectX = 0;
	private int grectY = 0;
	private int lrectWidth = 0;
	private int lrectHeight = 0;
	private int grectWidth = 0;
	private int grectHeight = 0;
	
	private int lminX = 0;		// the 4 values used to create maximum rectangle around the target (used when evaluating each of the contour images)
	private int lminY = 0;
	private int lmaxX = 0;
	private int lmaxY = 0;
	private int gminX = 0;		// the 4 values used to create maximum rectangle around the target (used when evaluating each of the contour images)
	private int gminY = 0;
	private int gmaxX = 0;
	private int gmaxY = 0;
	
	private boolean liftHasTarget 		= false;		// flag indicating whether the robot sees the target
	private boolean liftIsCentered 		= false;		// flag indicating whether the robot sees the center of the target
	private boolean liftIsAtDistance 	= false;		// flag indicating whether the robot is at the correct distance from the target			
								
	private int liftTargetHeight 	= AutoConstants.LIFT_TARGET_HEIGHT;		//height of actual target reflective tape
	private int liftTargetWidth 	= AutoConstants.LIFT_TARGET_WIDTH;		//width of actual target reflective tape

	private double trueCenter 			= liftImageWidth/2; 						// horizontal value of the center of the camera image  
	private double liftDistanceToStop 	= AutoConstants.DISTANCE_TO_LIFT_TARGET; 	// distance to stop at in front of lift target
	private double liftCurrentCenter 	= 0.0; 										// horizontal value of where robot is looking
	private double liftCurrentDistance 	= 0.0; 										// distance robot is from the target
	
	private double thresholdX 			= AutoConstants.X_THRESHOLD_CENTER;			// threshold for determining center of the target
	private double thresholdDistance 	= AutoConstants.DISTANCE_TARGET_THRESHOLD; 	// threshold for determining threshold for stopping at the lift peg
	private double minThresholdX 		= AutoConstants.X_THRESHOLD_HAS_TARGET_MIN;	// threshold for determining min value for whether the robot sees the target
	private double maxThresholdX 		= AutoConstants.X_THRESHOLD_HAS_TARGET_MAX;	// threshold for determining max value for whether the robot sees the target

	
	// Sweep Variables
	private boolean liftInSweep 		= false;	// flag indicating whether the robot is sweeping left and right looking for the target
	private double  liftSweepDirection 	= 0.0;		// value indicating the direction of the sweep -1 = right; 1 = left
	private int 	liftSweepCounter 	= 0;		// value indicating when the sweep will change direction
	
	
	// drive variables
	private double liftRotateDiff 	= 0;	// for smartdashboard - how far away from center 
    private double liftMoveDiff 	= 0;	// for smartdashboard - how far away from target

    double rotate;							// speed of the rotate being returned to the command
	double move; 							// speed of the move forward being returned to the command

	// Vision Processing 
	public VisionLift() {
		super("Vision Processor");


// define the Cameras:
// on little bot - cam2, 1
// Pipeline outputs: hsl and 0 means lift, rgb and 1 means gear
//	 	axisCamera=CameraServer.getInstance().addAxisCamera("cam1", "10.7.8.11");

		usbCameraLiftGear=CameraServer.getInstance().startAutomaticCapture(AutoConstants.USB_CAMERA_ID, AutoConstants.USB_VIDEO_PORT);
		usbCameraLiftGear.setResolution(liftImageWidth, liftImageHeight);
		usbCameraLiftGear.setBrightness(40);        		//40 for lift  70 for gear
		usbCameraLiftGear.setExposureManual(25);			//25 for lift  48 for gear
		usbCameraLiftGear.setWhiteBalanceManual(10000);		//10000 for lift 2800 for gear
//		usbCameraLiftGear.setFPS(20);
		
	    // define the output stream on the smart dashboard
		outputStreamLift = CameraServer.getInstance().putVideo("Target_Lift", liftImageWidth, liftImageHeight);

		// Vision thread which processes the image contours
	    visionThreadLift = new VisionThread(usbCameraLiftGear, new GripPipelineLift(), lgPipeline -> {
	    	
	    	liftPipelineSize 	= lgPipeline.findContoursOutput().size();
	    	
	    	// if the grip pipeline filter "filterContours0Output" sees the target
	    	// loop through each contour image  
	    	// grab the bounding rectangle values of each contour 
	    	// to create the biggest rectangle around the 2 vertical retro-reflective tapes 
	    	// on either side of the lift peg
	        if (!lgPipeline.findContoursOutput().isEmpty()) {
	        	
	        	for (int i = 0; i < lgPipeline.findContoursOutput().size(); i++) {
	        		Rect lift = Imgproc.boundingRect(lgPipeline.findContoursOutput().get(i));
	        		
	        		// set the min/max values to match the values form the 1st image
	        		if (i == 0) {
	        			lminX = lift.x;
	        			lminY = lift.y;
	        			lmaxX = lift.x + lift.width;
	        			lmaxY = lift.y + lift.height;
	        		}
	        		
	        		// compare each value to the min/max and replace if a better one is found
	        		if (lift.x < lminX) {
	        			lminX = lift.x;
	        		}
	        		if (lift.y < lminY) {
	        			lminY = lift.y;
	        		}
	        		if (lift.x + lift.width > lmaxX) {
	        			lmaxX = lift.x + lift.width;
	        		}
	        		if (lift.y + lift.height> lmaxY) {
	        			lmaxY = lift.y + lift.height;
	        		}	        		
	        	}
	        	
	        	
	        	
//				// this is a second method of looping through the contours in the filterContours0Output Array of Mat Images	        	
//	        	for (MatOfPoint contour : pipeline.filterContours0Output()) {
//	        		Rect r = Imgproc.boundingRect(contour);
//	        		if (r.x < minX) {
//	        			minX = r.x;
//			        }
//	        	}
	        	

	        	
	            synchronized (imgLockLift) {
	                liftCurrentCenter = lminX + ((lmaxX - lminX) / 2);
	                
		             // set values for the smartdashboard
		             lrectX = lminX;
		             lrectY = lminY;
		             lrectWidth = lmaxX - lminX;
		             lrectHeight = lmaxY - lminY;
	
		             // note - this formula was pulled from 1640's github code - need to find the specific reference
			         // from 1640
		             //Equation to determine the distance from a target (d) given the width in pixels of a vision target in the camera image (w): 
			         //   	d = (TARGET_WIDTH*CAMERA_IMAGE_WIDTH)/(2*tan(FOV_ANGLE/2)*w) 
			         //   	i.e. d and w are inversely related.
		             liftCurrentDistance = liftTargetWidth * liftImageWidth / (2*(Math.tan(Math.toRadians(fovDegrees))/2)*lrectWidth);
		             
		            // display the current image on the driver station 
		             
//		            	outputStreamLift.putFrame(lgPipeline.resizeImageOutput());
	            }
	        }
	            	        
	        //  the target is not in the camera (ie, pipeline is empty)
	        else {
	        	liftHasTarget = false;
	        	lminX = 0;
	        	lminY = 0;
	        	lmaxX = 0;
	        	lmaxY = 0;
	        }
	        
	    });
	    
	    visionThreadLift.start();
	}   
	    
	/*
	 * liftProcessData
	 * Method to interpret the camera data received above
	 */	 
	public void liftProcessData() {
		try {
			
			// use the sonar to get the distance from the target (backup plan if camera distance not available)
	//		currentDistance=Robot.drivetrain.getSonarDistance();
	
			// if robot sees the target (current X between its min and max)
			if ((liftCurrentCenter > minThresholdX) && (liftCurrentCenter < maxThresholdX)) {
				liftHasTarget = true;
			} 
			else {
				liftHasTarget = false;
			}
			
		} catch (TableKeyNotDefinedException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * isCentered
	 * Method to determine whether the robot sees the center of the target (within the threshold value)
	 */
	public boolean liftIsCentered() {
		
		// if the robot sees the target 
		// determine whether the horizontal value the robot sees is within the threshold defining the center of the target
		// set isCentered according to whether the robot is aligned with the center of the target
		if (liftHasTarget) 
		{
			
			double difference = trueCenter - (liftCurrentCenter);			
			if (Math.abs(difference) <= thresholdX) {
				liftIsCentered = true;
			}
			else if (Math.abs(difference) > thresholdX) {
				liftIsCentered = false;
			}
			liftRotateDiff = difference;
		}
		else{
			liftIsCentered = false;
		}
	
			return liftIsCentered;
	}
		
	/*
	 * getRotate
	 * Method to determine whether the robot is at the center of the target so it can drive towards target
	 */
	public double liftGetRotate() {
		double difference=0;
		
		// currently we are only running 1 cycle of the sweep and stopping
		// if in the future additional sweeps are required, this is where the reset should occur
	//	if (sweepCounter > 400){
	//		sweepCounter = 0;
	//	}
		
		// if robot sees target and is centered - no need to rotate the robot
		if (liftHasTarget && liftIsCentered) 
		{
			rotate = 0.0;
		}
		
		// if the robot sees the target but is not centered
		// check to see whether the robot is within the threshold
		// rotate based on the math function
		else if (liftHasTarget && !liftIsCentered){
			difference = trueCenter - (liftCurrentCenter);
	
			rotate = Math708.getClippedPercentError(liftCurrentCenter, trueCenter, AutoConstants.DRIVE_ROTATE_MIN, AutoConstants.DRIVE_ROTATE_MAX);
		
			
			if (Math.abs(difference) > thresholdX) {
				if (liftCurrentCenter < trueCenter){
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
		else if (!liftHasTarget){
			if (Math.abs(liftSweepDirection) < .1){
				liftSweepDirection = AutoConstants.SWEEP_DIRECTION_RIGHT;
				rotate = -AutoConstants.SWEEP_ROTATE;
			}
			else if (liftSweepDirection > AutoConstants.SWEEP1_MIN){
				if ((liftSweepCounter >= AutoConstants.SWEEP1_MIN && liftSweepCounter <= AutoConstants.SWEEP1_MAX)
				|| (liftSweepCounter >= AutoConstants.SWEEP3_MIN && liftSweepCounter <= AutoConstants.SWEEP3_MAX)){
				
					rotate = -AutoConstants.SWEEP_ROTATE;
					if (liftSweepCounter== AutoConstants.SWEEP1_MAX || liftSweepCounter== AutoConstants.SWEEP3_MAX){
						liftSweepDirection = AutoConstants.SWEEP_DIRECTION_LEFT;
					}
				}
			}
			else {
				if (liftSweepCounter >= AutoConstants.SWEEP2_MIN && liftSweepCounter <= AutoConstants.SWEEP2_MAX)
					rotate = AutoConstants.SWEEP_ROTATE;
					if (liftSweepCounter== AutoConstants.SWEEP2_MAX){
						liftSweepDirection = AutoConstants.SWEEP_DIRECTION_RIGHT;
				}
			}
				
			liftSweepCounter++;
		}
		liftRotateDiff = difference;
		return rotate;
	}
	
	/*
	 * getMove
	 * Method to determine if the robot is close enough to target so it can stop
	 */
	
	public double liftGetMove() {
		
		// if the robot sees the target
		// Method to determine whether the robot is at the correct distance to the target so stop
		if (liftHasTarget) 
		{
			double difference = liftDistanceToStop - liftCurrentDistance;			
			move = Math708.getClippedPercentError(liftCurrentDistance, liftDistanceToStop, AutoConstants.DRIVE_MOVE_MIN, AutoConstants.DRIVE_MOVE_MAX); 
	
			//Check if target is at correct distance within threshold
			if (Math.abs(difference) <= thresholdDistance) {
				move = 0.0;
				liftIsAtDistance = true;
			} else {
				liftIsAtDistance = false;
			}
			liftMoveDiff = difference;
		} else {
				move = 0.0;
		}
		
		return move;
	}
	

	
	/**
	 * GETTERS and PUTTERS to return the private variables
	 * @return
	 */
	
	
	public boolean liftIsAtDistance() {
		double difference = liftDistanceToStop - liftCurrentDistance;			
		//Check if target is at correct level within threshold
		if (Math.abs(difference) <= thresholdDistance) {
			liftIsAtDistance = true;
		} else {
			liftIsAtDistance = false;
		}
		return liftIsAtDistance;
	}
	
	public boolean liftIsHasTarget() {
		return liftHasTarget;
	}
	
	public void putLiftHasTarget(boolean lht) {
		liftHasTarget = lht;
	}
	
	public void putLiftCurrentCenter(double lcc) {
		liftCurrentCenter = lcc;
	}
	

	public int getLiftCounter() {
		return liftSweepCounter;
	}
	
	public void putLiftCounter(int lct) {
		liftSweepCounter = lct;
	}
	
	public void putLiftIsCentered(boolean lic) {
		liftIsCentered = lic;
	}
	
	public void putLiftAtDistance(boolean lad) {
		liftIsAtDistance = lad;
	}
	
	public boolean liftIsInSweep() {
		if (liftHasTarget) {
			liftInSweep = false;
			liftSweepCounter=1;
		}
		else {
			liftInSweep = true;
		}
		return liftInSweep;
	}
	
	public void setLiftCamera() {
		usbCameraLiftGear.setBrightness(40);        		//40 for lift  70 for gear
		usbCameraLiftGear.setExposureManual(25);			//25 for lift  48 for gear
		usbCameraLiftGear.setWhiteBalanceManual(10000);		//10000 for lift 2800 for gear
		usbCameraLiftGear.setFPS(20);
	}

	public void sendToDashboard() {
		if (Constants.LIFT_DEBUG) {
		   
			SmartDashboard.putBoolean("L-Has Target", liftIsHasTarget());
			SmartDashboard.putBoolean("L-Is At Distance", liftIsAtDistance());
			SmartDashboard.putNumber("L-Center of Target", liftCurrentCenter);
			SmartDashboard.putNumber("L-Rotation", rotate);
			SmartDashboard.putNumber("L-Rotate Difference", liftRotateDiff);
			SmartDashboard.putNumber("L-Distance Difference", liftMoveDiff);
			SmartDashboard.putNumber("L-Sweep Counter", liftSweepCounter);
			SmartDashboard.putNumber("L-SweepDirection", liftSweepDirection);
			SmartDashboard.putBoolean("L-isCentered", liftIsCentered());
			SmartDashboard.putNumber("L-rectX", lrectX);
			SmartDashboard.putNumber("L-rectY", lrectY);
			SmartDashboard.putNumber("L-rectWidth", lrectWidth);
			SmartDashboard.putNumber("L-rectHeight", lrectHeight);
			SmartDashboard.putNumber("L-Distance To Target", liftCurrentDistance);
			SmartDashboard.putNumber("L-pipelineSize", liftPipelineSize);
		}
	}
	
	public void initDefaultCommand() {
		if (Constants.DEBUG) {
		}    	
	}

}
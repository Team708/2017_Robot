package org.usfirst.frc.team708.robot;

public final class AutoConstants {
	
	// Threshold Constants 
	public static final double DISTANCE_TARGET_THRESHOLD	= 5;	// threshold for determining the distance to stop in front of the lift
	public static final double X_THRESHOLD_CENTER 			= 20;	// threshold for determining center of the target 
	public static final double X_THRESHOLD_HAS_TARGET_MIN 	= 20;	// threshold for determining min value for whether the robot sees the target	 
	public static final double X_THRESHOLD_HAS_TARGET_MAX 	= 300;	// threshold for determining max value for whether the robot sees the target
//	public static final double HEIGHT_THRESHOLD				= 20;
	
	// Sweep Constants
	public static final int SWEEP1_MIN						= 0;
	public static final int SWEEP1_MAX 						= 100;
	public static final int SWEEP2_MIN 						= 101;
	public static final int SWEEP2_MAX 						= 300;
	public static final int SWEEP3_MIN 						= 301;
	public static final int SWEEP3_MAX 						= 400;
	public static final int SWEEP_DIRECTION_LEFT 			= -1;
	public static final int SWEEP_DIRECTION_RIGHT 			= 1;
	public static final double SWEEP_ROTATE 				= .6;

	// Drivetrain Constants
	public static final double DRIVE_ROTATE_MIN 			= .4;
	public static final double DRIVE_ROTATE_MAX 			= .5;
	public static final double DRIVE_MOVE_MIN 				= .3;
	public static final double DRIVE_MOVE_MAX 				= .4;
	
	// Lift Constants (Field)
	public static final int LIFT_TARGET_HEIGHT 				= 5;
	public static final int LIFT_TARGET_WIDTH 				= 10;
	public static final int DISTANCE_TO_LIFT_TARGET 		= 14;	// Distance to stop at to place gear on lift peg
	
	
	// Boiler Constants (Field)
	public static final int BOILER_TARGET_HEIGHT 			= 10;
	public static final int BOILER_TARGET_WIDTH 			= 14;
	public static final double DISTANCE_TO_BOILER_LOCATION1 = 54;	// A distance to stop at and shoot for high goal in inches
	public static final double DISTANCE_TO_BOILER_LOCATION2	= 108;	// A distance to stop at and shoot for high goal in inches (9')
	
	// Gear Constants (Game piece)
	public static final double DISTANCE_TO_GEAR				= 15;
	public static final int GEAR_TARGET_HEIGHT 				= 4;
	public static final int GEAR_TARGET_WIDTH 				= 10;

	
	// Hopper Constants (Field)
	public static final double DISTANCE_TO_HOPPER			= 140;
	

	
	// Axis Camera constants
	public static final double	 AXIS_FOV_DEGREES 			= 43.5;			// Field of View of the AXIS Camera
	public static final int 	AXIS_IMG_WIDTH 				= 320;			// width of the AXIS image - resolution
	public static final int 	AXIS_IMG_HEIGHT 			= 240;			// height of the AXIS image - resolution
	
	// USB Camera Constants
	public static final double USB_FOV_DEGREES 				= 75;			// Field of View of the USB Camera
	public static final int USB_IMG_WIDTH 					= 320;			// width of the USB image - resolution
	public static final int USB_IMG_HEIGHT 					= 240;			// height of the USB image - resolution
	
	
	// USB Lift Camera Constants
	public static final double 	USB_LIFT_FOV_DEGREES 		= 43.5;			// Field of View of the AXIS Camera
	public static final int 	USB_LIFT_IMG_WIDTH 			= 320;			// width of the USB image - resolution
	public static final int 	USB_LIFT_IMG_HEIGHT 		= 240;			// height of the USB image - resolution

	
	// USB Camera Constants
	public static final double 	USB_BOILER_FOV_DEGREES 			= 43.5;			// Field of View of the AXIS Camera
	public static final int 	USB_BOILER_IMG_WIDTH 			= 320;			// width of the USB image - resolution
	public static final int 	USB_BOILER_IMG_HEIGHT 			= 240;			// height of the USB image - resolution

	public static final String AXIS_IP_ADDRESS				= "10.7.8.12";      // lift -practice - 10.7.8.2
	public static final String AXIS_CAMERA_ID				=  "cam3";			// lift - practice - cam1    comp - 3
	public static final String USB_CAMERA_ID				=  "cam2";			// boiler - practice - cam3   comp - 2
	public static final int USB_VIDEO_PORT					=  0;				// boiler - practice - 0
							
}

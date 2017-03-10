//package org.usfirst.frc.team708.robot.subsystems;
//
//import org.usfirst.frc.team708.robot.AutoConstants;
//
////import org.team708.robot.commands.visionProcessor.ProcessData;
//
//import org.usfirst.frc.team708.robot.Constants;
//import org.usfirst.frc.team708.robot.util.Math708;
//
////import edu.wpi.first.wpilibj.Preferences;
//import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.networktables.NetworkTable;
////import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;
//
///**
// *
// */
//public class VisionProcessor extends Subsystem {
//    
//	private NetworkTable roboRealmInfo;
//// NumberArray deprecated	private NumberArray targetCrosshair;
//	
//	private boolean seesTarget;
//	private boolean isCentered;
//	private boolean isAtY = false;
//	
//	private final double 	imageWidth 	= 320;
//	private final double	imageHeight = 240;
//	private final double 	targetWidth = 10.25; //width of lever in inches
//
//// Gear 11" in diameter
//// Boiler Target is 1'3" wide	9" high		-- 7' off ground to center
//// lever is 10.25" wide			5" high		-- 12.75" off ground to center
//	
//	private double 			centerX 	= 0.0;
//	private double			targetY 	= 100;  //location of target when on hook
//	private double 			currentX 	= 0.0;
//	private double			currentY 	= 0.0;
//
//	private double 		thresholdX = 20;
//	private double 		thresholdY = 20;
//	
//// lift aspect ratio (12.75" / 23")  .55435
//// lift aspect ratio (4" / 23") (.174)
//    private final double targetAspectRatio = .55435; 
//    
//// Distance related measurements from the network table
////   	private double 			distanceToTarget 	= 0.0;
////    	private int 			differencePx 		= 0;
////    	private final double 	targetDiameterIn 	= 24;
//    
//// Data sent from the network table
////    private double currentAspectRatio = 0.0;
////    private double upper_left_x = 0;
////    private double upper_left_y = 0;
////    private double upper_right_x = 0;
////    private double upper_right_y = 0;
////    private double lower_left_x = 0;
////    private double lower_left_y = 0;
////    private double radius = 0;
////    private double blob_count = 0;
//    
////    private double lowerLengthX;
////    private double setProportion;
//    	
////daisy says to set this to 43.5 deg
////    private final double cameraFOVRads = Math.toRadians(47);
////    private double cameraFOVRads = Math.toRadians(43.5);
//
//	double rotate;
//    
//	public VisionProcessor() {
//		super("Vision Processor");
//		roboRealmInfo = NetworkTable.getTable("vision");
//		
//		centerX = imageWidth / 2;
//	}
//	
//	public void processData() {
//		try {
//			currentX= roboRealmInfo.getNumber("lever_x", 0);
//			currentY= roboRealmInfo.getNumber("lever_y", 0);
//			
////			upper_left_x 	= (double) roboRealmInfo.getNumber("p1x");
////          upper_left_y 	= (double) roboRealmInfo.getNumber("p1y");
////          upper_right_x 	= (double)roboRealmInfo.getNumber("p2x");
////          upper_right_y 	= (double)roboRealmInfo.getNumber("p2y");
////          lower_left_x 	= (double) roboRealmInfo.getNumber("p3x");
////          lower_left_y 	= (double) roboRealmInfo.getNumber("p3y");
//			
//			if (currentX > 0) {
//				seesTarget = true;
//			} else {
//				seesTarget = false;
//			}
//			
//            
//		} catch (TableKeyNotDefinedException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public double getRotate() {
//		
//		if (seesTarget) 
//		{
//			
//			double difference = centerX - (currentX);
//			rotate = Math708.getClippedPercentError(currentX, centerX, 0.25, 0.35);
//			
//			if (Math.abs(difference) <= thresholdX) {
//				rotate = 0.0;
//				isCentered = true;
//			}
//			else if (Math.abs(difference) > thresholdX) {
//				isCentered = false;
//			}
//			
//			rotate = difference / centerX;
//			
//			if (rotate > 0.0) {
//				rotate = -rotate;  //reverses the sign to turn left, when target is left
//			}
//		}
//		
//		else {
//			//rotates if not target (default is right) if loses/doesn't have target
//			//let's d0 a sweep	
//			
//			
//		}
//		
//		return rotate;
//	}
//	
//	//Returns how to move to get to target distance (targetAmount = target ratio)
//	
//	public double getMove() {
//		
//		double move = 0.0;
//		double difference = targetY - currentY;
//		
//		if (seesTarget) 
//		{
//			move = Math708.getClippedPercentError(currentY, targetY, 0.25, 0.35); 
//			//Check if target is at correct level within threshold
//			if (difference <= thresholdY) {
//				move = 0.0;
//				isAtY = true;
//			} else {
//				isAtY = false;
//			}
//			
//		} else {
// 			move = 0.0;
//		}
//		
//		return move;
//	}
//	
//	/**
//	 * Returns if the robot sees a container
//	 * @return
//	 */
//	public boolean isHasTarget() {
//		return seesTarget;
//	}
//	
//	public boolean isAtY() {
//		double difference = targetY - currentY;			
//		//Check if target is at correct level within threshold
//		if (Math.abs(difference) <= thresholdY) {
//			isAtY = true;
//		} else {
//			isAtY = false;
//		}
//		return isAtY;
//	}
//	
//	public boolean isCentered() {
//		return isCentered;
//	}
//
//	public void sendToDashboard() {
//		if (Constants.DEBUG) {
//			SmartDashboard.putBoolean("See Target",      isHasTarget());
//			SmartDashboard.putBoolean("Is Centered",     isCentered());
//			SmartDashboard.putBoolean("Is At Y", 	     isAtY());
//			SmartDashboard.putNumber("Current Y", 		 currentY);
//			SmartDashboard.putNumber("Center of Target", currentX);
//			SmartDashboard.putNumber("Rotation", 		 rotate);
////			SmartDashboard.putString("visionProcessor", "called");
//		}
//	}
//
//    public void initDefaultCommand() {
//		if (Constants.DEBUG) {
//		}    	
//    }
//}
//

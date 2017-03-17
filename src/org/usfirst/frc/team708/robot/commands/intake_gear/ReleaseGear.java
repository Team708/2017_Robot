//package org.usfirst.frc.team708.robot.commands.intake_gear;
//
//import org.usfirst.frc.team708.robot.Robot;
//import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTimeOrGear;
//import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//import edu.wpi.first.wpilibj.command.WaitCommand;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
//public class ReleaseGear extends CommandGroup {
//
//	
//	private static final double driveStraightSpeed = 0.4;
//	private static final double driveStraightTime = 2;
//	
//	private static final double turnSpeed = -0.4;
//	private static final double turnDegrees = 90;
//   
//    // Called just before this Command runs the first time
//    protected void initialize() {
////    	Robot.drivetrain.resetEncoder();
////    	Robot.drivetrain.resetEncoder2();
////    	Robot.drivetrain.resetGyro();
//
//    }
//	
//    public  ReleaseGear() {
//
////    	addParallel(new Intake_Gear_Down());
//    	addSequential(new Intake_Gear_Out());
//    }
//    
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return false;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//}

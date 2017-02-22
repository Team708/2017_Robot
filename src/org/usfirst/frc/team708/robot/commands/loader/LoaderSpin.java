//package org.usfirst.frc.team708.robot.commands.loader;
//
//import org.usfirst.frc.team708.robot.Constants;
//import org.usfirst.frc.team708.robot.OI;
//import org.usfirst.frc.team708.robot.Robot;
//import org.usfirst.frc.team708.robot.subsystems.Loader;
//import org.usfirst.frc.team708.robot.util.Gamepad;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class LoaderSpin extends Command {
//
//    public LoaderSpin() {
//    	requires(Robot.loader);
//    }
//    
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	if (Robot.loader.spinForward())
//    	{
//    		Robot.loader.manualMove(Constants.LOADER_MOTOR_FORWARD);
//    	}
//    	else
//    	{
//    		Robot.loader.manualMove(Constants.LOADER_MOTOR_REVERSE);
//    	}
//    	
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//    	return(true);
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
////    	Robot.loader.stop(); //runs till you hit the off button
//    	Robot.loader.toggleSpin();
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems are scheduled to run
//    protected void interrupted() {
//    	end();
//    }
//}
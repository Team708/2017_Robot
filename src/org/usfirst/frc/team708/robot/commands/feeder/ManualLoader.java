package org.usfirst.frc.team708.robot.commands.feeder;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualLoader extends Command {


    public ManualLoader() {
    	requires(Robot.feeder);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	boolean Apressed = OI.operatorGamepad.getButton(Gamepad.button_A);
    	boolean Xpressed = OI.operatorGamepad.getButton(Gamepad.button_X);
    	boolean Ypressed = OI.operatorGamepad.getButton(Gamepad.button_Y);

//  feeder_IN_BUTTON 	= Gamepad.button_Y;
//  feeder_OUT_BUTTON 	= Gamepad.button_A;
//  feeder_OFF_BUTTON 	= Gamepad.button_X;
    	
    	if (Ypressed == true){
    		Robot.feeder.manualMove(Constants.MOTOR_FORWARD);
    	}
    	else
    	if (Apressed == true){
    		Robot.feeder.manualMove(Constants.MOTOR_REVERSE);
    	}
    	else
    	if (Xpressed == true){
    		Robot.feeder.manualMove(Constants.MOTOR_OFF);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return(false);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.feeder.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}

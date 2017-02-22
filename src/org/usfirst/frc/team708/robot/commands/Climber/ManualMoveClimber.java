package org.usfirst.frc.team708.robot.commands.Climber;

import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.Constants;

import edu.wpi.first.wpilibj.command.Command;
/**
 ** @author James Makovics
 **/
public class ManualMoveClimber extends Command {
	public ManualMoveClimber() {
//        requires(Robot.climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double moveSpeed = OI.operatorGamepad.getAxis(Gamepad.leftStick_Y);
    	
    	//check if joystick axis is in deadzone. Change movespeed to 0 if it is
    	if(moveSpeed <= Constants.AXIS_DEAD_ZONE && moveSpeed >= -Constants.AXIS_DEAD_ZONE){
    		moveSpeed = 0.0;
    	}
    	
    	Robot.climber.manualMove(moveSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}


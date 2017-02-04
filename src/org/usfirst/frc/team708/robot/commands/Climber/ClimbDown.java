package org.usfirst.frc.team708.robot.commands.Climber;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.*;

import edu.wpi.first.wpilibj.command.Command;
/**
 ** @author James Makovics
 **/
public class ClimbDown extends Command{

	public ClimbDown(){
		requires(Robot.climber); //Requires Climber from the IO.Java
	}

	   // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double moveSpeed = OI.operatorGamepad.getAxis(Gamepad.leftStick_Y); //Gets input from operator's controller
    	 Robot.climber.manualMove(moveSpeed); //Defines speed of the motor from the operator's controller

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
    	end();
    }
 }


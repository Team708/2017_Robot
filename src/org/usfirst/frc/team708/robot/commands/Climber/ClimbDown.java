package org.usfirst.frc.team708.robot.commands.Climber;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.*;
import org.usfirst.frc.team708.robot.Constants;

import edu.wpi.first.wpilibj.command.Command;
/**
 ** @author James Makovics
 **/
public class ClimbDown extends Command{

	public ClimbDown(){
//		requires(Robot.climber); //Requires Climber from the IO.Java
	}

	   // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.climber.manualMove(Constants.CLIMB_REVERSE); //Defines move speed from the operator's controller
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    protected void end() {
    	Robot.climber.stop();
    }

    protected void interrupted() {
    	end();
    }
 }


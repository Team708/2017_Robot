package org.usfirst.frc.team708.robot.commands.loader;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.subsystems.Loader;
import org.usfirst.frc.team708.robot.util.Gamepad;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LoaderOff extends Command {

    public LoaderOff() {
    	requires(Robot.loader);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.loader.manualMove(Constants.LOADER_OFF);
    }


	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
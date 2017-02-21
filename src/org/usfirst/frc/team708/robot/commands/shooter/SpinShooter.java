package org.usfirst.frc.team708.robot.commands.shooter;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.OI;

//import org.team708.robot.OI;
//import org.team708.robot.subsystems.Loader;
//import org.team708.robot.util.Gamepad;
//import org.team708.robot.commands.shooter.Fire;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class SpinShooter extends Command {


    public SpinShooter() {
    	requires(Robot.shooter);
    	requires(Robot.drivetrain);
    }
    
// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {   	
   	    Robot.shooter.setFgain(Constants.SHOOTER_F);
   	    if (Robot.drivetrain.getSonarDistance() > 60)
   	    {
//   	    	Robot.shooter.moveHood(Constants.HOOD_GEAR);
	        Robot.shooter.manualRPM(Constants.SHOOTER_MOTOR_SPEED_HIGH);
   	    }
   	    else
   	    {
   	    	Robot.shooter.manualRPM(Constants.SHOOTER_MOTOR_SPEED_LOW);
//   	    	Robot.shooter.moveHood(Constants.HOOD_BUMBER);
   	    }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}

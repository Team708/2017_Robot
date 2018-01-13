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

public class MoveHoodHigh extends Command {

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	/*

    public MoveHoodHigh() {
//    	requires(Robot.shooter);
//    	requires(Robot.feeder);
//    	requires(Robot.drivetrain);
    	}
    
// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.moveHood(Constants.HOOD_LEVER);  // 2000 is upper bounds
    	Robot.shooter.setSpinSpeed(Constants.SHOOTER_MOTOR_SPEED_LEVER);
    }

    // Called repeatedly 50 times/sec
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    	//Robot.shooter.stop();
    }

    protected void interrupted() {
    	end();
    }
    */
}

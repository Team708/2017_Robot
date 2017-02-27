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

public class MoveHoodLow extends Command {


    public MoveHoodLow() {
//   	requires(Robot.shooter);
//    	requires(Robot.feeder);
//    	requires(Robot.drivetrain);
    	}
    
// Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.moveHood(Constants.HOOD_BOILER);  // 25 is the lower bounds
        Robot.shooter.setSpinSpeed(Constants.SHOOTER_MOTOR_SPEED_BOILER);
    }

    // Called repeatedly 50 times/sec when this Command is scheduled to run
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
}

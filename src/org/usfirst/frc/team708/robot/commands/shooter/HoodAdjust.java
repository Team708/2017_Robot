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

public class HoodAdjust extends Command {
    
    public HoodAdjust() {
//    	requires(Robot.shooter);
//    	requires(Robot.feeder);
//    	requires(Robot.drivetrain);
    }
    
// Called just before this Command runs the first time
    protected void initialize() {
//    	double hoodAngle = OI.operatorGamepad.getAxis(Gamepad.rightStick_Y);
//    	Robot.shooter.hoodAdjust(hoodAngle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double hoodAngle = OI.operatorGamepad.getAxis(Gamepad.rightStick_Y);    
    	Robot.shooter.hoodAdjust(hoodAngle);
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

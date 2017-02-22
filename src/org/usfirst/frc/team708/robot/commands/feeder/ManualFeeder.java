package org.usfirst.frc.team708.robot.commands.feeder;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;

import edu.wpi.first.wpilibj.command.Command;

public class ManualFeeder extends Command {


    public ManualFeeder() {
//    	requires(Robot.feeder);
//    	requires(Robot.intake_ball);
//    	requires(Robot.drivetrain);
//    	requires(Robot.shooter);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.feeder.manualMove(Constants.FEEDER_MOTOR_FORWARD);
    	Robot.intake_ball.moveMotor(Constants.INTAKE_FORWARD);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return(false);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.feeder.stop();
    	Robot.intake_ball.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}

package org.usfirst.frc.team708.robot.commands.feeder;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.subsystems.Feeder;
import org.usfirst.frc.team708.robot.subsystems.Intake_Ball;
import org.usfirst.frc.team708.robot.util.Gamepad;

import edu.wpi.first.wpilibj.command.Command;

public class SpinFeeder extends Command {

	private double maxTime;
	
    public SpinFeeder(double maxTime) {
//    	requires(Robot.feeder);
//    	requires(Robot.intake_ball);
//    	requires(Robot.drivetrain);
//    	requires(Robot.shooter);

    	this.setTimeout(maxTime);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.feeder.manualMove(Constants.FEEDER_MOTOR_FORWARD);
//    	Robot.intake_ball.moveMotor(Constants.INTAKE_FORWARD);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	if (timeSinceInitialized() > 1.0){
    		Robot.feeder.manualMove(Constants.FEEDER_MOTOR_FORWARD);
    		Robot.intake_ball.moveMotor(Constants.INTAKE_FORWARD);
//    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	return(false);
    	return (isTimedOut());

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

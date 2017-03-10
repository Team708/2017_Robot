package org.usfirst.frc.team708.robot.commands.intake_ball;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Intake_Ball_In extends Command {

	private double maxTime;

	public Intake_Ball_In(double maxTime) {		
//		requires(Robot.intake_ball);
		this.setTimeout(maxTime);
	}

	protected void initialize() {
		Robot.intake_ball.moveMotor(Constants.INTAKE_FORWARD);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return(isTimedOut());
	}

	protected void end() {
		Robot.intake_ball.stop();
	}

	protected void interrupted() {
		end();
	}
	
}
	
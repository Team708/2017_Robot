package org.usfirst.frc.team708.robot.commands.intake_gear;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.subsystems.Pivot_Gear;
import org.usfirst.frc.team708.robot.commands.intake_gear.*;


import edu.wpi.first.wpilibj.command.Command;

public class Intake_Gear_In extends Command {

	public Intake_Gear_In() {	
//		requires(Robot.intake_gear);
//		requires(Robot.pivot_gear);
	}

	protected void initialize() {
	}

	protected void execute() {
//		if (!Robot.intake_gear.hasGear())
		    Robot.intake_gear.moveMotor(Constants.GEAR_IN);
//		else
//		{
//			Robot.intake_gear.stop();
//			Robot.pivot_gear.moveMotor(Constants.GEAR_UP);
//		}
	}

	protected boolean isFinished() {
		return(false);
	}

	protected void end() {
			Robot.intake_gear.stop();
//			Robot.pivot_gear.stop();
	}

	protected void interrupted() {
		end();
	}
	
}
	
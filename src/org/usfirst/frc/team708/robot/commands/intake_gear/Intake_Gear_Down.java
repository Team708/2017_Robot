package org.usfirst.frc.team708.robot.commands.intake_gear;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Intake_Gear_Down extends Command {

public Intake_Gear_Up() {
		
		requires(Robot.intake_gear);
	}

	protected void initialize() {

	}

	protected void execute() {

		Robot.intake_gear.moveMotor(Constants.INTAKE_GEAR_DOWN);
	}


	protected boolean isFinished() {

		return(false);
	}

	protected void end() {
	
		Robot.intake_gear.stop();
	
	}

	protected void interrupted() {

		end();
	}
	
}
	


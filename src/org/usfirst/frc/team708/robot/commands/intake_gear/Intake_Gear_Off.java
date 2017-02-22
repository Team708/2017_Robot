package org.usfirst.frc.team708.robot.commands.intake_gear;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;

import edu.wpi.first.wpilibj.command.Command;

public class Intake_Gear_Off extends Command {
    
public Intake_Gear_Off() {
	
	requires(Robot.intake_gear);
}

protected void initialize() {

}

protected void execute() {
		Robot.intake_gear.stop();
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

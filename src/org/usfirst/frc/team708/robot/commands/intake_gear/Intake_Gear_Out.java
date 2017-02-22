package org.usfirst.frc.team708.robot.commands.intake_gear;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;

import edu.wpi.first.wpilibj.command.Command;

public class Intake_Gear_Out extends Command {  
	
	public Intake_Gear_Out() {		
//		requires(Robot.intake_gear);
	   	this.setTimeout(.1);
	}
	
	protected void initialize() {
		Robot.intake_gear.moveMotor(Constants.GEAR_OUT);
	}
	
	protected void execute() {
	}
	
	protected boolean isFinished() {
		return(isTimedOut());
	}
	
	protected void end() {
		Robot.intake_gear.stop();
	}
	
	protected void interrupted() {
		end();
	}
}

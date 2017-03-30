package org.usfirst.frc.team708.robot.commands.intake_gear;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;

import edu.wpi.first.wpilibj.command.Command;

public class Intake_Gear_Out extends Command {  
	
	public Intake_Gear_Out() {		
//		requires(Robot.intake_gear);
	   	this.setTimeout(1.0);
	}
	
	protected void initialize() {
		Robot.intake_gear.moveMotor(-.3);
	}
	
	protected void execute() {
	}
	
	protected boolean isFinished() {
		return(isTimedOut());
//		return false;
	}
	
	protected void end() {
		Robot.intake_gear.stop();
	}
	
	protected void interrupted() {
		end();
	}
}

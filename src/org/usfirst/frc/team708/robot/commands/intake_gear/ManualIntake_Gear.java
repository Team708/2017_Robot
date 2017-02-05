package org.usfirst.frc.team708.robot.commands.intake_gear;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;
import edu.wpi.first.wpilibj.command.Command;


public class ManualIntake_Gear extends Command {

	 public ManualIntake_Gear() {
	    	requires(Robot.intake_gear);
	    }
	    

	    // Called just before this Command runs the first time
	    protected void initialize() {

	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	
	    	boolean L_Shoulderpressed = OI.driverGamepad.getButton(Gamepad.button_L_Shoulder);

	//  LOADER_IN_BUTTON 	= Gamepad.Button_L_Shoulder;
	//  LOADER_OUT_BUTTON 	= Gamepad.shoulderAxisLeft;
	    	
	    	if (L_Shoulderpressed == true){
	    		Robot.intake_gear.moveMotor(Constants.INTAKE_FORWARD);
	    	}
	    	else if (OI.driverGamepad.getAxis(Gamepad.shoulderAxisLeft) != 0.0){
	    		Robot.intake_gear.moveMotor(Constants.INTAKE_REVERSE);
	    	}
	    	else {
	    		Robot.intake_gear.moveMotor(Constants.INTAKE_OFF);
	    	}
	    	
	    }

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	return(false);
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    	Robot.intake_gear.stop();
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems are scheduled to run
	    protected void interrupted() {
	    	end();
	    }
}

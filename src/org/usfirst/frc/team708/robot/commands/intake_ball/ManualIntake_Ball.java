package org.usfirst.frc.team708.robot.commands.intake_ball;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;
import edu.wpi.first.wpilibj.command.Command;


public class ManualIntake_Ball extends Command {

	private boolean isdone = false;
	
	 public ManualIntake_Ball() {
	    }
	    
	    // Called just before this Command runs the first time
	    protected void initialize() {
	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {	
	    	boolean R_Shoulderpressed = OI.driverGamepad.getButton(Gamepad.button_R_Shoulder);
	    	
	    	if (R_Shoulderpressed == true){
	    		Robot.intake_ball.moveMotor(Constants.INTAKE_FORWARD);
	    		isdone = false;
	    	}
	    	else
	    	if (OI.driverGamepad.getAxis(Gamepad.shoulderAxisRight) >= Constants.AXIS_DEAD_ZONE){
	    		Robot.intake_ball.moveMotor(Constants.INTAKE_REVERSE);
	    		isdone = false;
	    	}
	    	else {
	    		Robot.intake_ball.moveMotor(Constants.INTAKE_OFF);
	    		isdone = true;
	    	}
	    	
	    }

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	return(false || isdone);
//	    	return(false);
	    }

	    // Called once after isFinished returns true
	    protected void end() {
	    	Robot.intake_ball.stop();
	    }

	    // Called when another command which requires one or more of the same
	    // subsystems are scheduled to run
	    protected void interrupted() {
	    	end();
	    }
}

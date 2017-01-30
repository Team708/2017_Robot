package org.usfirst.frc.team708.robot.commands.intake_ball;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;
import edu.wpi.first.wpilibj.command.Command;


public class ManualIntake_Ball extends Command {

	 public ManualIntake_Ball() {
	    	requires(Robot.loader);
	    }
	    

	    // Called just before this Command runs the first time
	    protected void initialize() {

	    }

	    // Called repeatedly when this Command is scheduled to run
	    protected void execute() {
	    	
	    	boolean R_Shoulderpressed = OI.operatorGamepad.getButton(Gamepad.button_R_Shoulder);
	    	boolean AxisRightpressed = OI.operatorGamepad.getButton(Gamepad.shoulderAxisRight);

	//  LOADER_IN_BUTTON 	= Gamepad.Button_R_Shoulder;
	//  LOADER_OUT_BUTTON 	= Gamepad.shoulderAxisRight;
	    	
	    	if (R_Shoulderpressed == true){
	    		Robot.intake_ball.moveMotor(Constants.INTAKE_FORWARD);
	    	}
	    	else
	    	if (AxisRightpressed == true){
	    		Robot.intake_ball.moveMotor(Constants.INTAKE_REVERSE);
	    	}
	    	else {
	    		Robot.intake_ball.moveMotor(Constants.INTAKE_OFF);
	    	}
	    	
	    }

	    // Make this return true when this Command no longer needs to run execute()
	    protected boolean isFinished() {
	    	return(false);
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

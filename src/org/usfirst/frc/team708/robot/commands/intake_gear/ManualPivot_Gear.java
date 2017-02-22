//package org.usfirst.frc.team708.robot.commands.intake_gear;
//
//import org.usfirst.frc.team708.robot.Constants;
//import org.usfirst.frc.team708.robot.OI;
//import org.usfirst.frc.team708.robot.Robot;
//import org.usfirst.frc.team708.robot.util.Gamepad;
//import edu.wpi.first.wpilibj.command.Command;
//
//
//public class ManualPivot_Gear extends Command {
//
//	 public ManualPivot_Gear() {
//	    	requires(Robot.pivot_gear);
//	    }
//	    
//
//	    // Called just before this Command runs the first time
//	    protected void initialize() {
//
//	    }
//
//	    // Called repeatedly when this Command is scheduled to run
//	    protected void execute() {
//	    	
//	    	boolean B_Buttonpressed = OI.driverGamepad.getButton(Gamepad.button_B);
//	    	boolean A_Buttonpressed = OI.driverGamepad.getButton(Gamepad.button_A);
//
//	//  LOADER_IN_BUTTON 	= Gamepad.Button_L_Shoulder;
//	//  LOADER_OUT_BUTTON 	= Gamepad.shoulderAxisLeft;
//	    	
//
//	    	if (B_Buttonpressed == true){
//	    		Robot.pivot_gear.moveMotor(Constants.INTAKE_FORWARD);
//	    	}
//	    	else if (A_Buttonpressed == true){
//	    		Robot.pivot_gear.moveMotor(Constants.INTAKE_REVERSE);
//	    	}
//	    	else {
//	    		Robot.pivot_gear.moveMotor(Constants.INTAKE_OFF);	    		
//	    	}
//	    	
//	    }
//
//	    // Make this return true when this Command no longer needs to run execute()
//	    protected boolean isFinished() {
//	    	return(false);
//	    }
//
//	    // Called once after isFinished returns true
//	    protected void end() {
//	    	Robot.pivot_gear.stop();
//	    }
//
//	    protected void interrupted() {
//	    	end();
//	    }
//}

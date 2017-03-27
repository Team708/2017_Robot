package org.usfirst.frc.team708.robot.commands.intake_gear;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.OI;

//import org.team708.robot.OI;
//import org.team708.robot.subsystems.Loader;
//import org.team708.robot.util.Gamepad;
//import org.team708.robot.commands.shooter.Fire;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GearAdjust extends Command {
    
    public GearAdjust() {
//    	requires(Robot.intake_gear);
    }
    
// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double gearAngle = OI.operatorGamepad.getAxis(Gamepad.leftStick_Y); //Gets Input from operator's controller
//    	double gearAnglex = OI.operatorGamepad.getAxis(Gamepad.leftStick_X); //Gets Input from operator's controller

//    	if ((gearAnglex>0))
//		{
//		    Robot.intake_gear.moveMotor(Constants.GEAR_IN);
//		}
//		else if (gearAnglex<0)
//		{
//			Robot.intake_gear.moveMotor(Constants.GEAR_OUT);
//		}
    	
    	if ((gearAngle >0) 
 //   			&& (!Robot.pivot_gear.isFwdSwitch())
    			)
    		Robot.pivot_gear.moveMotor(.8);
    	else if ((gearAngle <0) 
    			&& (!Robot.pivot_gear.isRevSwitch())
    			)
    		Robot.pivot_gear.moveMotor(-.8); //Defines move speed from the operator's controller
    }

    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pivot_gear.stop();
//    	Robot.intake_gear.stop();

    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}

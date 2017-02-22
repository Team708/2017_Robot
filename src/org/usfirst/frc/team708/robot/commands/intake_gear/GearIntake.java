package org.usfirst.frc.team708.robot.commands.intake_gear;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.OI;

import edu.wpi.first.wpilibj.command.Command;

public class GearIntake extends Command {
    
    public GearIntake() {
//    	requires(Robot.intake_gear);
    }
    
// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double gearAngle = OI.operatorGamepad.getAxis(Gamepad.leftStick_X); //Gets Input from operator's controller
 
///    	Robot.intake_gear.moveMotor(gearAngle); //Defines move speed from the operator's controller
    	
		if ((!Robot.intake_gear.hasGear()) && (gearAngle>0))
		    Robot.intake_gear.moveMotor(Constants.GEAR_IN);
		else if (gearAngle<0)
			Robot.intake_gear.moveMotor(Constants.GEAR_OUT);
		else
		{
			Robot.intake_gear.stop();
			Robot.pivot_gear.moveMotor(Constants.GEAR_UP);
		}
	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake_gear.stop();
    	Robot.pivot_gear.stop();
    }

    protected void interrupted() {
    	end();
    }
}

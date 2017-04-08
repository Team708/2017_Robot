package org.usfirst.frc.team708.robot.commands.intake_gear;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTimeOrGear;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ReleaseGear extends CommandGroup {
   
    protected void initialize() {
	   	this.setTimeout(1.0);
    }
	
    public  ReleaseGear() {

    //  place gear on lever and back away 
	   	addSequential(new Intake_Gear_Out());
    	addParallel(new Intake_Gear_Down());

    // get off lever and go for some balls
    	addSequential(new DriveStraightToEncoderDistanceOrTime(10, .4, true, 1));
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return(isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

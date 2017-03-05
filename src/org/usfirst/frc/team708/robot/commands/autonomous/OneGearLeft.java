package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.commands.intake_gear.ReleaseGear;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.ToggleBrakeMode;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegreesAlliance;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Down;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Off;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OneGearLeft extends CommandGroup {
   
    protected void initialize() {
    }
	
    public  OneGearLeft() {
    	addSequential(new DriveStraightToEncoderDistance(80, .3, false));
    	addSequential(new TurnToDegreesAlliance(.5, 40, Constants.CLOCKWISE));
    	addSequential(new DriveStraightToEncoderDistance(6, .3, false));

    	addSequential(new WaitCommand(1.0));
    	addSequential(new RotateAndDriveToLift());
    	
//    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));
    	
    	addParallel(new Intake_Gear_Out());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new Intake_Gear_Off());  	
     	addSequential(new DriveStraightToEncoderDistance(25, .4, true));
//    	addSequential(new Intake_Gear_Out());
    	addSequential(new DriveStraightToEncoderDistance(5, .4, true));
    	
//
//    	addSequential(new DriveStraightToEncoderDistance(75, .4, false));
//    	addSequential(new TurnToDegreesAlliance(.5, 45, Constants.CLOCKWISE));
//    	addSequential(new RotateAndDriveToLift());
//
////    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));
//
//    	addParallel(new Intake_Gear_Down());
//    	addSequential(new DriveStraightToEncoderDistance(5, .3, true));
//    	
////    	addSequential(new Intake_Gear_Out());
//    	addSequential(new DriveStraightToEncoderDistance(5, .4, true));
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.ToggleBrakeMode;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegreesAlliance;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Off;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
//import org.usfirst.frc.team708.robot.commands.intake_gear.AquireGear;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OneGearCenter extends CommandGroup {
   
    protected void initialize() {
//    	Robot.drivetrain.resetEncoder();
//    	Robot.drivetrain.resetEncoder2();
//    	Robot.drivetrain.resetGyro();
    }
	
    public  OneGearCenter() {

//    	addSequential(new WaitCommand(1.0));
    	addSequential(new DriveStraightToEncoderDistance(55, .4, false));
    	
    	addSequential(new RotateAndDriveToLift());
    	
//    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));
    	
    	addSequential(new Intake_Gear_Out());
    	
    	addSequential(new DriveStraightToEncoderDistance(50, .4, true));
    	
 //this is a test replace with 3 gear
//    	addSequential(new TurnToDegreesAlliance(.6, 45));  //add alliance direction
//    	addSequential(new DriveStraightToEncoderDistance(110, .4, false));
    	
// get gear 2
//    	addSequential(new TurnToDegreesAlliance(.6, 135));
//    	addSequential(new RotateAndDriveToGear());
//    	addSequential(new AcquireGear());
//    	addSequential(new TurnToDegreesAlliance(.6, -135));
//    	addSequential(new DriveStraightToEncoderDistance(40, .4, false));
//    	addSequential(new RotateAndDriveToLift());
//    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));
//    	addSequential(new Intake_Gear_Out());
//    	addSequential(new DriveStraightToEncoderDistance(50, .4, true));
// get gear 3
//    	addSequential(new TurnToDegreesAlliance(.6, -135));
//    	ddSequential(new RotateAndDriveToGear());
//    	addSequential(new AcquireGear());
//    	addSequential(new TurnToDegreesAlliance(.6, 135));
//    	addSequential(new DriveStraightToEncoderDistance(40, .4, false));
//    	addSequential(new RotateAndDriveToLift());
//    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));
//    	addSequential(new Intake_Gear_Out());
//    	addSequential(new DriveStraightToEncoderDistance(50, .4, true));
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

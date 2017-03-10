package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToBoiler;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
//import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToGear;
import org.usfirst.frc.team708.robot.commands.drivetrain.ToggleBrakeMode;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegreesAlliance;
import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Off;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
import org.usfirst.frc.team708.robot.commands.intake_gear.ReleaseGear;
import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
//import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;
import org.usfirst.frc.team708.robot.commands.shooter.StopShooter;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Down;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_In;
import org.usfirst.frc.team708.robot.commands.intake_gear.AquireGear;

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

 // go to lever
    	addSequential(new DriveStraightToEncoderDistance(24, .3, false));

//  target lever
    	addSequential(new WaitCommand(1.0));
    	addSequential(new RotateAndDriveToLift());
    	
//  place gear on lever and back away    	
    	addSequential(new WaitCommand(0.5));
//    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));

    	addParallel(new Intake_Gear_Out());
//    	addSequential(new WaitCommand(0.5));
//    	addSequential(new Intake_Gear_Off());
    	addParallel(new Intake_Gear_Down());
//    	addSequential(new DriveStraightToEncoderDistance(5, .3, true));  put this back in!!!!
    	addSequential(new DriveStraightForTime(.3, 2));

// get off lever and go for some balls
//    	addSequential(new DriveStraightToEncoderDistance(10, .3, true));  put this back in!!!!    	

// turn toward boiler
    	addSequential(new TurnToDegreesAlliance(.4, 50, Constants.COUNTERCLOCKWISE));

// target Boiler
    	addSequential(new WaitCommand(1.0));
    	addSequential(new SetLED(Constants.SET_TARGETING));
    	addSequential(new RotateAndDriveToBoiler(AutoConstants.DISTANCE_TO_BOILER_LOCATION2));

// unload balls
		addParallel(new SpinShooter(8));
		addSequential(new SpinFeeder(6));  	
    	
//this is a test replace with 3 gear
//    	addSequential(new TurnToDegreesAlliance(.6, 45));  //add alliance direction
//    	addSequential(new DriveStraightToEncoderDistance(110, .4, false));
    	
// get gear 2
//    	addSequential(new TurnToDegreesAlliance(.6, 125, Constants.CLOCKWISE));
//    	addSequential(new Intake_Gear_Down());
//
//    	addSequential(new SetLED(Constants.SET_HAS_GEAR_TARGETING));
//    	addSequential(new WaitCommand(1.0));
//    	addParallel(new Intake_Gear_In());
//    	addParallel(new RotateAndDriveToGear());
//    	addSequential(new AquireGear());
//    	
//    	addSequential(new TurnToDegreesAlliance(.6, 120, Constants.COUNTERCLOCKWISE));
//    	addSequential(new RotateAndDriveToLift());
//    	
////    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));
    	
//    	addParallel(new Intake_Gear_Down());
//    	addSequential(new Intake_Gear_Out());
//    	addParallel(new Intake_Gear_Out());
//    	addSequential(new WaitCommand(0.5));
//    	addSequential(new Intake_Gear_Off());
//    	
//    	addSequential(new DriveStraightToEncoderDistance(5, .3, true));
//    	addSequential(new DriveStraightToEncoderDistance(45, .4, true));
//    	
// get gear 3
//    	addSequential(new TurnToDegreesAlliance(.6, 120, Constants.CLOCKWISE));
//    	addSequential(new Intake_Gear_Down());
//
//    	addSequential(new SetLED(Constants.SET_HAS_GEAR_TARGETING));
//
//    	addParallel(new Intake_Gear_In());
//    	addParallel(new RotateAndDriveToGear());
//    	addSequential(new AquireGear());
//   	
//    	addSequential(new TurnToDegreesAlliance(.6, 120, Constants.COUNTERCLOCKWISE));
//    	addSequential(new RotateAndDriveToLift());
//    	
////    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));
//    	
//    	addParallel(new Intake_Gear_Out());
//    	addSequential(new WaitCommand(0.5));
//    	addSequential(new Intake_Gear_Off());
//    	
//    	addSequential(new DriveStraightToEncoderDistance(5, .4, true));
//    	addSequential(new Intake_Gear_Out());
//    	addSequential(new DriveStraightToEncoderDistance(12, .4, true));
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

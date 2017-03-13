package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.commands.intake_gear.ReleaseGear;
import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;
import org.usfirst.frc.team708.robot.commands.shooter.StopShooter;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToBoiler;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.ToggleBrakeMode;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegreesAlliance;
import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
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
// go to lever
    	addSequential(new DriveStraightToEncoderDistance(80, .3, false));
//    	addSequential(new DriveStraightForTime(-.3, 3.5));
    	addSequential(new TurnToDegreesAlliance(.5, 45, Constants.CLOCKWISE));
    	
//    	addSequential(new DriveStraightForTime(-.3, .5));
//    	addSequential(new DriveStraightToEncoderDistance(10, .3, false));

//  target lever
    	addSequential(new WaitCommand(1.0));
    	addSequential(new RotateAndDriveToLift());
    	
//  place gear on lever and back away    	
//    	addSequential(new WaitCommand(0.5));
    	addSequential(new Intake_Gear_Out());
    	addParallel(new Intake_Gear_Down());
    	
//    	addSequential(new DriveStraightForTime(.3, .5));
//    	addSequential(new DriveStraightToEncoderDistance(5, .3, true));
    	
// get off lever and go for some balls
//    	addSequential(new DriveStraightForTime(.3, 1));
    	addSequential(new DriveStraightToEncoderDistance(42, .4, true));
    	
// turn toward boiler
    	addSequential(new TurnToDegreesAlliance(.5, 90, Constants.COUNTERCLOCKWISE));
    	
//    	addSequential(new DriveStraightForTime(.3, 2.5));
    	addSequential(new DriveStraightToEncoderDistance(50, .4, true));
    	addSequential(new TurnToDegreesAlliance(.5, 30, Constants.COUNTERCLOCKWISE));


// target Boiler
    	addSequential(new WaitCommand(1.0));
    	addSequential(new SetLED(Constants.SET_TARGETING));
    	addSequential(new RotateAndDriveToBoiler(AutoConstants.DISTANCE_TO_BOILER_LOCATION2));

// unload balls
		addParallel(new SpinShooter(9));
		addSequential(new SpinFeeder(7));   
		addSequential(new StopShooter());    	
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

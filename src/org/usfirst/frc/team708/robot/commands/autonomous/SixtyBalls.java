package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToBoiler;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegreesAlliance;
import org.usfirst.frc.team708.robot.commands.feeder.FeederOff;
import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
import org.usfirst.frc.team708.robot.commands.intake_ball.Intake_Ball_In;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Down;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Off;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
//import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
import org.usfirst.frc.team708.robot.commands.shooter.StopShooter;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SixtyBalls extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  SixtyBalls() {  	
// goto Hopper
    	addSequential(new DriveStraightToEncoderDistance(100, .4, false));
    	addSequential(new TurnToDegreesAlliance(.4, 80, Constants.COUNTERCLOCKWISE));
    	addSequential(new DriveStraightToEncoderDistance(25, .5, true));
    	addSequential(new Intake_Ball_In(4));
    	
// back off hopper and turn toward boiler		
		addSequential(new DriveStraightToEncoderDistance(25, .5, false));
    	addSequential(new TurnToDegreesAlliance(.4, 45, -1));

//    	addSequential(new DriveStraightToEncoderDistance(40, .4, true));
    	
// target Boiler
    	addSequential(new WaitCommand(1.0));
    	addSequential(new SetLED(Constants.SET_TARGETING));
    	addSequential(new RotateAndDriveToBoiler(AutoConstants.DISTANCE_TO_BOILER_LOCATION2));

// unload balls
		addParallel(new SpinShooter(8));
		addSequential(new SpinFeeder(6));
		
// go to lever
    	addSequential(new TurnToDegreesAlliance(.4, 20, Constants.COUNTERCLOCKWISE));

//  target lever
    	addSequential(new WaitCommand(1.0));
    	addSequential(new RotateAndDriveToLift());
    	
//  place gear on lever and back away    	
    	addSequential(new WaitCommand(0.5));
    	addParallel(new Intake_Gear_Out());
    	addParallel(new Intake_Gear_Down());
    	addSequential(new DriveStraightToEncoderDistance(5, .3, true));
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

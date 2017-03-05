package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
//import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToBoiler;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegreesAlliance;
import org.usfirst.frc.team708.robot.commands.feeder.FeederOff;
import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Down;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Off;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
import org.usfirst.frc.team708.robot.commands.intake_gear.ReleaseGear;
import org.usfirst.frc.team708.robot.commands.led_out.SetLED;
import org.usfirst.frc.team708.robot.commands.shooter.StopShooter;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TenBalls extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  TenBalls() {  	
    	addSequential(new DriveStraightToEncoderDistance(80, .3, false));
    	addSequential(new TurnToDegreesAlliance(.5, 40, Constants.COUNTERCLOCKWISE));
    	addSequential(new DriveStraightToEncoderDistance(6, .3, false));

    	addSequential(new WaitCommand(1.0));
    	addSequential(new RotateAndDriveToLift());
    	
//    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));
    	
    	addParallel(new Intake_Gear_Out());
    	addSequential(new WaitCommand(0.5));
    	addSequential(new Intake_Gear_Off());  	
    	
    	addSequential(new DriveStraightToEncoderDistance(25, .4, true));
//    	addSequential(new Intake_Gear_Out());
    	
    	
//        addSequential(new DriveStraightToEncoderDistance(75, .4, false));
//    	addSequential(new TurnToDegreesAlliance(.5, 45, Constants.COUNTERCLOCKWISE));
//    	addSequential(new RotateAndDriveToLift());
//    	
////    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));
//
//    	addParallel(new Intake_Gear_Down());
//    	addParallel(new Intake_Gear_Out());
//    	addSequential(new WaitCommand(1.0));
//    	addSequential(new DriveStraightToEncoderDistance(5, .3, true));
//    	addParallel(new Intake_Gear_Down());
//    	addSequential(new DriveStraightToEncoderDistance(5, .3, true));
////    	addSequential(new Intake_Gear_Out());
//    	addSequential(new DriveStraightToEncoderDistance(5, .4, true));
    	
//    	addSequential(new DriveStraightToEncoderDistance(85, .4, false));  //55, .4, false
//    	addSequential(new TurnToDegreesAlliance(.6, -45));
    	
    	addSequential(new WaitCommand(1.0));
    	addSequential(new SetLED(Constants.SET_TARGETING));
    	addSequential(new RotateAndDriveToBoiler(AutoConstants.DISTANCE_TO_BOILER_LOCATION1));

    	addSequential(new DriveStraightToEncoderDistanceOrTime(50, .3, true, 3));
    	addSequential(new WaitCommand(1.0));

		addParallel(new SpinShooter(8));
		addSequential(new SpinFeeder(6));
		
//		addSequential(new WaitCommand(10));
//		addSequential(new FeederOff());
		
		addSequential(new StopShooter());
    	addSequential(new DriveStraightToEncoderDistance(12, .4, true));
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

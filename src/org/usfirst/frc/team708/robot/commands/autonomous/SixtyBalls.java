package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToBoiler;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegreesAlliance;
import org.usfirst.frc.team708.robot.commands.feeder.FeederOff;
import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Off;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
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
    	addSequential(new DriveStraightToEncoderDistance(100, .4, false));  //55, .4, false
    	addSequential(new TurnToDegreesAlliance(.6, 75, 1));
    	addSequential(new DriveStraightToEncoderDistance(25, .5, true));
		addSequential(new WaitCommand(4));
    	addSequential(new DriveStraightToEncoderDistance(25, .5, false));
    	addSequential(new TurnToDegreesAlliance(.6, 90, -1));
    	addSequential(new DriveStraightToEncoderDistance(40, .4, true));  //55, .4, false

    	addSequential(new TurnToDegreesAlliance(.6, 45, 1));
     	addSequential(new RotateAndDriveToBoiler(10));
		addSequential(new WaitCommand(4));

    	addSequential(new SpinShooter());
		addSequential(new WaitCommand(1.0));
		addParallel(new SpinFeeder());
		addSequential(new WaitCommand(5));
		addSequential(new FeederOff());
		addSequential(new StopShooter());
		
    	addSequential(new TurnToDegreesAlliance(.6, 80, -1));
    	addSequential(new DriveStraightToEncoderDistance(50, .4, false));
    	addSequential(new RotateAndDriveToLift());
//    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));
    	addSequential(new Intake_Gear_Out());
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

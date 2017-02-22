package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.ToggleBrakeMode;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Off;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OneGearLeft extends CommandGroup {

	
	private static final double driveStraightSpeed = 0.4;
	private static final double driveStraightTime = 2;
	
	private static final double turnSpeed = -0.4;
	private static final double turnDegrees = 90;
   
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoder();
    	Robot.drivetrain.resetEncoder2();
    	Robot.drivetrain.resetGyro();

    }
	
    public  OneGearLeft() {

    	addSequential(new WaitCommand(1.0));
    	addSequential(new DriveStraightToEncoderDistance(-70, .4));
    	addSequential(new TurnToDegrees(-45,-0.4));

    	addSequential(new RotateAndDriveToLift());
    	addSequential(new DriveStraightToEncoderDistance(-6, .2));
    	addSequential(new Intake_Gear_Out());
    	addSequential(new WaitCommand(.1));
    	addSequential(new Intake_Gear_Off());
    	addSequential(new DriveStraightToEncoderDistance(12, .2));
    	addSequential(new TurnToDegrees(45, 0.4));
    	addSequential(new DriveStraightToEncoderDistance(90, .4));

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

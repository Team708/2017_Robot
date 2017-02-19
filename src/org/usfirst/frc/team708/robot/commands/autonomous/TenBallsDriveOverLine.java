package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToBoiler;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.feeder.FeederOff;
import org.usfirst.frc.team708.robot.commands.feeder.SpinFeeder;
import org.usfirst.frc.team708.robot.commands.shooter.ShooterOff;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TenBallsDriveOverLine extends CommandGroup {

	private static final double driveStraightSpeed = 0.4;
	private static final double driveStraightTime = 2;
	
	private static final double turnSpeed = 0.4;
	private static final double turnRight = 90;
	private static final double turnLeft = -90;
   
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoder();
    	Robot.drivetrain.resetEncoder2();
    	Robot.drivetrain.resetGyro();

    }
	
    public  TenBallsDriveOverLine() {  	
    	addSequential(new DriveStraightToEncoderDistance(72));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new TurnToDegrees(turnSpeed, turnRight));
    	addSequential(new RotateAndDriveToBoiler(40.0));
    	addSequential(new SpinShooter());
		addSequential(new WaitCommand(AutoConstants.SHOOTER_MOTOR_SPINUP_TIME));
		addParallel(new SpinFeeder());
		addSequential(new WaitCommand(AutoConstants.SHOOTING_TIME));
		addSequential(new FeederOff());
		addSequential(new ShooterOff());
		addSequential(new TurnToDegrees(turnSpeed, -110));
		addSequential(new DriveStraightToEncoderDistance(112));
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

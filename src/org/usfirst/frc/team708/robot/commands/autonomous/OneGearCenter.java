package org.usfirst.frc.team708.robot.commands.autonomous;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
import org.usfirst.frc.team708.robot.subsystems.*;


import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class OneGearCenter extends CommandGroup {

	private static final double driveStraightSpeed = -0.4;
	private static final double driveStraightDistance = 24;
	private static final double driveStraightSpeedReverse = 0.4;
	
	private static final double turnSpeed = 0.4;
	private static final double turnDegrees = 70; //Right is positive and Left is negative
   
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoder();
    	Robot.drivetrain.resetEncoder2();
    	Robot.drivetrain.resetGyro();

    }
	
    public  OneGearCenter() {
    	
    	addSequential(new WaitCommand(0.1));
    	addSequential(new DriveStraightToEncoderDistance(driveStraightDistance, driveStraightSpeed));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new RotateAndDriveToLift(12));
    	addSequential(new WaitCommand(0.1));
    	addSequential(new Intake_Gear_Out());
    	addSequential(new WaitCommand(0.1));
    	addSequential(new DriveStraightToEncoderDistance(driveStraightDistance, driveStraightSpeedReverse));
    	addSequential(new WaitCommand(0.1));
    	

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


package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistance;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToLift;
import org.usfirst.frc.team708.robot.commands.drivetrain.ToggleBrakeMode;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Off;
import org.usfirst.frc.team708.robot.commands.intake_gear.Intake_Gear_Out;
import org.usfirst.frc.team708.robot.commands.drivetrain.RotateAndDriveToGear;
import org.usfirst.frc.team708.robot.commands.intake_gear.AquireGear;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OneGearCenter extends CommandGroup {

	
	private static final double driveStraightSpeed = 0.4;
	private static final double driveStraightTime = 2;
	
	private static final double turnSpeed = -0.4;
	private static final double turnDegrees = 90;
   
    // Called just before this Command runs the first time
    protected void initialize() {
//    	Robot.drivetrain.resetEncoder();
//    	Robot.drivetrain.resetEncoder2();
//    	Robot.drivetrain.resetGyro();

    }
	
    public  OneGearCenter() {

////    	addSequential(new WaitCommand(1.0));
//    	addSequential(new DriveStraightToEncoderDistance(55, .4, false));
//    	addSequential(new RotateAndDriveToLift());
//    	
////    	addSequential(new WaitCommand(4));
//
//    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));
//    	addSequential(new Intake_Gear_Out());
// //   	addSequential(new WaitCommand(.1));
// //   	addSequential(new Intake_Gear_Off());
//    	
//    	addSequential(new DriveStraightToEncoderDistance(50, .4, true));
//    	addSequential(new TurnToDegrees(.6, 130));
    	
    	addSequential(new RotateAndDriveToGear());
    	
    	addSequential(new WaitCommand(4));
    	
    	addSequential(new AquireGear());
 //   	addSequential(new TurnToDegrees(-.6, -135));   	
    	
    	
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

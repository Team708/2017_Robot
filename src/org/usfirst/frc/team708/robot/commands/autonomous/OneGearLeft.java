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
   
    protected void initialize() {
    }
	
    public  OneGearLeft() {

    	addSequential(new DriveStraightToEncoderDistance(70, .4, false));

    	addSequential(new TurnToDegrees(-Robot.allianceColor*45,-Robot.allianceColor*0.6));

    	addSequential(new RotateAndDriveToLift());

    	addSequential(new DriveStraightToEncoderDistance(6, .4, false));

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

package org.usfirst.frc.team708.robot.commands.drivetrain;

import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToDegreesAlliance extends Command {
	
	private double rotationSpeed;
	private double goalDegrees;

	/**
	 * Constructor
	 * @param rotationSpeed
	 * @param goalDegrees
	 */
    public TurnToDegreesAlliance(double rotationSpeed, double goalDegrees) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        
        this.rotationSpeed = rotationSpeed;
        this.goalDegrees = goalDegrees;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    	goalDegrees = goalDegrees * Robot.allianceColor;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Color = " + SmartDashboard.getInt("AllianceColor"));
    	System.out.println("GoalDegress = " + goalDegrees);
//    	SmartDashboard.putNumber("gaolDegress", goalDegrees);
    	
    	if (goalDegrees >= 0) {
    		Robot.drivetrain.haloDrive(0.0, -rotationSpeed, false);
    	} else {
    		Robot.drivetrain.haloDrive(0.0, rotationSpeed, false);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (goalDegrees >= 0) {
    		return (Robot.drivetrain.getAngle() >= goalDegrees);
    	} else {
    		return (Robot.drivetrain.getAngle() <= goalDegrees);
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

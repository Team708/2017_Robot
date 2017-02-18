package org.usfirst.frc.team708.robot.commands.drivetrain;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;



/**
 *RotateAndDriveToGear
 * this command will utilize the vision data to drive the robot to the center of the gear
 * and stop when it is at the gear stop at target distance
 */
public class RotateAndDriveToGear extends Command {
	
	private double		moveSpeed;
	private double		rotate;
	/**
	 * Constructor
	 * @param targetDistance - the distance to stop in front of the target
	 */
    public RotateAndDriveToGear() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        requires(Robot.visionLift);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.visionLift.putIsCentered(false);
    	Robot.visionLift.putHasTarget(false);
    	Robot.visionLift.putAtDistance(false);
    	Robot.visionLift.putCounter(0);
    	Robot.visionLift.putCurrentCenter(0);
    	Robot.visionLift.putDistanceToStop(AutoConstants.DISTANCE_TO_GEAR);
    	Robot.visionLift.putVisionType(AutoConstants.GEAR);

    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	Robot.visionLift.processData();
    	rotate = Robot.visionLift.getRotate();  
    	moveSpeed = Robot.visionLift.getMove();   // was + made -

 
    	Robot.drivetrain.haloDrive(moveSpeed, rotate, false);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.visionLift.getCounter() >= AutoConstants.SWEEP3_MAX){
    		
    		return true;
    	}
    	//Check if the sonar distance is less then the target Distance, end
//    	if (Robot.drivetrain.getSonarDistance() < targetDistance  && Robot.visionProcessor.wasCentered()){
//     	if (Robot.visionProcessor.isAtY() && Robot.visionProcessor.wasCentered()){
        if (Robot.visionLift.isAtDistance() && Robot.visionLift.isCentered()){
         		     		return true;
    	}
//    	else if (Robot.drivetrain.getSonarDistance() < targetDistance && Robot.visionProcessor.isHasTarget()) {
    	else if (Robot.visionLift.isAtDistance() && Robot.visionLift.isHasTarget()) {
    		return false;
    	}
    	
    	return false;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    	Robot.visionLift.putCounter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

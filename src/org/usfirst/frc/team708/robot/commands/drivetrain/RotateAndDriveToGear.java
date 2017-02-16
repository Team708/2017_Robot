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
// VIET ARE WE STILL USING THE TARGET DISTANCE HERE - I THINK WE ARE ACTUALLY CALCULATING IT IN THE SUBSYSTEM
    public RotateAndDriveToGear(double targetDistance) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        requires(Robot.visionGear);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.visionGear.putIsCentered(false);
    	Robot.visionGear.putHasTarget(false);
    	Robot.visionGear.putAtDistance(false);
    	Robot.visionGear.putCounter(0);
    	Robot.visionGear.putCurrentCenter(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	Robot.visionGear.processData();
    	rotate = Robot.visionGear.getRotate();  
    	moveSpeed = Robot.visionGear.getMove();   // was + made -

 
    	Robot.drivetrain.haloDrive(moveSpeed, rotate, false);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.visionGear.getCounter() >= AutoConstants.SWEEP3_MAX){
    		
    		return true;
    	}
    	//Check if the sonar distance is less then the target Distance, end
//    	if (Robot.drivetrain.getSonarDistance() < targetDistance  && Robot.visionProcessor.wasCentered()){
//     	if (Robot.visionProcessor.isAtY() && Robot.visionProcessor.wasCentered()){
        if (Robot.visionGear.isAtDistance() && Robot.visionGear.isCentered()){
         		     		return true;
    	}
//    	else if (Robot.drivetrain.getSonarDistance() < targetDistance && Robot.visionProcessor.isHasTarget()) {
    	else if (Robot.visionGear.isAtDistance() && Robot.visionGear.isHasTarget()) {
    		return false;
    	}
    	
    	return false;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    	Robot.visionGear.putCounter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

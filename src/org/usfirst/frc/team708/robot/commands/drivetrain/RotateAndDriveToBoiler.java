package org.usfirst.frc.team708.robot.commands.drivetrain;

import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 *RotateAndDriveToBoiler
 * this command will utilize the vision data to drive the robot to the center of the boiler
 * and stop when it is at the any of the target stop at distances
 */
public class RotateAndDriveToBoiler extends Command {
	
	private double		moveSpeed;
	private double		rotate;
	/**
	 * Constructor
	 * @param targetDistance - the distance to stop in front of the target
	 */
// VIET ARE WE STILL USING THE TARGET DISTANCE HERE - I THINK WE ARE ACTUALLY CALCULATING IT IN THE SUBSYSTEM
// IN THE METHOD ISATDISTANCE
// BUT WE NEED TO FIGURE OUT HOW WE ARE GOING TO MAKE THIS WORK WITH MULITPLE DISTANCES FOUND
	public RotateAndDriveToBoiler(double bstopAtDistance){
		double test = bstopAtDistance;
//		SmartDashboard.putNumber("Bstop at distance", test);

//        requires(Robot.drivetrain);
//        requires(Robot.visionBoiler);
        
        // save the distance
        Robot.visionBoiler.putBoilerStopAtDistance(bstopAtDistance);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.visionBoiler.putBoilerIsCentered(false);
    	Robot.visionBoiler.putBoilerHasTarget(false);
    	Robot.visionBoiler.putBoilerAtDistance(false);
    	Robot.visionBoiler.putBoilerCounter(0);
    	Robot.visionBoiler.putBoilerCurrentCenter(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	Robot.visionBoiler.boilerProcessData();
    	rotate 	  = Robot.visionBoiler.boilerGetRotate();  
    	moveSpeed = Robot.visionBoiler.boilerGetMove();

 
    	Robot.drivetrain.haloDrive(moveSpeed, rotate, false);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.visionBoiler.getBoilerCounter() >= AutoConstants.SWEEP3_MAX){
    		
    		return true;
    	}
    	//Check if the sonar distance is less then the target Distance, end
    	else if (Robot.visionBoiler.boilerIsAtDistance() && Robot.visionBoiler.boilerIsCentered()){
			Robot.led1.send_to_led(Constants.SET_TARGET_FOUND);
     		return true;
    	}
    	else {
    		return false;
    	}
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    	Robot.visionBoiler.putBoilerCounter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

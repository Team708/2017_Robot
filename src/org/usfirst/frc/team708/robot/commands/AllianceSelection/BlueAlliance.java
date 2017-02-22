package org.usfirst.frc.team708.robot.commands.AllianceSelection;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

    public class BlueAlliance  extends Command {
 
    	public BlueAlliance() {
    	    }
    	    
    	    protected void initialize() {
    	    }

    	    protected void execute() {    	
        		Robot.allianceColor = Constants.ALLIANCE_BLUE;
        	    Robot.led1.send_to_led(Constants.SET_ALLIANCE_BLUE);
    	    }

    	    protected boolean isFinished() {
    	    	return true;
    	    }

    	    protected void end() {
    	    }

    	    protected void interrupted() {
    	    	end();
    	    }   	
	}
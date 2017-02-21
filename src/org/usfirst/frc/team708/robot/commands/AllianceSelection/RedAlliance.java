package org.usfirst.frc.team708.robot.commands.AllianceSelection;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

    public class RedAlliance  extends Command {
 
    	public RedAlliance() {
    	    }
    	    
    	    protected void initialize() {
    	    }

    	    protected void execute() {    	
        		Robot.allianceColor = Constants.ALLIANCE_RED;    		
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
package org.usfirst.frc.team708.robot.commands.intake_gear;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Intake_Gear_Down extends Command {

	public Intake_Gear_Down() {
//		requires(Robot.pivot_gear);
		this.setTimeout(1.0);
	}

	protected void initialize() {
	}

	protected void execute() {
        if (!Robot.pivot_gear.isRevSwitch())
		      Robot.pivot_gear.moveMotor(Constants.INTAKE_REVERSE);
        else
        	Robot.pivot_gear.stop();
        }

	protected boolean isFinished() {
		if (Robot.pivot_gear.isRevSwitch()|| isTimedOut())
	      	return(true);
        else
      	    return(false);
	}

	protected void end() {
		Robot.pivot_gear.stop();
	}

	protected void interrupted() {
		end();
	}
}
	


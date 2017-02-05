package org.usfirst.frc.team708.robot.commands.intake_gear;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class Intake_Gear_Up extends Command {

public Intake_Gear_Up() {
		
<<<<<<< HEAD
		requires(Robot.intake_gear);
=======
		requires(Robot.pivot_gear);
>>>>>>> refs/remotes/origin/master
	}

	protected void initialize() {

	}

	protected void execute() {

<<<<<<< HEAD
		Robot.intake_gear.movePivotMotor(Constants.INTAKE_FORWARD);
=======
		Robot.pivot_gear.moveMotor(Constants.INTAKE_FORWARD);
>>>>>>> refs/remotes/origin/master
	}


	protected boolean isFinished() {

		return(false);
	}

	protected void end() {
	
<<<<<<< HEAD
		Robot.intake_gear.stopPivot();
=======
		Robot.pivot_gear.stop();
>>>>>>> refs/remotes/origin/master
	
	}

	protected void interrupted() {

		end();
	}
	
}
	

package org.usfirst.frc.team708.robot.commands.intake_gear;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Intake_Gear_Down extends Command {

public Intake_Gear_Down() {
<<<<<<< HEAD
		
		requires(Robot.intake_gear);
	}

	protected void initialize() {

	}

	protected void execute() {

		Robot.intake_gear.movePivotMotor(Constants.INTAKE_REVERSE);
=======
		requires(Robot.pivot_gear);
	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.pivot_gear.moveMotor(Constants.INTAKE_REVERSE);
>>>>>>> refs/remotes/origin/master
	}


	protected boolean isFinished() {
<<<<<<< HEAD

=======
>>>>>>> refs/remotes/origin/master
		return(false);
	}

	protected void end() {
<<<<<<< HEAD
	
		Robot.intake_gear.stopPivot();

	}

	protected void interrupted() {

=======
		Robot.pivot_gear.stop();
	}

	protected void interrupted() {
>>>>>>> refs/remotes/origin/master
		end();
	}
	
}
	


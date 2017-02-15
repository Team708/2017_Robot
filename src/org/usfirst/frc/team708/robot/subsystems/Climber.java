package org.usfirst.frc.team708.robot.subsystems;


import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.Climber.ManualMoveClimber;

import com.ctre.CANTalon;

//import org.usfirst.frc.team708.robot.RobotMap;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *  @author James Makovics
 *  @author Cody Cooper
 *  @author James McPeak
 */
public class Climber extends Subsystem {
	public static CANTalon climberMotor; // Uses the Motor CanTalon

	/**
	 * Constructor
	 */
	public Climber() {
	// Initializes the motor for the Climber
		climberMotor = new CANTalon (RobotMap.climberMotor);
		
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
	setDefaultCommand(new ManualMoveClimber());
	}
	//Sets the motor speed to the variable speed
	public void manualMove(double speed) {
		climberMotor.set(speed);
	}
	
	public void stop(){
		climberMotor.set(Constants.MOTOR_OFF);
	}
	
	public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
	}
}


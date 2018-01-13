package org.usfirst.frc.team708.robot.subsystems;


import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.Climber.ManualMoveClimber;

import com.ctre.phoenix.motorcontrol.can.*;

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
	public static WPI_TalonSRX climberMotor; // Uses the Motor CanTalon

	/**
	 * Constructor
	 */
	public Climber() {
	// Initializes the motor for the Climber
		climberMotor = new WPI_TalonSRX (RobotMap.climberMotor);
	}

	public void initDefaultCommand() {
	}

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


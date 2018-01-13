package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem that intakes balls
 * @author Madison
 * @author Nick
 */

public class Intake_Ball extends Subsystem {
	
    private WPI_TalonSRX intakeMotor;
    
	public Intake_Ball() {
		intakeMotor = new WPI_TalonSRX (RobotMap.intakeMotorBall);	
	}
	
	public void initDefaultCommand() {
    }
	
	//I believe this sets the speed of the motor
	public void moveMotor(double speed) {
		intakeMotor.set(speed);
	}
	//I believe this stops the motor
	public void stop(){
		intakeMotor.set(Constants.INTAKE_OFF);
	}

//Sends data about the subsystem to the Smart Dashboard
      public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
    }
}


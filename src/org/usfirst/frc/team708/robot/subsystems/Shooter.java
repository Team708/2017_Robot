package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team708.robot.commands.visionProcessor.SonarOverride;


import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.interfaces.Gyro;
//import edu.wpi.first.wpilibj.GyroBase;
//import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmsartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
	// Put methods for controlling this subsystem here. Call these
	// from Commands.
	
	private CANTalon shooter;	// Motor Controllers

	/**
	 * Constructor
	 */
	public Shooter() {
		// Initializes the encoder
        
		
		// Initializes the motor

		shooter = new CANTalon(56);
    	shooter.reset();
    	shooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);    
    	shooter.reverseSensor(false);
    	shooter.configEncoderCodesPerRev(500);
    	shooter.set(0);
    	shooter.configNominalOutputVoltage(12.0, -12.0);
    	shooter.configMaxOutputVoltage(12.0);
    	shooter.configPeakOutputVoltage(12.0, -12.0);        
	}

	public void initDefaultCommand() {

    }
	

	
	/**
	 * Sends data to the Smart Dashboard
	 */
	public void sendToDashboard() {
		SmartDashboard.putNumber("Encoder Position", shooter.getEncPosition());
		SmartDashboard.putNumber("Encoder Speed", shooter.getSpeed());
		SmartDashboard.putNumber("Encoder Velocity", shooter.getEncVelocity());
	}
}


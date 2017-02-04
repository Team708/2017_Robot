package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team708.robot.commands.visionProcessor.SonarOverride;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

		shooter = new CANTalon(41);
		shooter.enable();
    	shooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);    
    	shooter.reverseSensor(false);
    	shooter.configEncoderCodesPerRev(256);
//		shooter.changeControlMode(TalonControlMode.PercentVbus);
    	shooter.configNominalOutputVoltage(+0.0, -0.0);
    	shooter.configPeakOutputVoltage(+4.0, -4.0);
        /* set closed loop gains in slot1 */
    	shooter.setProfile(0);

    	shooter.setP(0.6);
    	shooter.setI(0.002);
    	shooter.setD(0);

	}

	public void initDefaultCommand() {

    }
	
	public void manualSpeed(double speed) {
		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.set(speed);
//		shooter.set(.2);
}
	
	public void setFgain(double F){
		shooter.setF(F);
	}
	
	public void stop(){
		shooter.set(Constants.MOTOR_OFF);
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


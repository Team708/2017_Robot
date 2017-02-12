package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.shooter.ManualShoot;
import org.usfirst.frc.team708.robot.commands.shooter.SpinShooter;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team708.robot.commands.visionProcessor.SonarOverride;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
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
	
	private CANTalon shooter, shooterSlave;	// Motor Controllers
	
	private Servo	 hood;

	/**
	 * Constructor
	 */
	public Shooter() {
		// Initializes the encoder
        
		
		// Initializes the motor

		shooter = new CANTalon(RobotMap.shooterMotorMaster);
		shooterSlave = new CANTalon(RobotMap.shooterMotorSlave);
	    shooterSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		shooterSlave.set(shooter.getDeviceID());
	    
		shooter.enable();
    	shooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);    
    	shooter.reverseSensor(false);
    	shooter.configEncoderCodesPerRev(256);
		shooter.changeControlMode(TalonControlMode.PercentVbus);
    	shooter.configNominalOutputVoltage(+0.0, -0.0);
    	shooter.configPeakOutputVoltage(+4.0, -4.0);
        /* set closed loop gains in slot1 */
    	
    	shooter.setPID(0.06, 0.001, 2.0, 0.0, 10, 0.0, 0);
    	hood = new Servo(RobotMap.hoodAngle);
		
	}

	public void initDefaultCommand() {
//			setDefaultCommand(new ManualShoot());
    }
	
	public void manualSpeed(double speed) {
		shooter.changeControlMode(TalonControlMode.PercentVbus);
		shooter.set(speed);
}
	
	public void manualRPM(double rpm) {
		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.set(rpm);
}
	
	public void setFgain(double F){
		shooter.setF(F);
	}
	
	public void moveHood(int angle) {
		SmartDashboard.putNumber("Servo passed in: ", angle);
		SmartDashboard.putNumber("Servo Raw", hood.getRaw());
        hood.setRaw(angle);		
	}
	
	public void stop(){
		shooter.changeControlMode(TalonControlMode.PercentVbus);
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


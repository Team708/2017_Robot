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

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Servo;

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
	private Servo	 hood;
	private int		 hoodLocation;

	/**
	 * Constructor
	 */
	public Shooter() {
		// Initializes the encoder
        
		
		// Initializes the motor

		shooter = new CANTalon(RobotMap.shooterMotorMaster);
		shooter.enable();
    	shooter.setFeedbackDevice(FeedbackDevice.QuadEncoder);    
    	shooter.reverseSensor(false);
    	shooter.configEncoderCodesPerRev(256);
//		shooter.changeControlMode(TalonControlMode.PercentVbus);
    	shooter.configNominalOutputVoltage(+0.0, -0.0);
    	shooter.configPeakOutputVoltage(+4.0, -4.0);
        /* set closed loop gains in slot1 */
    	shooter.setProfile(0);

//    	shooter.setP(0.6);
//    	shooter.setI(0.002);
//    	shooter.setD(0);

    	shooter.setPID(0.6, 0.0, 0.0, Constants.SHOOTER_F_HIGH, 0, 4.0, 0);
    	
    	hood = new Servo(RobotMap.hoodAngle);

//    	hood.setBounds(2455.0, 8.0, 0.0, 8.0, 553.0); // defines HS-805MG Servo
    	hoodLocation = 25;
	}

	public void initDefaultCommand() {

    }
	
	public void manualSpeed(double speed) {
//		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.set(speed);
//		shooter.set(.2);
}
	
	public void manualRPM(double rpm) {
		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.set(rpm);
}
	
	public void setFgain(double F){
		shooter.setF(F);
	}
	
	public void stop(){
		shooter.set(Constants.MOTOR_OFF);
}
	
	public void moveHood(int angle) {

		if (Constants.DEBUG) {
			SmartDashboard.putNumber("Servo passed in: ", angle);
			SmartDashboard.putNumber("Servo Raw", hood.getRaw());
		}
		hoodLocation = angle;
        hood.setRaw(angle);		
	}
	
	public void hoodAdjust(double angle) {

		if ((angle > 0.0) && (hoodLocation<2000)) hoodLocation+=Constants.HOOD_CALIBRATION;
		else if ((angle < 0.0) && (hoodLocation>25)) hoodLocation-=Constants.HOOD_CALIBRATION;
			
		moveHood(hoodLocation);
		if (Constants.DEBUG) {
			SmartDashboard.putNumber("Servo angle",hoodLocation);
			SmartDashboard.putNumber("Servo joystick value", angle);
		}
    	
	}
	/**
	 * Sends data to the Smart Dashboard
	 */
	public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
		SmartDashboard.putNumber("Encoder Position", shooter.getEncPosition());
		SmartDashboard.putNumber("Encoder Speed", shooter.getSpeed());
		SmartDashboard.putNumber("Encoder Velocity", shooter.getEncVelocity());
	}
}


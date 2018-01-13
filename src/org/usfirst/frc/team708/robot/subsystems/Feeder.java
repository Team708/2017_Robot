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
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Leaders
 * 
 */
public class Feeder extends Subsystem {
	
	private WPI_TalonSRX feedMotor;
	/**
	 * Constructor
	 */
	public Feeder() {
		feedMotor = new WPI_TalonSRX(RobotMap.feederMotor); //initializes the loading motor
	}
	
	public void initDefaultCommand() {
    }
	
	public void manualMove(double speed){
		feedMotor.set(speed);
	}
	
	public void stop(){
		feedMotor.set(Constants.MOTOR_OFF);
	}
	
	public void sendToDashboard() {
//		SmartDashboard.putNumber("Loader Motor Speed", feedMotor.getSpeed());

		if (Constants.DEBUG) {
		}
	}
}
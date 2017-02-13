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
import edu.wpi.first.wpilibj.command.Subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Leaders
 * 
 */
public class Loader extends Subsystem {
	
	private CANTalon loadMotor;
	private boolean spinforward; 

	/**
	 * Constructor
	 */
	public Loader() {
		loadMotor = new CANTalon(RobotMap.loaderMotor); //initializes the loading motor
		spinforward = false;
	}
	
	public void initDefaultCommand() {
    }
	
	public void manualMove(double speed){
		loadMotor.set(speed);
	}
	
	public void stop(){
		loadMotor.set(Constants.MOTOR_OFF);
	}
	
	public boolean toggleSpin() {
		    spinforward = !spinforward;
			return(spinforward);
	}
	
	public boolean spinForward() {
		return(spinforward);
	}
	
	public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
	}
}
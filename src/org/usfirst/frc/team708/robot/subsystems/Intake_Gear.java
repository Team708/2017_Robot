package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem that intakes balls
 * @author Madison
 * @author Nick
 */

public class Intake_Gear extends Subsystem {
	
    private CANTalon intakeMotor;

	//I'm trying to link the right motor to the intake code here
	public Intake_Gear() {
		intakeMotor = new CANTalon (RobotMap.intakeMotorGear);
	}
	
	public void initDefaultCommand() {
    }
	
	//I believe this sets the speed of the motor
	public void moveMotor(double speed) {
		intakeMotor.set(speed);
	}
	
	public boolean hasGear() {
//		return ((Robot.drivetrain.getIRDistance() > 0) && (Robot.drivetrain.getIRDistance() < Constants.IR_HAS_GEAR_DISTANCE));
		
		if (Robot.drivetrain.hasGear())
			Robot.led1.send_to_led(Constants.SET_HAS_GEAR_TARGETING);
		else
			Robot.led1.send_to_led(Constants.SET_ALLIANCE_BLUE);
		
		return (Robot.drivetrain.hasGear());
	}
	//I believe this stops the motor
	public void stop(){
		intakeMotor.set(Constants.INTAKE_OFF);
	}
	
    
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
//		if (Robot.drivetrain.hasGear())
//			Robot.led1.send_to_led(Constants.SET_HAS_GEAR);
//		else
//			Robot.led1.send_to_led(Constants.SET_ALLIANCE_BLUE);
		
		if (Constants.DEBUG) {
			
		}
    }
    
    
}


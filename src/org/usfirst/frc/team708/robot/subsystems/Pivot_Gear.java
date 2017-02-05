package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem that intakes balls
 * @author Madison
 * @author Nick
 */

public class Pivot_Gear extends Subsystem {
	
    private CANTalon pivotMotor;

	//I'm trying to link the right motor to the intake code here
	public Pivot_Gear() {
		pivotMotor 	= new CANTalon (RobotMap.pivotGearMotor);
	}
	
	public void initDefaultCommand() {
    }
	
	//I believe this sets the speed of the motor
	public void moveMotor(double speed) {		
		pivotMotor.set(speed);
	}
	
	//I believe this stops the motor
	public void stop(){	
		pivotMotor.set(Constants.INTAKE_OFF);	
	}
	
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
    }
}


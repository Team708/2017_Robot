package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import com.ctre.CANTalon;

//import org.usfirst.frc.team708.robot.RobotMap;
//import edu.wpi.first.wpilibj.Relay;
//import edu.wpi.first.wpilibj.Relay.Value;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * Subsystem that intakes balls
 * @author Madison
 * @author Nick
 */

public class Intake_Ball extends Subsystem {
	
    private CANTalon intakeMotor;

	//I'm trying to link the right motor to the intake code here
	public Intake_Ball() {
		
		intakeMotor = new CANTalon (RobotMap.intakeMotorBall);
		
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
    
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
    }
    
    
}


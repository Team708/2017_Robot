package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem that intakes balls
 * @author Madison
 * @author Nick
 */

public class Intake_Gear extends Subsystem {
	
    private WPI_TalonSRX 		intakeMotor;
	private DigitalInput	gearSensor;

	//I'm trying to link the right motor to the intake code here
	public Intake_Gear() {
		intakeMotor = new WPI_TalonSRX (RobotMap.intakeMotorGear);
	    gearSensor = new DigitalInput(RobotMap.gearSensorSwitch);
	    
//	    intakeMotor.reset();
//	    intakeMotor.changeControlMode(TalonControlMode.Voltage);
	}
	
	public void initDefaultCommand() {
    }
	
	//I believe this sets the speed of the motor
	public void moveMotor(double speed) {
		intakeMotor.set(speed);
	}
	
	public boolean hasGear() {
		
		if (gearSensor.get()) {
//			Robot.led1.send_to_led(Constants.SET_HAS_GEAR_TARGETING);
			return (true);
	    }
		else {
//			Robot.led1.send_to_led(Robot.ledAllianceColor);
			return (false);
		}
	}
	public void stop(){
		intakeMotor.set(Constants.INTAKE_OFF);
	
	}
	
    
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
		if (Constants.DEBUG) {
	    	SmartDashboard.putBoolean("has gear", hasGear());
		}
    }
    
    
}


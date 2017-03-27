package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.util.Math708;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

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
		pivotMotor.enable();

//		pivotMotor.reverseSensor(true);
		pivotMotor.enableBrakeMode(true);
		pivotMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		pivotMotor.changeControlMode(com.ctre.CANTalon.TalonControlMode.Position);
		pivotMotor.configEncoderCodesPerRev(Constants.PIVOT_GEAR_ENCODER_COUNT);
	}
	
	public void initDefaultCommand() {
    }
	
	//I believe this sets the speed of the motor
	public void moveMotor(double speed) {
    		pivotMotor.set(speed);
	}
	
	public boolean isFwdSwitch() {
		return (pivotMotor.getPosition()>= Constants.PIVOT_GEAR_ENCODER_HIGH);
	}
	
	public boolean isRevSwitch() {
//		return (pivotMotor.getPosition()<= Constants.PIVOT_GEAR_ENCODER_LOW);
		return (false);
	}
	
	//I believe this stops the motor
	public void stop(){	
		pivotMotor.set(Constants.INTAKE_OFF);	
	}
	
    public void sendToDashboard() {
    	SmartDashboard.putNumber("Pivot encoder", pivotMotor.getPosition());
		if (Constants.DEBUG) {
		}
    }
}


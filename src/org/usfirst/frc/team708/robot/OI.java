

package org.usfirst.frc.team708.robot;


import edu.wpi.first.wpilibj.buttons.*;

import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.commands.shooter.*;
import org.usfirst.frc.team708.robot.commands.led_out.*;
import org.usfirst.frc.team708.robot.commands.intake_ball.*;
import org.usfirst.frc.team708.robot.commands.intake_gear.*;
import org.usfirst.frc.team708.robot.commands.loader.*;

import org.usfirst.frc.team708.robot.commands.visionProcessor.*;

import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.util.triggers.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
	// Gamepads
	public final static Gamepad driverGamepad 	= new Gamepad(RobotMap.driverGamepad);	// Driver gamepad
	public final static Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);// Operator gamepad
	
	// look in Gamepad.java for button constants
	
	/*
	 * Driver Button Assignment
	 */
	
	// Drivetrain Buttons
	private static final int INTAKE_GEAR_IN			 	= Gamepad.button_L_Shoulder;
	private static final int INTAKE_BALL_IN				= Gamepad.button_R_Shoulder;
	private static final int INTAKE_GEAR_OUT			= Gamepad.shoulderAxisLeft;
	private static final int INTAKE_BALL_OUT			= Gamepad.shoulderAxisRight;
	private static final int INTAKE_GEAR_DOWN			= Gamepad.button_A;	
	private static final int INTAKE_GEAR_UP				= Gamepad.button_B;
	
	public static final int LED_BUTTON 					= Gamepad.button_X;

	
	/*
	 * Operator Button Assignment
	 */
	private static final int SPIN_SHOOTER_BUTTON		= Gamepad.button_L_Shoulder;
	private static final int SPIN_SHOOTER_BACK_BUTTON	= Gamepad.shoulderAxisLeft;
	private static final int SPIN_FEEDER_BUTTON			= Gamepad.button_R_Shoulder;
	private static final int SPIN_FEEDER_BACK_BUTTON	= Gamepad.shoulderAxisRight;
	
	private static final int OPERATE_HANGER				= Gamepad.leftStick_Y;
	private static final int OPERATE_HOOD				= Gamepad.rightStick_Y;

	public static final int HOOD_HIGH		 			= Gamepad.button_A;
	public static final int HOOD_LOW	 				= Gamepad.button_B;
	private static final int LOADER_STOP				= Gamepad.button_X;	
	private static final int LOADER_SPIN				= Gamepad.button_Y;	
	
	/*
	 * Driver Button Commands
	 */
	public static final Button  intakeGearIn 	= new JoystickButton(driverGamepad, INTAKE_GEAR_IN);
	public static final Trigger intakeGearOut	= new AxisUp(driverGamepad, INTAKE_GEAR_OUT);
	public static final Button  intakeBallIn 	= new JoystickButton(driverGamepad, INTAKE_BALL_IN);
	public static final Trigger intakeBallOut	= new AxisUp(driverGamepad, INTAKE_BALL_OUT);
	public static final Button  intakeGearUp 	= new JoystickButton(driverGamepad, INTAKE_GEAR_UP);
	public static final Button  intakeGearDown 	= new JoystickButton(driverGamepad, INTAKE_GEAR_DOWN);
	public static final Button 	led				= new JoystickButton(driverGamepad, LED_BUTTON);
	
	/*
	 * Operator Button Commands
	 */
	public static final Button  spinShooter		= new JoystickButton(operatorGamepad, SPIN_SHOOTER_BUTTON);
	public static final Trigger spinShooterBack	= new AxisUp(operatorGamepad, SPIN_SHOOTER_BACK_BUTTON);
	public static final Button  spinFeeder		= new JoystickButton(operatorGamepad, SPIN_FEEDER_BUTTON);
	public static final Trigger spinFeederBack	= new AxisUp(operatorGamepad, SPIN_FEEDER_BACK_BUTTON);
	public static final Button  loaderSpin		= new JoystickButton(operatorGamepad, LOADER_SPIN);
	public static final Button  loaderOff		= new JoystickButton(operatorGamepad, LOADER_STOP);

	public static final Button hoodHigh			= new JoystickButton(operatorGamepad, HOOD_HIGH);
	public static final Button hoodLow			= new JoystickButton(operatorGamepad, HOOD_LOW);
	public static final Trigger hoodAdjust		= new AxisUp(operatorGamepad, OPERATE_HOOD);
	public static final Trigger hoodAdjustDown	= new AxisDown(operatorGamepad, OPERATE_HOOD);

	/**
	 * Constructor
	 * Assigns commands to be called when each button is pressed.
	 */
	
	public OI() {
		/*
		 * Driver Commands to be called by button
		 */
		intakeGearIn.whileHeld(new Intake_Gear_In());
		intakeGearUp.whileHeld(new Intake_Gear_Up());
		intakeGearDown.whileHeld(new Intake_Gear_Down());
		intakeBallIn.whileHeld(new Intake_Ball_In());
		intakeGearOut.whileActive(new Intake_Gear_Out());
		intakeBallOut.whileActive(new Intake_Ball_Out());
		
//		sonarOverride.whenPressed(new SonarOverride());
//		
		spinShooter.whileHeld(new SpinShooter());
//		spinShooterBack.whileActive(new SpinShooterBack());
//		spinFeeder.whenPressed(new SpinFeeder());
//		spinFeederBack.whileActive(new SpinShooterBack());
//		
		loaderSpin.whenPressed(new LoaderSpin());
		loaderOff.whenPressed(new LoaderOff());
		
		led.whenPressed(new LED_out());
		hoodHigh.whenPressed(new MoveHoodHigh());
		hoodLow.whenPressed(new MoveHoodLow());
		hoodAdjust.whileActive(new HoodAdjust());
		hoodAdjustDown.whileActive(new HoodAdjust());

		}
}


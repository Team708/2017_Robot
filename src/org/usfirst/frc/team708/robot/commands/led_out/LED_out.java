package org.usfirst.frc.team708.robot.commands.led_out;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.subsystems.LED;
import org.usfirst.frc.team708.robot.subsystems.Drivetrain;


import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

//import org.team708.robot.OI;
//import org.team708.robot.subsystems.Loader;
//import org.team708.robot.util.Gamepad;
//import org.team708.robot.commands.shooter.Fire;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class LED_out extends Command {

static public byte count = 0x00;

    public LED_out() {
    	requires(Robot.led1);    	
    }
    
// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {    	
    	    count++;
    	    if (count > Constants.SET_OFF) count = 0x00;
    		Robot.led1.send_to_led(count);

//    		if (count == 0x02) Robot.drivetrain.setGearLight(false);
//    		if (count == 0x03) Robot.drivetrain.setBoilerLight(false);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}

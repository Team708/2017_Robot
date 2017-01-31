package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.AutoConstants;

//import org.team708.robot.commands.visionProcessor.ProcessData;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Math708;

//import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
//import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

/**
 *
 */
public class LED extends Subsystem {
    
	public static SerialPort		led_out;
    public static Port				port;
	
	
	public LED() {
		port = Port.kMXP;
		led_out = new SerialPort(9600, port);
	}

	public void send_to_led(int command){
		led_out.writeString("10\n");
		led_out.flush();
	}
	
	public void sendToDashboard() {
		SmartDashboard.putString("LED port", port.toString());
	}

    public void initDefaultCommand() {
		if (Constants.DEBUG) {
		}    	
    }
}


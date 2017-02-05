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
import edu.wpi.first.wpilibj.SerialPort.*;

/**
 *
 */

public class LED extends Subsystem {
    
	public static SerialPort		led_out;
    public static Port				port;
    public static int buttonvalue = 0;

    public static byte[] msg = new byte[4];
    public static String messageback;
    
	public LED() {		
		
		port = Port.kOnboard;
		led_out = new SerialPort(9600, port, 8, Parity.kNone, StopBits.kOne);
		led_out.setWriteBufferMode(WriteBufferMode.kFlushOnAccess);
		
		}

	public void send_to_led(int command){
		buttonvalue = command;
		String message = Integer.toString(buttonvalue);
		
		led_out.writeString(message.toString());
//		led_out.write(command, 8);

		messageback = new String(led_out.readString());
		SmartDashboard.putNumber("LED sent to", buttonvalue);
		SmartDashboard.putString("LED read from arduio", messageback);

		}
	
	public void sendToDashboard() {
		SmartDashboard.putString("LED port", port.toString());
	}

    public void initDefaultCommand() {
		if (Constants.DEBUG) {
		}    	
    }
}


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

    public static byte[] msg     = new byte[8];
    public static byte[] msgread = new byte[10];

    public static String messageback;
    
	public LED() {		
		
//		port = Port.kOnboard;
		port = Port.kMXP;
		led_out = new SerialPort(9600, port, 8, Parity.kNone, StopBits.kOne);
		led_out.setWriteBufferMode(WriteBufferMode.kFlushOnAccess);
		msg[0] = 0x00;
//		msg[1] = 0x55;
//		msg[2] = 0x00;
//		msg[3] = 0x64;
//		msg[4] = 0x00;
//		msg[5] = 0x0A;
//		msg[6] = 0x00;
		}

	public void send_to_led(int command){
		buttonvalue = command;
		String message = Integer.toString(buttonvalue);
		msg[0]++;
//		led_out.writeString(message.toString());
		led_out.write(msg, 1);
        led_out.flush();
        
//		if (led_out.getBytesReceived() > 0)
//			msgread = led_out.read(1);
		SmartDashboard.putNumber("LED sent to", msg[0]);
//		SmartDashboard.putNumber("LED sent to", buttonvalue);
//		SmartDashboard.putNumber("LED read from arduio", msgread[0]);
		}
	
	public void sendToDashboard() {
		SmartDashboard.putString("LED port", port.toString());
	}

    public void initDefaultCommand() {
		if (Constants.DEBUG) {
		}    	
    }
}


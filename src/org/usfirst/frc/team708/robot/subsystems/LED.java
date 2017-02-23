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

    public static byte[] msg     = new byte[10];

    public static String messageback;
    static public byte count = 0x00;
    
	public LED() {		
		
//		port = Port.kOnboard;  //on board serial - bits were reversed 0x55 = 0xAA to arduino
		port = Port.kMXP;      //expansion board serial
		led_out = new SerialPort(9600, port, 8, Parity.kNone, StopBits.kOne);
		led_out.setWriteBufferMode(WriteBufferMode.kFlushOnAccess);
		msg[0] = 0x00;
		}

	public void send_to_led(byte command){
		msg[0] = command;
		led_out.write(msg, 1);
        led_out.flush();
		}
	
	public void sendToDashboard() {
		if (Constants.DEBUG) {
		    SmartDashboard.putNumber("LED code sent", count);
		}
	}

    public void initDefaultCommand() {
    }
}


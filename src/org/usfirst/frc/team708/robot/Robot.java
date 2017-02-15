
package org.usfirst.frc.team708.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.vision.CameraServer;
//import edu.wpi.first.wpilibj.networktables.NetworkTable;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.AxisCamera;


import org.usfirst.frc.team708.robot.subsystems.Drivetrain;
import org.usfirst.frc.team708.robot.subsystems.Shooter;
import org.usfirst.frc.team708.robot.subsystems.Loader;
import org.usfirst.frc.team708.robot.subsystems.Feeder;
import org.usfirst.frc.team708.robot.subsystems.Intake_Ball;
import org.usfirst.frc.team708.robot.subsystems.Intake_Gear;
import org.usfirst.frc.team708.robot.subsystems.Pivot_Gear;
import org.usfirst.frc.team708.robot.subsystems.Climber;
import org.usfirst.frc.team708.robot.subsystems.VisionLift;
import org.usfirst.frc.team708.robot.subsystems.VisionGear;
import org.usfirst.frc.team708.robot.subsystems.VisionBoiler;
//import org.usfirst.frc.team708.robot.subsystems.VisionProcessor;
import org.usfirst.frc.team708.robot.subsystems.LED;

import org.usfirst.frc.team708.robot.OI;

import org.usfirst.frc.team708.robot.commands.feeder.*;
import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.commands.intake_ball.*;
import org.usfirst.frc.team708.robot.commands.intake_gear.*;
import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.commands.Climber.*;
import org.usfirst.frc.team708.robot.commands.loader.*;
import org.usfirst.frc.team708.robot.commands.shooter.*;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;
import org.usfirst.frc.team708.robot.commands.led_out.*;

public class Robot extends IterativeRobot {
    
    Timer statsTimer;										// Timer used for Smart Dash statistics
    
    public static Drivetrain 		drivetrain;
    public static Shooter	 		shooter;
    public static Feeder	 		feeder;
    public static Intake_Ball		intake_ball;
    public static Intake_Gear		intake_gear;
    public static Pivot_Gear		pivot_gear;
    public static Loader	 		loader;

    public static Climber			climber;
    
//    public static VisionProcessor 	visionProcessor;
    public static VisionLift 	visionLift;
    public static VisionBoiler 	visionBoiler;
    public static VisionGear 	visionGear;

    public static LED				led1;
    
    public static OI	 			oi;

    public static int 						AllianceColor;
    public static DriverStation 			ds;
    public static DriverStation.Alliance 	alliance;
    
    SendableChooser 	autonomousMode = new SendableChooser<>();
    Command 			autonomousCommand;
    Preferences			prefs;
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
        statsTimer = new Timer();	// Initializes the timer for sending Smart Dashboard data
        statsTimer.start();		// Starts the timer for the Smart Dashboard
        
		
        
// Subsystem Initialization

    drivetrain 			= new Drivetrain();
    shooter				= new Shooter();
    intake_ball			= new Intake_Ball();
    intake_gear			= new Intake_Gear();
    pivot_gear			= new Pivot_Gear();
    feeder				= new Feeder();
    loader				= new Loader();
    led1				= new LED();
    climber				= new Climber();
    visionLift			= new VisionLift();
    visionBoiler		= new VisionBoiler();
    visionGear			= new VisionGear();
            
    oi 					= new OI();		// Initializes the OI. 
						// This MUST BE LAST or a NullPointerException will be thrown
	
//	UsbCamera ucamera=CameraServer.getInstance().startAutomaticCapture("cam0", 0);
//	AxisCamera camera=CameraServer.getInstance().addAxisCamera("cam1", "10.7.8.11");
	
	sendDashboardSubsystems();		// Sends each subsystem's currently running command to the Smart Dashboard
	queueAutonomousModes();			// Adds autonomous modes to the selection box    
    }
	
    /**
     * Runs periodically while the robot is enabled
     */
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		sendStatistics();
		prefs = Preferences.getInstance();
	
//		try{
//		    if (ds.isFMSAttached())
//		        {
//			    alliance = ds.getAlliance();
//	             if (ds.getAlliance() == Alliance.Blue)
//	        	    led1.send_to_led(Constants.SET_ALLIANCE_BLUE);
//    	        else if (ds.getAlliance() == Alliance.Red)
//	            	led1.send_to_led(Constants.SET_ALLIANCE_RED);
//	            else
//	            	led1.send_to_led(Constants.SET_ALLIANCE_INVALID);	        
//    		    }
//	        }
//		catch ( e){}
	}

	/**
	 * Runs at the start of autonomous mode
	 */
    	public void autonomousInit() {
    	
//    	turnDirection = prefs.getDouble("TurnDirection", 4.0);
    		
    	// schedule the autonomous command (example)   		
    	autonomousCommand = (Command)autonomousMode.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {	
        Scheduler.getInstance().run();
        sendStatistics();
    }

    /**
     * Runs when teleop mode initializes
     */
    public void teleopInit() {
	    // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        sendStatistics();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        sendStatistics();
    }
    
    /**
     * Sends data from each subsystem periodically as set by sendStatsIntervalSec
     */
    private void sendStatistics() {
        if (statsTimer.get() >= Constants.SEND_STATS_INTERVAL) {
            statsTimer.reset();

            // Various debug information
            drivetrain.sendToDashboard();
            feeder.sendToDashboard();
            loader.sendToDashboard();
            shooter.sendToDashboard();
            led1.sendToDashboard();
            climber.sendToDashboard();
            intake_ball.sendToDashboard();
            intake_gear.sendToDashboard();
            pivot_gear.sendToDashboard();
//          visionProcessor.sendToDashboard();
            visionLift.sendToDashboard();
            visionBoiler.sendToDashboard();
            visionGear.sendToDashboard();
        }
    }
    
    /**
     * Adds every autonomous mode to the selection box and adds the box to the Smart Dashboard
     */
    private void queueAutonomousModes() {
    	
    	autonomousMode.addObject("Drive Straight for time", new DriveStraightForTime(.5, 3));
    	autonomousMode.addDefault("Do Nothing", new DoNothing());
//		autonomousMode.addObject("Find Target", new DriveToTarget());
		autonomousMode.addObject("Drive in Square", new DriveInSquare());

    	SmartDashboard.putData("Autonomous Selection", autonomousMode);    	   	
    }
    
    /**
     * Sends every subsystem to the Smart Dashboard
     */
    private void sendDashboardSubsystems() {
    	SmartDashboard.putData(shooter);
    	SmartDashboard.putData(feeder);
    	SmartDashboard.putData(loader);
    	SmartDashboard.putData(drivetrain);
    	SmartDashboard.putData(led1);
    	SmartDashboard.putData(intake_ball);
    	SmartDashboard.putData(intake_gear);
    	SmartDashboard.putData(pivot_gear);
//    	SmartDashboard.putData(visionProcessor);
    	SmartDashboard.putData(visionLift);
    	SmartDashboard.putData(visionBoiler);
    	SmartDashboard.putData(visionGear);
    	SmartDashboard.putData(climber);
    }
}
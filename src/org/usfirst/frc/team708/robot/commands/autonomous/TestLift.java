//package org.usfirst.frc.team708.robot.commands.autonomous;
//
//import edu.wpi.first.wpilibj.command.CommandGroup;
//import org.usfirst.frc.team708.robot.Robot;
//import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightForTime;
//import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
//
//
//import edu.wpi.first.wpilibj.command.WaitCommand;
///**
// *
// */
//public class TestLift extends CommandGroup {
//
//    public TestLift() {
//        // Add Commands here:
//        // e.g. addSequential(new Command1());
//        //      addSequential(new Command2());
//        // these will run in order.
//
//        // To run multiple commands at the same time,
//        // use addParallel()
//        // e.g. addParallel(new Command1());
//        //      addSequential(new Command2());
//        // Command1 and Command2 will run in parallel.
//
//        // A command group will require all of the subsystems that each member
//        // would require.
//        // e.g. if Command1 requires chassis, and Command2 requires arm,
//        // a CommandGroup containing them would require both the chassis and the
//        // arm.
//    	boolean test=true;
//    	addSequential(new DriveStraightForTime(.4, 2));
//    	if (test) {
//    		addSequential(new WaitCommand(2.0));
//    	}
//    	else{
//    		addSequential(new WaitCommand(5.0));
//    	}
//    	addSequential(new DriveStraightForTime(.4, 5));
//    	
//    	while (test){
//    		addSequential(new DriveStraightForTime(.1,1));
//    	}
//    }
//}

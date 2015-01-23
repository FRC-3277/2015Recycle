/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3277.robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.MotorSafety;
// Gone from 2015 build season.
//import edu.wpi.first.wpilibj.watchdog;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {
    
    boolean extendForward = false;
    boolean extendReverse = false;
    boolean retractForward = false;
    boolean retractReverse = false;
    
    // Joysticks
    Joystick joystick;
    Joystick controller;
    
    // Drive System
    FRCDriveControl DriveControl; 
    
    Timer TeleopInitTimer;

    // Autonomous
    Timer AutonomousTimer;

    MotorSafety motorSafety;
    MotorSafetyHelper watchdog;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // Input
        joystick = new Joystick(0);
        controller = new Joystick(1);
        
        // Drive
        DriveControl = new FRCDriveControl();
        
        TeleopInitTimer = new Timer();
        

        watchdog = new MotorSafetyHelper(motorSafety);
        watchdog.setExpiration(100);
    }

    /**
     * This function is called when the autonomous period starts.
     */
    public void autonomousInit()
    {
        AutonomousTimer = new Timer();
        AutonomousTimer.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        
        
        double autonomousTime = AutonomousTimer.get();
    }

    public void teleopInit() {
        TeleopInitTimer.reset();
        TeleopInitTimer.start();
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        // Drive system
        watchdog.feed();
        // What was twist used for last year?
        //DriveControl.Drive(joystick.getX(), joystick.getY(), joystick.getTwist());
        DriveControl.Drive((int)joystick.getX(), (int)joystick.getY());
        watchdog.feed();
        
        // Shooting system
//       watchdog.feed();
//        ShooterControl.Unlatch(joystick.getRawButton(11));
//       watchdog.feed();
//        ShooterControl.Latch(joystick.getRawButton(12));
//       watchdog.feed();
//        ShooterControl.Shoot(joystick.getRawButton(9));
//       watchdog.feed();
//        ShooterControl.RetractShooter(joystick.getRawButton(10));
//       watchdog.feed();
        

        

        
//        if (controller.getRawButton(1))
//        {
//            AutonomousTimer.delay(0.5);
//            extendForward = !extendForward;
//            PickerControl.ExtendForwardControl(extendForward);
//        }
//        if (controller.getRawButton(2))
//        {
//            AutonomousTimer.delay(0.5);
//            extendReverse = !extendReverse;
//            PickerControl.ExtendReverseControl(extendReverse);
//        }
//        if (controller.getRawButton(3))
//        {
//            AutonomousTimer.delay(0.5);
//            retractForward = !retractForward;
//            PickerControl.RetractForwardControl(retractForward);
//        }
//        if (controller.getRawButton(4))
//        {
//            AutonomousTimer.delay(0.5);
//            retractReverse = !retractReverse;
//            PickerControl.RetractReverseControl(retractReverse);
//        }
        
        //DriverScreen.printLine(6, "PS: " + extendForward + "|" + extendReverse + "|" + retractForward + "|" + retractReverse);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

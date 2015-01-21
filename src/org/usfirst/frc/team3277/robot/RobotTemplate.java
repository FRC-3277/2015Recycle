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
// Gone from 2015 build season.
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

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
    
    // Pneumatic Control
    FRCPneumaticControl PneumaticControl;
    
    // Shooter System
    FRCShooterControl ShooterControl;
    
    // Picker System
    FRCPickerControl PickerControl;
    Timer TeleopInitTimer;
    
    // Driver Station
    DriverStationScreen DriverScreen;
    
    // Autonomous
    Timer AutonomousTimer;
    boolean HasAutonomouslyShot;
    
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // Input
        joystick = new Joystick(1);
        controller = new Joystick(2);
        
        // Drive
        DriveControl = new FRCDriveControl();
        
        // Pneumatic Control
        PneumaticControl = new FRCPneumaticControl();
        
        // Shoot
        ShooterControl = new FRCShooterControl();
        
        // Pick up
        PickerControl = new FRCPickerControl();
        TeleopInitTimer = new Timer();
        
        // Driver Station
        DriverScreen = new DriverStationScreen();
        
    }

    /**
     * This function is called when the autonomous period starts.
     */
    public void autonomousInit()
    {
        AutonomousTimer = new Timer();
        AutonomousTimer.start();
        ShooterControl.Pressurize();
        HasAutonomouslyShot = false;
    }
    public void disabledInit(){
    }
    public void disabledPeriodic(){
        PickerControl.Control(false, true, false, false);
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        
        
        double autonomousTime = AutonomousTimer.get();
        PickerControl.Control(true, false, false, false);
        if (autonomousTime < 4.0)
        {
            ShooterControl.Shoot(false);
            ShooterControl.Pressurize();
            DriveControl.Drive(0.0, -0.300, 0.0); 
        }
        if (autonomousTime > 4.0 && autonomousTime < 4.5)
        {
            ShooterControl.Shoot(false);
            ShooterControl.Pressurize();
            DriveControl.Drive(0.0, 0.0, 0.0);
        }
        if (autonomousTime > 4.5)
        {
            if (!HasAutonomouslyShot && ShooterControl.ReadyToShoot())
            {
                HasAutonomouslyShot = true;
                ShooterControl.Shoot(true);
            }
            else
            {
                ShooterControl.Shoot(false);
            }
        }
        
        DriverScreen.printLine(1, "Autonomous Timer: " + autonomousTime);
        ShooterControl.Output(DriverScreen);
    }

    public void teleopInit() {
        PneumaticControl.Start();
        ShooterControl.Pressurize();
        TeleopInitTimer.reset();
        TeleopInitTimer.start();
        PickerControl.Control(false, true, false, false);
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        // Drive system
        Watchdog.getInstance().feed();
        DriveControl.Drive(joystick.getX(), joystick.getY(), joystick.getTwist(), (!joystick.getRawButton(2) ? 0.5 : 1.0));
        Watchdog.getInstance().feed();
        
        // Shooting system
//        Watchdog.getInstance().feed();
//        ShooterControl.Unlatch(joystick.getRawButton(11));
//        Watchdog.getInstance().feed();
//        ShooterControl.Latch(joystick.getRawButton(12));
//        Watchdog.getInstance().feed();
//        ShooterControl.Shoot(joystick.getRawButton(9));
//        Watchdog.getInstance().feed();
//        ShooterControl.RetractShooter(joystick.getRawButton(10));
//        Watchdog.getInstance().feed();
        
        // Autoshooter
        Watchdog.getInstance().feed();
        ShooterControl.Shoot(joystick.getRawButton(3));
        ShooterControl.Output(DriverScreen);
        Watchdog.getInstance().feed();
        
        // Picking-up system
        Watchdog.getInstance().feed();
        if (TeleopInitTimer.get() < 1.0)
            PickerControl.Control(false, true, false, false);
        else
            PickerControl.Control(controller.getRawButton(1), controller.getRawButton(4), controller.getRawButton(3), controller.getRawButton(2));
        Watchdog.getInstance().feed();
        
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
        
        ShooterControl.Output(DriverScreen);
        //DriverScreen.printLine(6, "PS: " + extendForward + "|" + extendReverse + "|" + retractForward + "|" + retractReverse);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}

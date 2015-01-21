/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team3277.robot;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Robot_2
 */
public class FRCShooterControl
{
    private Solenoid ShooterLeftSolenoid;
    private Solenoid ShooterRightSolenoid;
    private Solenoid LatchSolenoid;
    private DoubleSolenoid PressureControlSolenoid;
    
    private final SolenoidChannel LatchChannel = new SolenoidChannel(1, 4);
    private final SolenoidChannel ShooterLeftChannel = new SolenoidChannel(1, 5);
    private final SolenoidChannel ShooterRightChannel = new SolenoidChannel(1, 6);
    private final DoubleSolenoidChannel PressureControlChannel = new DoubleSolenoidChannel(2, 3, 6);

    private final int PressureControlA = 9;
    private final int PressureControlB = 10;
            
    private final int LatchLimitSwitchChannel = 2;
    private final int PressureSensorChannel = 1;
     
    private final double NORMAL_SHOT_PRESSURE = 60.0;
    private final double SOFT_SHOT_PRESSURE = 20.0;
    private final double LATCH_RESET_DELAY = 500;
      

    private boolean IsShooting;
    
    private String SensorValue;
    
    private AnalogChannel ShooterPressureSensor;
    
    private DigitalInput LatchLimitSwitch;
   
    private Timer ShootingTimer;
    
    public FRCShooterControl()
    {        
        IsShooting = false;
         
        LatchLimitSwitch = new DigitalInput(LatchLimitSwitchChannel);
  
        LatchSolenoid = new Solenoid(LatchChannel.Slot, LatchChannel.Channel);
        ShooterLeftSolenoid = new Solenoid(ShooterLeftChannel.Slot, ShooterLeftChannel.Channel);
        ShooterRightSolenoid = new Solenoid(ShooterRightChannel.Slot, ShooterRightChannel.Channel);
        PressureControlSolenoid = new DoubleSolenoid(PressureControlChannel.Slot, PressureControlChannel.Channel, PressureControlChannel.SecondChannel);

        ShooterPressureSensor = new AnalogChannel(PressureSensorChannel);
        
        PressureControlSolenoid.set(DoubleSolenoid.Value.kForward);
        
        ShootingTimer = new Timer();
        
    }
    
    public boolean IsLimitSwitchPressed()
    {
        // The limit switch returns 1 if it's open (not being pressed) and 0 if it's closed (being pressed).
        // ...So we need to invert it in order to make sense.
        return !LatchLimitSwitch.get();
    }
  
    private double GetPressureSensorValue()
    {        
        // * 30
        return ShooterPressureSensor.getVoltage() * 30;
    }
            
    public void Pressurize() 
    {
        ShooterLeftSolenoid.set(true);
        ShooterRightSolenoid.set(true);
    }
   
    public void Latch()
    {
        LatchSolenoid.set(false);
    }
        
    public void Unlatch()
    {
        LatchSolenoid.set(true);
    }
    
    public void RetractShooter()
    {
        ShooterLeftSolenoid.set(false);
        ShooterRightSolenoid.set(false);  
    }
    
    public void Output(DriverStationScreen dss)
    {
        SensorValue = String.valueOf(GetPressureSensorValue());
        DriverStationScreen.printLine(2, "Pressure: " + SensorValue);
        DriverStationScreen.printLine(3, "Shooting: " + IsShooting);
        DriverStationScreen.printLine(4, "Timer: " + ShootingTimer.get());
        DriverStationScreen.printLine(5, "Ready to shoot: " + ReadyToShoot());
        //dss.printLine(4, "Compressor running: " + compressor.enabled());
    }

    public FRCShooterControl(Solenoid ShooterLeftSolenoid, Solenoid ShooterRightSolenoid, Solenoid LatchSolenoid, DoubleSolenoid PressureControlSolenoid, boolean IsShooting, String SensorValue, AnalogChannel ShooterPressureSensor, DigitalInput LatchLimitSwitch, Timer ShootingTimer) {
        this.ShooterLeftSolenoid = ShooterLeftSolenoid;
        this.ShooterRightSolenoid = ShooterRightSolenoid;
        this.LatchSolenoid = LatchSolenoid;
        this.PressureControlSolenoid = PressureControlSolenoid;
        this.IsShooting = IsShooting;
        this.SensorValue = SensorValue;
        this.ShooterPressureSensor = ShooterPressureSensor;
        this.LatchLimitSwitch = LatchLimitSwitch;
        this.ShootingTimer = ShootingTimer;
    }
    
    public boolean ReadyToShoot()
    {
        return !IsShooting && GetPressureSensorValue() > 55.0;
    }
    
    public void Shoot(boolean shoot)
    {
        if (!IsShooting && shoot)
        {
            IsShooting = true;
            ShootingTimer.reset();
            ShootingTimer.start();
        }
        
        double shootTimer = ShootingTimer.get();
        if (IsShooting)
        {
            if (shootTimer < 1.0)
            {
                Unlatch();
            }
            if (shootTimer > 1.0)
            {
                RetractShooter();
                Latch();
                if (IsLimitSwitchPressed())
                {
                    IsShooting = false;
                }
            }
        }
        else{
            // Pressurize the shooting mechanism
            Pressurize();
        }
    }
    
//    public void AutoShooter(boolean shooter)
//    {
//        
//        
//        if(shooter && !LatchLimitSwitch.get())
//        {
//            Unlatch();
//            
//            delayTimer.delay(1);
//            Latch();
//            delayTimer.delay(1);
//            
//            RetractShooter();
//        }
//        else if(!LatchLimitSwitch.get())
//        {
//            ShooterLeftSolenoid.set(true);
//            ShooterRightSolenoid.set(true);
//        }
//        else if(LatchLimitSwitch.get())
//        {
//            Latch();
//            RetractShooter();
//        }
//    }
}

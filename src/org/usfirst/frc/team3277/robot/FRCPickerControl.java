/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team3277.robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANTimeoutException;


/**
 *
 * @author Robot_2
 */
public class FRCPickerControl {
    //private DoubleSolenoid PickerRetractSolenoid;
    //private DoubleSolenoid PickerExtendSolenoid;
    
    private Solenoid PickerRetractForwardSolenoid;
    private Solenoid PickerRetractReverseSolenoid;
    private Solenoid PickerExtendForwardSolenoid;
    private Solenoid PickerExtendReverseSolenoid;

    private final int BallPickerChannel;
    private CANJaguar BallPicker;
    private Timer ExtendTimer;
    private boolean IsExtending;

    //private DoubleSolenoidChannel PickerRetractSolenoidChannel = new DoubleSolenoidChannel(2, 1, 4);
    //private DoubleSolenoidChannel PickerExtendSolenoidChannel = new DoubleSolenoidChannel(2, 2, 5);
    
    private SolenoidChannel PickerRetractForwardChannel = new SolenoidChannel(2, 1);
    private SolenoidChannel PickerRetractReverseChannel = new SolenoidChannel(2, 4);
    private SolenoidChannel PickerExtendForwardChannel = new SolenoidChannel(2, 2);
    private SolenoidChannel PickerExtendReverseChannel = new SolenoidChannel(2, 5);
    
    
    public FRCPickerControl()
    {
       BallPickerChannel = 5;
       
       //PickerRetractSolenoid = new DoubleSolenoid(PickerRetractSolenoidChannel.Slot, PickerRetractSolenoidChannel.Channel, PickerRetractSolenoidChannel.SecondChannel);
       //PickerExtendSolenoid  = new DoubleSolenoid(PickerExtendSolenoidChannel.Slot, PickerExtendSolenoidChannel.Channel, PickerExtendSolenoidChannel.SecondChannel);
       
       PickerRetractForwardSolenoid = new Solenoid(PickerRetractForwardChannel.Slot, PickerRetractForwardChannel.Channel);
       PickerRetractReverseSolenoid = new Solenoid(PickerRetractReverseChannel.Slot, PickerRetractReverseChannel.Channel);
       PickerExtendForwardSolenoid = new Solenoid(PickerExtendForwardChannel.Slot, PickerExtendForwardChannel.Channel);
       PickerExtendReverseSolenoid = new Solenoid(PickerExtendReverseChannel.Slot, PickerExtendReverseChannel.Channel);
       
       BallPicker = Jaguar.initJag(BallPickerChannel);
       IsExtending = false;
       ExtendTimer = new Timer();
       
    }

    private void Extend()
    {
        if (!IsExtending)
        {
            IsExtending = true;
            ExtendTimer.reset();
            ExtendTimer.start();
        }
            PickerExtendForwardSolenoid.set(false); 
            PickerExtendReverseSolenoid.set(true);
            PickerRetractForwardSolenoid.set(true);
            PickerRetractReverseSolenoid.set(false);
//            PickerExtendSolenoid.set(DoubleSolenoid.Value.kForward);
//            PickerRetractSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    private void Retract()
    {
        IsExtending = false;
        
        PickerExtendForwardSolenoid.set(true); 
        PickerExtendReverseSolenoid.set(false);
        PickerRetractForwardSolenoid.set(false);
        PickerRetractReverseSolenoid.set(true);
        
//        PickerRetractSolenoid.set(DoubleSolenoid.Value.kForward);
//        PickerExtendSolenoid.set(DoubleSolenoid.Value.kReverse);
    }

    private void Float()
    {
        PickerExtendForwardSolenoid.set(true);
        PickerExtendReverseSolenoid.set(false);
        PickerRetractForwardSolenoid.set(false);
        PickerRetractReverseSolenoid.set(false);

        //PickerRetractSolenoid.set(DoubleSolenoid.Value.kOff);
        //PickerExtendSolenoid.set(DoubleSolenoid.Value.kOff);
    }
    
    public void ExtendForwardControl(boolean extend)
    {
        PickerExtendForwardSolenoid.set(extend);
    }
    public void ExtendReverseControl(boolean reverse)
    {
        PickerExtendReverseSolenoid.set(reverse);
    }
    public void RetractForwardControl(boolean retract)
    {
        PickerRetractForwardSolenoid.set(retract);
    }
    public void RetractReverseControl(boolean reverse)
    {
        PickerRetractReverseSolenoid.set(reverse);
    }

    public void Control(boolean extend, boolean retract, boolean pickerIn, boolean pickerOut)
    {
        
        if (retract)
        {
            Retract();
        }
        else
        {
      
            if (extend && !IsExtending)
            {
                Extend();
            }

            if (IsExtending)
            {
                if (ExtendTimer.get() < 2.0)
                {
                    Extend();
                }
                else
                {
                    Float();
                }
            }
        }
     
          try
          {
            if (pickerIn)
            {
                BallPicker.setX(-1);
            } else if(pickerOut){
                BallPicker.setX(1);
            }else
            {
                BallPicker.setX(0);
            }
        } catch (CANTimeoutException ex)
        {
            System.out.println("Error Setting Picker Speed: " + ex);
        }
    }
}

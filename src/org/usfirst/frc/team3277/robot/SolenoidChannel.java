/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team3277.robot;

/**
 *
 * @author Robot_2
 */
public class SolenoidChannel {
    public int Slot;
    public int Channel;
    
    public SolenoidChannel(int slot, int channel)
    {
        Slot = slot;
        Channel = channel;
    }
}

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
public class DoubleSolenoidChannel extends SolenoidChannel {
    public int SecondChannel;
    public DoubleSolenoidChannel(int slot, int firstChannel, int secondChannel) {
        super(slot, firstChannel);
        SecondChannel = secondChannel;
    }
    
}

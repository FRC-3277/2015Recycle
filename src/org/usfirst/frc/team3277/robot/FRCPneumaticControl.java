/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team3277.robot;;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 * @author Robot_2
 */
public class FRCPneumaticControl {
    private int PressureSwitchChannel = 1;
    private int CompressorRelayChannel = 2;
    private Compressor compressor;
    private int IsCompressorOn;
    
    public FRCPneumaticControl()
    {    
        try
        {
            compressor = new Compressor(PressureSwitchChannel, CompressorRelayChannel);            
            System.out.println("Compressor is set up correctly ");

        }
        catch (Exception ex)
        {
            System.out.println("Error initializing compressor: " + ex.getMessage());
        }
    }
    
    public void Start()
    {
        compressor.setRelayValue(Relay.Value.kOn);
        compressor.start();

    }
    
    public void Stop()
    {
        compressor.stop();  
        compressor.setRelayValue(Relay.Value.kOff);

    }
    
//    public void Output(DriverStationScreen dss)
//    {
//        dss.printLine(3, "Pressure Switch Value: " + compressor.getPressureSwitchValue());
//        dss.printLine(4, "Compressor running: " + compressor.enabled());
//    }
}

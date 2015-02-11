package org.usfirst.frc.team3277.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Parity;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.SerialPort.StopBits;

/*
 * This method could have worked according to the documentation, but ended up throwing errors
 * which could not be identified as to the cause since the error message was the empty
 * string.  We resorted to converting over to reading the Arduino from the I2C port.
 */

/**
 * Python (via Raspberry Pi) to Arduino example: http://www.bitsharr.com/rtxu6LvK
 */
//public class ArduinoSerialReadNative extends Subsystem {
//	// Subsystem devices
//	SerialPort usbSerialPort;
//	
//	static private Logger lumberjack;
//   
//    public void initDefaultCommand() {
//        // Set the default command for a subsystem here.
//        //setDefaultCommand(new MySpecialCommand());
//    }
//    
//    public ArduinoSerialReadNative()
//    {
//    	lumberjack = new Logger();
//    	try
//    	{
//    		usbSerialPort.enableTermination();
//    		usbSerialPort = new SerialPort(9600, Port.kUSB, 8, Parity.kNone, StopBits.kOne);
//    	}
//    	catch(Exception e)
//    	{
//    		lumberjack.dashLogError("ArduinoSerialRead", e.toString());
//    		usbSerialPort.free();
//    	}
//    }
//    
//    /**
//	 * The log method puts information of interest from the Talon subsystem to the SmartDashboard.
//	 */
//    public void dashLog() 
//    {
//    	String arduinoSerialMessage = "";
//    	try 
//    	{
//			arduinoSerialMessage = usbSerialPort.readString();
//			lumberjack.dashLogString("UsbSerialRead", arduinoSerialMessage);
//		} 
//    	catch (Exception e) 
//    	{
//    		lumberjack.dashLogString("ArduinoSerialRead", e.toString());
//    		usbSerialPort.free();
//		}
//    }
//}
//

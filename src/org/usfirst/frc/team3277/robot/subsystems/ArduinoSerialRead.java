package org.usfirst.frc.team3277.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

/**
 * Python (via Raspberry Pi) to Arduino example: http://www.bitsharr.com/rtxu6LvK
 */
public class ArduinoSerialRead extends Subsystem {
	// Subsystem devices
	SerialPort usbSerialPort;
	
	static private Logger lumberjack;
   
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public ArduinoSerialRead()
    {
    	lumberjack = new Logger();
    	usbSerialPort = new SerialPort(9600, Port.kUSB);
    }
    
    /**
	 * The log method puts information of interest from the Talon subsystem to the SmartDashboard.
	 */
    public void dashLog() 
    {
    	String arduinoSerialMessage;
    	arduinoSerialMessage = usbSerialPort.readString();
        lumberjack.dashLogString("UsbSerialRead", arduinoSerialMessage);
    }
}


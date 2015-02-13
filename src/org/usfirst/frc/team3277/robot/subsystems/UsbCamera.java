package org.usfirst.frc.team3277.robot.subsystems;

import org.usfirst.frc.team3277.robot.RobotMap;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class UsbCamera extends Subsystem {
	// Subsystem devices
	CameraServer server;
	
	static private Logger lumberjack;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public UsbCamera()
    {
    	lumberjack = new Logger();
    	
    	try
    	{
	    	server = CameraServer.getInstance();
	        server.setQuality(RobotMap.USB_CAMERA_QUALITY);
	        //the camera name (ex "cam0") can be found through the roborio web interface
	        server.startAutomaticCapture(RobotMap.USB_CAMERA_INSTANCE);
    	}
    	catch (Exception e)
    	{
    		lumberjack.dashLogError("UsbCamera", e.getMessage());
    	}
    }
    
    /**
	 * The log method puts information of interest from the Usb Camera subsystem to the SmartDashboard.
	 */
    public void dashLog() 
    {
    	//lumberjack.dashLogNumber("UsbCamera", );
    }
}


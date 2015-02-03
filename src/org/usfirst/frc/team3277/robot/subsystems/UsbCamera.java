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

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public UsbCamera()
    {
    	super();
    	server = CameraServer.getInstance();
        server.setQuality(RobotMap.USB_CAMERA_QUALITY);
        //the camera name (ex "cam0") can be found through the roborio web interface
        server.startAutomaticCapture(RobotMap.USB_CAMERA_INSTANCE);
    }
    
    /**
	 * The log method puts information of interest from the USB Camera subsystem to the SmartDashboard.
	 */
    public void log() 
    {
        //SmartDashboard.putData("Key", value);
    }
}


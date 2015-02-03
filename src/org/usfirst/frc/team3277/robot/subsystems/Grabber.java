package org.usfirst.frc.team3277.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The grabber is the subsystem that interacts with the tote(s).
 */
public class Grabber extends Subsystem {
	// Subsystem devices
    
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public Grabber()
	{
		super();
	}
    
    /**
	 * The log method puts information of interest from the Grabber subsystem to the SmartDashboard.
	 */
    public void log() 
    {
        //SmartDashboard.putData("Key", value);
    }
}


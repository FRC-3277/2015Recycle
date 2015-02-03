package org.usfirst.frc.team3277.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Accelerometer extends Subsystem {
	// Subsystem devices
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public Accelerometer()
	{
		super();
	}
    
    /**
	 * The log method puts information of interest from the Accelerometer subsystem to the SmartDashboard.
	 */
    public void dashLog() 
    {
        //SmartDashboard.putData("Key", value);
    }
}


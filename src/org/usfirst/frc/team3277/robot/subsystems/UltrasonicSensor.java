package org.usfirst.frc.team3277.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class UltrasonicSensor extends Subsystem
{
	// Subsystem devices

	static private Logger lumberjack;

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public UltrasonicSensor()
	{
		lumberjack = new Logger();
	}

	/**
	 * The log method puts information of interest from the Ultrasonic Sensor
	 * subsystem to the SmartDashboard.
	 */
	public void dashLog()
	{
		// SmartDashboard.putData("Key", value);
	}
}

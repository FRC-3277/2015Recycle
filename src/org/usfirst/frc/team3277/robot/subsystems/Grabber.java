package org.usfirst.frc.team3277.robot.subsystems;

import org.usfirst.frc.team3277.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The grabber is the subsystem that interacts with the tote(s).
 */
public class Grabber extends Subsystem
{
	// Subsystem devices
	public CANTalon grabberMotor;

	static private Logger lumberjack;

	public void initDefaultCommand()
	{

	}

	public Grabber()
	{
		lumberjack = new Logger();

		try
		{
			grabberMotor = Talon.initTalon(RobotMap.GRABBER_MOTOR);
			// Override the default behavior which is to enable.
			grabberMotor.enableBrakeMode(true);
			grabberMotor.enableControl();

		} catch (Exception e)
		{
			lumberjack.dashLogError("Grabber", "Motor controller failure: " + e.getMessage());
		}
	}

	public void activelyOpenGrabber()
	{
		// - = Open
		grabberMotor.set(-RobotMap.GRABBER_MOTOR_SPEED);
	}

	public void activelyCloseGrabber()
	{
		// + = Close
		grabberMotor.set(RobotMap.GRABBER_MOTOR_SPEED);
	}

	public void stopGrabber()
	{
		grabberMotor.set(0);
	}

	/**
	 * The log method puts information of interest from the Grabber subsystem to
	 * the SmartDashboard.
	 */
	public void dashLog()
	{
		// lumberjack.dashLogNumber("Grabber", );
	}
}

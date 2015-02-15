package org.usfirst.frc.team3277.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3277.robot.Robot;
import org.usfirst.frc.team3277.robot.RobotMap;

import java.lang.Math;

/**
 * Uses the ADW22307 gyro and the built in wpilibj logic adding additional
 * functionality such as logging.
 */
public class GyroSensor extends Subsystem
{
	// Subsystem devices

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Gyro gyro;

	// When the Gyro initializes, it makes this zero
	double rotationDegrees = 0;

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public GyroSensor(int channel, double sensitivity)
	{
		// Configure gyro
		gyro = new Gyro(channel);
		if (Robot.isReal())
		{
			gyro.setSensitivity(sensitivity); // TODO: Handle more gracefully?
		}
		LiveWindow.addSensor("DriveTrain", "Gyro", gyro);
	}

	/**
	 * @return The current angle of the drivetrain relative to the the time the
	 *         bot was powered up.
	 */
	public double getAngle()
	{
		return gyro.getAngle();
	}

	/**
	 * @return The current angle of the drivetrain relative to the the time the
	 *         bot was powered up in a human readable format (0-360 degrees).
	 */
	public double getHumanReadableAngle()
	{
		// Absolute value desired since turning counterclockwise returns
		// negative degrees after passing 0. Modulus to keep it relative to full
		// 360 degrees
		// that can represent
		return (Math.abs(gyro.getAngle()) % 360);
	}

	public void reset()
	{
		gyro.reset();
	}

	/**
	 * The log method puts information of interest from the GyroSensor subsystem
	 * to the SmartDashboard.
	 */
	public void dashLog()
	{
		SmartDashboard.putNumber("Angle", this.getAngle());
	}
}

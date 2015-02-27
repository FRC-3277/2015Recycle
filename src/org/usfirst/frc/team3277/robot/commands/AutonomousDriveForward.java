package org.usfirst.frc.team3277.robot.commands;

import org.usfirst.frc.team3277.robot.Robot;
import org.usfirst.frc.team3277.robot.RobotMap;
import org.usfirst.frc.team3277.robot.subsystems.Logger;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousDriveForward extends Command
{
	Logger lumberjack;
	boolean lidarHasFailed = false;
	double distanceLidarDetected;
	Timer debugTimer;

	public AutonomousDriveForward()
	{
		lumberjack = new Logger();

		setTimeout(RobotMap.AUTONOMOUS_TIMEOUT_DRIVE_TRAIN_DRIVE_FORWARD);

		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		debugTimer = new Timer();
		debugTimer.start();
		
		// Determine if Lidar is functional. If not then stick with timeout already assigned. Otherwise override.
		try
		{
			distanceLidarDetected = Robot.lidarSensor.getDistance();
		} catch (Exception e)
		{
			lidarHasFailed = true;
			Robot.lumberjack.dashLogError("AutonomousDriveForward", "Lidar failed to initialize.  Default to time mode.");
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (!lidarHasFailed)
		{
			distanceLidarDetected = Robot.lidarSensor.getDistance();
			if (distanceLidarDetected < RobotMap.AUTONOMOUS_DRIVE_TRAIN_TRAVEL_DISTANCE)
			{
				distanceLidarDetected = Robot.lidarSensor.getDistance();
				Robot.drivetrain.mechanumDriveMoveForward();
			} else
			{
				Robot.drivetrain.stopMecanumDrive();
			}
		} else
		{
			Robot.drivetrain.mechanumDriveMoveForward();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
			return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.drivetrain.stopMecanumDrive();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		Robot.drivetrain.stopMecanumDrive();
	}
}
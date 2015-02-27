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
	double distanceLidarDetected;
	Timer debugTimer;

	public AutonomousDriveForward()
	{
		lumberjack = new Logger();
		
		// Initially setup the timeout with intention of overriding in the event
		// that Lidar proves viable.
		setTimeout(RobotMap.AUTONOMOUS_TIMEOUT_DRIVE_TRAIN_DRIVE_FORWARD);

		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		debugTimer = new Timer();
		debugTimer.start();
		//lumberjack.dashLogDebug("AutonomousDriveFwdTimer", debugTimer.get())
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		Robot.drivetrain.mechanumDriveMoveForward();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{

		Robot.drivetrain.stopMecanumDrive();
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

package org.usfirst.frc.team3277.robot.commands;

import org.usfirst.frc.team3277.robot.Robot;
import org.usfirst.frc.team3277.robot.RobotMap;
import org.usfirst.frc.team3277.robot.subsystems.Logger;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command allows joystick to drive the robot. It is always running except
 * when interrupted by another command.
 */
public class DriveWithJoystick extends Command
{

	double currentX, currentY, currentTwist, previousX = 0, previousY = 0, previousTwist = 0, finesseMultiplier = 1, throttleMultiplier = 1; //, governorX = RobotMap.GOVERNOR_STARTING_PERCENT, governorY = RobotMap.GOVERNOR_STARTING_PERCENT, governorTwist = RobotMap.GOVERNOR_STARTING_PERCENT;
//	Timer timerX, timerY, timerTwist;
	boolean finesseButton;
	
	Logger lumberjack;
	
	public DriveWithJoystick()
	{
		requires(Robot.drivetrain);		
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		lumberjack = new Logger();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		currentX = Robot.operatorInterface.getControllerX();
		currentY = Robot.operatorInterface.getControllerY();
		currentTwist = Robot.operatorInterface.getControllerTwist();
		finesseButton = Robot.operatorInterface.getFinesseButton();
		finesseMultiplier = RobotMap.FINESSE_VELOCITY_PERCENTAGE;
		throttleMultiplier = RobotMap.STANDARD_VELOCITY_PERCENTAGE;
		Robot.drivetrain.mecanumDrive(currentX, currentY, currentTwist, (finesseButton ? finesseMultiplier : throttleMultiplier));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.drivetrain.stopTankDrive();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
	
	/**
	 * The log method puts information of interest from the DriveWithJoystick subsystem
	 * to the SmartDashboard.
	 */
	public void dashLog()
	{
		//lumberjack.dashLogDebug("DriveWithJoystick:", Float.toString(Robot.drivetrain.imu.getHeading()));
	}
}

package org.usfirst.frc.team3277.robot.commands;

import org.usfirst.frc.team3277.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This command allows joystick to drive the robot. It is always running except
 * when interrupted by another command.
 */
public class DriveWithJoystick extends Command
{
	double currentX, currentY, currentTwist, previousX = 0, previousY = 0, previousTwist = 0;
	Timer timer;

	public DriveWithJoystick()
	{
		timer = new Timer();
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		timer.reset();
	    timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		currentX = Robot.operatorInterface.getControllerX();
		currentY = Robot.operatorInterface.getControllerY();
		currentTwist = Robot.operatorInterface.getControllerTwist();
		
		if (currentX <= previousX || currentY <= previousY || currentTwist <= previousTwist)
		{
			timer.reset();
			Robot.operatorInterface.getGovernorTimestamp(timer);
		}
		
		previousX = currentX;
		previousY = currentY;
		previousTwist = currentTwist;
		
		// Robot.drivetrain.arcadeDrive(Robot.operatorInterface.getJoystick());
		//Robot.drivetrain.mecanumDrive(Robot.operatorInterface.getJoystickX(), Robot.operatorInterface.getJoystickY(),	Robot.operatorInterface.getJoystickTwist(), 1.0);// (!joystick.getRawButton(2)
		Robot.drivetrain.mecanumDrive(currentX, currentY, currentTwist, 1.0);// (!joystick.getRawButton(2)
																	// ? 0.80 :
																	// 1.0));
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
}

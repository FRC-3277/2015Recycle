package org.usfirst.frc.team3277.robot.commands;

import org.usfirst.frc.team3277.robot.Robot;
import org.usfirst.frc.team3277.robot.RobotMap;
import org.usfirst.frc.team3277.robot.subsystems.Logger;

import edu.wpi.first.wpilibj.Timer;
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
	static private Logger lumberjack;
	

	public DriveWithJoystick()
	{
		lumberjack = new Logger();
//		timerX = new Timer();
//		timerY = new Timer();
//		timerTwist = new Timer();
//		
//		timerX.reset();
//		timerX.start();
//		timerY.reset();
//		timerY.start();
//		timerTwist.reset();
//		timerTwist.start();
		
		requires(Robot.drivetrain);
		
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		
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
//		if (currentX <= previousX)
//		{
//			timerX.reset();
//			governorX = Robot.operatorInterface.governor(governorX, timerX);
//		}
//		else
//		{
//			governorX = RobotMap.GOVERNOR_ACCELERATION_PERCENTAGE;
//		}
//		
//		if (currentY <= previousY)
//		{
//			timerY.reset();
//			governorY = Robot.operatorInterface.governor(timerY);
//		}
//		
//		if (currentTwist <= previousTwist)
//		{
//			timerTwist.reset();
//			governorTwist = Robot.operatorInterface.governor(timerTwist);
//		}
		
//		previousX = currentX;
//		previousY = currentY;
//		previousTwist = currentTwist;
		
	//	dashLog();
		
		// Robot.drivetrain.arcadeDrive(Robot.operatorInterface.getJoystick());
		//Robot.drivetrain.mecanumDrive(Robot.operatorInterface.getJoystickX(), Robot.operatorInterface.getJoystickY(),	Robot.operatorInterface.getJoystickTwist(), 1.0);// (!joystick.getRawButton(2)
		Robot.drivetrain.mecanumDrive(currentX, currentY, currentTwist, (finesseButton ? finesseMultiplier : throttleMultiplier)); //1.0);// (!joystick.getRawButton(2)
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
	
	/**
	 * The log method puts information of interest from the DriveWithJoystick subsystem
	 * to the SmartDashboard.
	 */
	public void dashLog()
	{
	//	lumberjack.dashLogError("DriveWithJoystick:", Double.toString(timerX.get()));
	}
}

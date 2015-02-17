package org.usfirst.frc.team3277.robot.commands;

import org.usfirst.frc.team3277.robot.Robot;
import org.usfirst.frc.team3277.robot.RobotMap;
import org.usfirst.frc.team3277.robot.subsystems.Logger;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turn the robot a specified number of degrees.
 */
public class AutonomousRobotTurn extends Command {

	Logger lumberjack;
	double currentAngleInDegrees;
	
    public AutonomousRobotTurn() {
    	lumberjack = new Logger();
    	
    	setTimeout(RobotMap.AUTONOMOUS_TIMEOUT_DRIVE_TRAIN_TURN);
    	
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	currentAngleInDegrees = Robot.drivetrain.gyro.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    @SuppressWarnings("unused")
	protected void execute() {
    	if (RobotMap.AUTONOMOUS_DRIVE_TRAIN_TURN_DEGREES > 0)
    	{
    		// CW Turning
    		while (currentAngleInDegrees + RobotMap.AUTONOMOUS_DRIVE_TRAIN_TURN_DEGREES < Robot.drivetrain.gyro.getAngle())
    		{
    			Robot.drivetrain.mecanumDriveTurn(RobotMap.AUTONOMOUS_DRIVE_TRAIN_TURN_SPEED);
    		}
    	}
    	else
    	{
    		// CCW Turning
    		while (currentAngleInDegrees + RobotMap.AUTONOMOUS_DRIVE_TRAIN_TURN_DEGREES > Robot.drivetrain.gyro.getAngle())
    		{
    			Robot.drivetrain.mecanumDriveTurn(-RobotMap.AUTONOMOUS_DRIVE_TRAIN_TURN_SPEED);
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopMecanumDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stopMecanumDrive();
    }
}

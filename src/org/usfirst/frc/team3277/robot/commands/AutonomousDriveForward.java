package org.usfirst.frc.team3277.robot.commands;

import org.usfirst.frc.team3277.robot.Robot;
import org.usfirst.frc.team3277.robot.RobotMap;
import org.usfirst.frc.team3277.robot.subsystems.Logger;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousDriveForward extends Command {
	Logger lumberjack;
	boolean lidarHasFailed = false;
	double distanceLidarDetected;
	
    public AutonomousDriveForward() {
    	lumberjack = new Logger();
    	
    	// Initially setup the timeout with intention of overriding in the event that Lidar proves viable.
    	setTimeout(RobotMap.AUTONOMOUS_TIMEOUT_DRIVE_TRAIN_DRIVE_FORWARD);
    	
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// Determine if Lidar is functional.  If not then stick with timeout already assigned.  Otherwise override.
    	try
		{
    		distanceLidarDetected = Robot.lidarSensor.getDistance();
    		// In case null is returned
			if (Robot.lidarSensor.getDistance() > 0)
			{
				setTimeout(RobotMap.AUTONOMOUS_TIMEOUT_DRIVE_TRAIN_DRIVE_FORWARD_lIDAR_WORKS_OVERRIDE);
			}
		} catch (Exception e)
		{
			lidarHasFailed = true;
			Robot.lumberjack.dashLogError("AutonomousDriveForward", "Lidar failed to initialize.  Default to time mode.");
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!lidarHasFailed)
    	{
    		distanceLidarDetected = Robot.lidarSensor.getDistance();
    		if (distanceLidarDetected < RobotMap.AUTONOMOUS_DRIVE_TRAIN_TRAVEL_DISTANCE)
    		{
    			distanceLidarDetected = Robot.lidarSensor.getDistance();
    			Robot.drivetrain.mechanumDriveMoveForward();
    		}
    		else
    		{
    			Robot.drivetrain.stopMecanumDrive();
    		}
    	}
    	else
    	{
    		Robot.drivetrain.mechanumDriveMoveForward();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (!lidarHasFailed)
    	{
    		return false;
    	}
    	else
    	{
    		return isTimedOut();
    	}
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

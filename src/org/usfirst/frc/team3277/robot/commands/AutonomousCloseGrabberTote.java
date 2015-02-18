package org.usfirst.frc.team3277.robot.commands;

import org.usfirst.frc.team3277.robot.Robot;
import org.usfirst.frc.team3277.robot.RobotMap;
import org.usfirst.frc.team3277.robot.subsystems.Logger;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Step 1.  Close z grabber on z tote!
 */
public class AutonomousCloseGrabberTote extends Command {
	
	Logger lumberjack;
	
    public AutonomousCloseGrabberTote() {
    	lumberjack = new Logger();
    	
    	setTimeout(RobotMap.AUTONOMOUS_TIMEOUT_GRABBER_CLOSE_TOTE);
    	
    	requires(Robot.grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.grabber.activelyCloseGrabber();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	dashLog();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.grabber.stopGrabber();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.grabber.stopGrabber();
    }
    
    /**
	 * The log method puts information of interest from the AutonomousCloseGrabberTote Command to the RioLog.
	 */
	public void dashLog()
	{
		lumberjack.dashLogDebug(getName(), "Seconds to timeout: " + Double.toString((RobotMap.AUTONOMOUS_TIMEOUT_GRABBER_CLOSE_TOTE - timeSinceInitialized())));
	}
}

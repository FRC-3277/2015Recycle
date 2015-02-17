package org.usfirst.frc.team3277.robot.commands;

import org.usfirst.frc.team3277.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Step 1.  Close z grabber on z tote!
 */
public class AutonomousCloseGrabberTote extends Command {
	
    public AutonomousCloseGrabberTote() {    	
    	requires(Robot.grabber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.grabber.activelyCloseGrabber();
    	System.out.println("m_timeout is " + m_timeout);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.grabber.stopGrabber();
        return false;
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
}

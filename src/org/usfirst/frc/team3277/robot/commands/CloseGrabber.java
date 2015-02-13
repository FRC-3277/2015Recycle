package org.usfirst.frc.team3277.robot.commands;

import org.usfirst.frc.team3277.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseGrabber extends Command {

	public CloseGrabber() {
		requires(Robot.grabber);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.grabber.activelyCloseGrabber();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.grabber.stopGrabber();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		// called when whileHeld ends.
    	Robot.grabber.stopGrabber();
	}
}

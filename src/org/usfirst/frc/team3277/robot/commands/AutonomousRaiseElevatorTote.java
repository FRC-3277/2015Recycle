package org.usfirst.frc.team3277.robot.commands;

import org.usfirst.frc.team3277.robot.Robot;
import org.usfirst.frc.team3277.robot.RobotMap;
import org.usfirst.frc.team3277.robot.subsystems.Logger;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 */
public class AutonomousRaiseElevatorTote extends Command {

	Logger lumberjack;
	
    public AutonomousRaiseElevatorTote() {
    	lumberjack = new Logger();
    	
    	setTimeout(RobotMap.AUTONOMOUS_TIMEOUT_ELEVATOR_RAISE_TOTE);
    	
        requires(Robot.elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.activeRaiseElevator();
    	dashLog();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();			
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stopElevator();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevator.stopElevator();
    }
    
    /**
	 * The log method puts information of interest from the AutonomousRaiseElevatorTote Command to the RioLog.
	 */
	public void dashLog()
	{
		lumberjack.dashLogDebug(getName(), "Seconds to timeout: " + Double.toString((RobotMap.AUTONOMOUS_TIMEOUT_ELEVATOR_RAISE_TOTE - timeSinceInitialized())));
	}
}

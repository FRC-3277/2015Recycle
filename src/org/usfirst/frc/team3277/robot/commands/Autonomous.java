package org.usfirst.frc.team3277.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup
{

	public Autonomous()
	{
		addSequential(new AutonomousCloseGrabberTote());
		addParallel(new AutonomousRaiseElevatorTote());
		addSequential(new AutonomousRobotTurn());
		addSequential(new AutonomousDriveForward());
	}
}

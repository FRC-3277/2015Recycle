package org.usfirst.frc.team3277.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class OpenGrabberCommandGroup extends CommandGroup {
    
    public  OpenGrabberCommandGroup() {
    	addSequential(new OpenGrabber());
    }
}

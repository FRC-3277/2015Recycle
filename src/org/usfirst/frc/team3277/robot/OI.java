package org.usfirst.frc.team3277.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3277.robot.RobotMap;

// Make the commands available
import org.usfirst.frc.team3277.robot.commands.*;

// Subsystem
import org.usfirst.frc.team3277.robot.subsystems.Logger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * This is where UP+UP+DOWN+DOWN+LEFT+RIGHT+LEFT+RIGHT+B+A+SELECT+START means
 * something!
 */
public class OI
{

	// Driver input(s)
	private Joystick joystick;
	private Joystick controller;

	// Joystick buttons
	private JoystickButton buttonGrabberOpen, buttonGrabberClose, buttonElevatorUp, buttonElevatorDown, buttonElevatorHold;

	public static Logger lumberjack;

	public OI()
	{
		lumberjack = new Logger();

		joystick = new Joystick(RobotMap.OPERATOR_INPUT_DEVICE_JOYSTICK);
		controller = new Joystick(RobotMap.OPERATOR_INPUT_DEVICE_CONTROLLER);

		try
		{
			/*
			 * For every physical controller button create a virtual button as a
			 * failsafe in the event that the device fails and the robot cannot
			 * be interacted with.
			 */
			SmartDashboard.putData("Open Grabber", new GrabberOpen());
		} catch (Exception e)
		{
			lumberjack.dashLogError("OI", "SmartDashboard Open Grabber Error: " + e.getMessage());
		}

		try
		{
			SmartDashboard.putData("Close Grabber", new GrabberClose());
		} catch (Exception e)
		{
			lumberjack.dashLogError("OI", "SmartDashboard Close Grabber Error: " + e.getMessage());
		}
		try
		{
			SmartDashboard.putData("Elevator Up", new ElevatorRaise());
		} catch (Exception e)
		{
			lumberjack.dashLogError("OI", "SmartDashboard Raise Elevator Error: " + e.getMessage());
		}
		try
		{
			SmartDashboard.putData("Elevator Down", new ElevatorLower());
		} catch (Exception e)
		{
			lumberjack.dashLogError("OI", "SmartDashboard Lower Elevator Error: " + e.getMessage());
		}

		// Button creation grouped by subsystem
		try
		{
			// Grabber buttons
			buttonGrabberOpen = new JoystickButton(joystick, RobotMap.BUTTON_GRABBER_OPEN);
			buttonGrabberClose = new JoystickButton(joystick, RobotMap.BUTTON_GRABBER_CLOSE);

			// Elevator buttons
			buttonElevatorUp = new JoystickButton(joystick, RobotMap.BUTTON_ELEVATOR_UP);
			buttonElevatorDown = new JoystickButton(joystick, RobotMap.BUTTON_ELEVATOR_DOWN);
			buttonElevatorHold = new JoystickButton(joystick, RobotMap.BUTTON_ELEVATOR_HOLD);
		} catch (Exception e)
		{
			lumberjack.dashLogError("OI", "Button Mapping Error: " + e.getMessage());
		}
		
		// Connect the buttons to commands
		// Grabber listeners
		try
		{
			buttonGrabberOpen.whileHeld(new GrabberOpen());
		} catch (Exception e)
		{
			lumberjack.dashLogError("OI", "Grabber Button Open Grabber Listener Error: " + e.getMessage());
		}
		
		try
		{
			buttonGrabberClose.whileHeld(new GrabberClose());
		} catch (Exception e)
		{
			lumberjack.dashLogError("OI", "Grabber Button Close Grabber Listener Error: " + e.getMessage());
		}

		// Elevator listeners
		try
		{
			buttonElevatorUp.whileHeld(new ElevatorRaise());
		} catch (Exception e)
		{
			lumberjack.dashLogError("OI", "Elevator Button Raise Listener Error: " + e.getMessage());
		}
		
		try
		{
			buttonElevatorDown.whileHeld(new ElevatorLower());
		} catch (Exception e)
		{
			lumberjack.dashLogError("OI", "Elevator Button Lower Listener Error: " + e.getMessage());
		}
		
		try
		{
			buttonElevatorHold.whileHeld(new ElevatorHold());
		} catch (Exception e)
		{
			lumberjack.dashLogError("OI", "Elevator Button Hold Listener Error: " + e.getMessage());
		}
	}

	public Joystick getController()
	{
		return controller;
	}

	public Joystick getJoystick()
	{
		return joystick;
	}

	public double getJoystickX()
	{
		return joystick.getX();
	}

	public double getJoystickY()
	{
		return joystick.getY();
	}

	public double getJoystickTwist()
	{
		return joystick.getTwist();
	}
}

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
 * This is where UP+UP+DOWN+DOWN+LEFT+RIGHT+LEFT+RIGHT+B+A+SELECT+START means something!
 */
public class OI {

	// Driver input(s)
	private Joystick joystick;
	private Joystick controller;
	
	// Joystick buttons
	private JoystickButton buttonGrabberOpen, 
	buttonGrabberClose, buttonElevatorUp, 
	buttonElevatorDown, auto;
	
	public static Logger lumberjack;

	public OI() {
		lumberjack = new Logger();
		
		joystick = new Joystick(RobotMap.OPERATOR_INPUT_DEVICE_JOYSTICK);
		controller = new Joystick(RobotMap.OPERATOR_INPUT_DEVICE_CONTROLLER);
		
		try
		{
			/*
			 * For every physical controller button create a virtual button as a
			 * failsafe in the event that the device fails and the robot cannot be
			 * interacted with.
			 */
			//SmartDashboard.putData("Elevator Floor", new SetElevatorSetpoint(RobotMap.ELEVATOR_FLOOR));
			SmartDashboard.putData("Open Grabber", new OpenGrabber());
			SmartDashboard.putData("Close Grabber", new CloseGrabber());
			SmartDashboard.putData("Elevator Up", new RaiseElevator());
			SmartDashboard.putData("Elevator Down", new LowerElevator());
			SmartDashboard.putData("Autonomous Mode", new Autonomous());
		}
		catch (Exception e)
		{
			lumberjack.dashLogError("OI", e.getMessage());
		}

		// Button creation grouped by subsystem
		try
		{
			// Grabber buttons
			buttonGrabberOpen = new JoystickButton(joystick,
					RobotMap.BUTTON_GRABBER_OPEN);
			buttonGrabberClose = new JoystickButton(joystick,
					RobotMap.BUTTON_GRABBER_CLOSE);
			// Elevator buttons
			buttonElevatorUp = new JoystickButton(joystick,
					RobotMap.BUTTON_ELEVATOR_UP);
			buttonElevatorDown = new JoystickButton(joystick,
					RobotMap.BUTTON_ELEVATOR_DOWN);
			// Autonomous button
			auto = new JoystickButton(joystick,
					RobotMap.BUTTON_AUTONOMOUS_MODE_ENABLE);
		}
		catch (Exception e)
		{
			lumberjack.dashLogError("OI", e.getMessage());
		}
		// Connect the buttons to commands
		// Grabber listeners
		try
		{
			//buttonGrabberOpen.whenPressed(new OpenGrabber());
			//buttonGrabberOpen.whenReleased(new StopGrabber());
			buttonGrabberOpen.whileHeld(new OpenGrabber());
			//buttonGrabberClose.whenPressed(new CloseGrabber());
			//buttonGrabberClose.whenReleased(new StopGrabber());
			buttonGrabberClose.whileHeld(new CloseGrabber());
		}
		catch (Exception e)
		{
			lumberjack.dashLogError("OI", e.getMessage());
		}
		
		// Elevator listeners
		try
		{
			//buttonElevatorUp.whenPressed(new RaiseElevator());
			//buttonElevatorUp.whenReleased(new StopElevator());
			buttonElevatorUp.whileHeld(new RaiseElevator());
			//buttonElevatorDown.whenPressed(new LowerElevator());
			//buttonElevatorDown.whenReleased(new StopElevator());
			buttonElevatorDown.whileHeld(new LowerElevator());
			// Autonomous mode
			auto.whenPressed(new Autonomous());
		}
		catch (Exception e)
		{
			lumberjack.dashLogError("OI", e.getMessage());
		}
	}

	public Joystick getController() {
		return controller;
	}

	public Joystick getJoystick() {
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

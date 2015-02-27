package org.usfirst.frc.team3277.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team3277.robot.RobotMap;

// Make the commands available
import org.usfirst.frc.team3277.robot.commands.*;

// Subsystem
import org.usfirst.frc.team3277.robot.subsystems.Logger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * This is where UP+UP+DOWN+DOWN+LEFT+RIGHT+LEFT+RIGHT+B+A+B+A+SELECT+START means
 * something!
 */
public class OI
{
	// Values
	double x, y, twist;
	boolean finesseButton, bControllerAssistant = false;

	// Driver input(s)
	private Joystick joystick;
	private Joystick controllerMain, controllerAssistant;

	// Joystick buttons
	private JoystickButton buttonGrabberOpen, buttonGrabberClose, buttonElevatorUp, buttonElevatorDown, buttonElevatorHold;

	public static Logger lumberjack;
	
	Timer timer;

	public OI()
	{
		lumberjack = new Logger();

		joystick = new Joystick(RobotMap.OPERATOR_INPUT_DEVICE_JOYSTICK);
		controllerMain = new Joystick(RobotMap.OPERATOR_INPUT_DEVICE_CONTROLLER_MAIN);
		controllerAssistant = new Joystick(RobotMap.OPERATOR_INPUT_DEVICE_CONTROLLER_ASSISTANT);

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
			if (bControllerAssistant)
			{
				// Secondary controller enabled for subsystem controls
				// Grabber buttons assigned to main
				buttonGrabberOpen = new JoystickButton(controllerAssistant, RobotMap.BUTTON_CONTROLLER_GRABBER_OPEN);
				buttonGrabberClose = new JoystickButton(controllerAssistant, RobotMap.BUTTON_CONTROLLER_GRABBER_CLOSE);
	
				// Elevator buttons
				buttonElevatorUp = new JoystickButton(controllerAssistant, RobotMap.BUTTON_CONTROLLER_ELEVATOR_UP);
				buttonElevatorDown = new JoystickButton(controllerAssistant, RobotMap.BUTTON_CONTROLLER_ELEVATOR_DOWN);
				buttonElevatorHold = new JoystickButton(controllerAssistant, RobotMap.BUTTON_CONTROLLER_ELEVATOR_HOLD);
			}
			else
			{
				// Main controller enabled for subsystem controls.  Secondary controller disabled
				// Grabber buttons assigned to main
				buttonGrabberOpen = new JoystickButton(controllerMain, RobotMap.BUTTON_CONTROLLER_GRABBER_OPEN);
				buttonGrabberClose = new JoystickButton(controllerMain, RobotMap.BUTTON_CONTROLLER_GRABBER_CLOSE);
	
				// Elevator buttons
				buttonElevatorUp = new JoystickButton(controllerMain, RobotMap.BUTTON_CONTROLLER_ELEVATOR_UP);
				buttonElevatorDown = new JoystickButton(controllerMain, RobotMap.BUTTON_CONTROLLER_ELEVATOR_DOWN);
				buttonElevatorHold = new JoystickButton(controllerMain, RobotMap.BUTTON_CONTROLLER_ELEVATOR_HOLD);
			}
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
		return controllerMain;
	}

	public Joystick getJoystick()
	{
		return joystick;
	}

	public double getJoystickX()
	{
		this.x = joystick.getX();
		return this.x;
	}

	public double getJoystickY()
	{
		this.y = joystick.getY();
		return this.y;
	}

	public double getJoystickTwist()
	{
		this.twist = joystick.getTwist();
		return this.twist;
	}
	
	public double getControllerX()
	{
		this.x = applyDeadZone(controllerMain.getRawAxis(RobotMap.CONTROLLER_AXIS_LEFT_RIGHT_X), RobotMap.CONTROLLER_DEAD_ZONE);
		return this.x;
	}
	
	public double getControllerY()
	{
		this.y = applyDeadZone(controllerMain.getRawAxis(RobotMap.CONTROLLER_AXIS_FWD_REV_Y), RobotMap.CONTROLLER_DEAD_ZONE);
		return this.y;
	}
	
	public double getControllerTwist()
	{
		this.twist = applyDeadZone(controllerMain.getRawAxis(RobotMap.CONTROLLER_AXIS_CRAB_X), RobotMap.CONTROLLER_DEAD_ZONE);
		return this.twist;
	}
	
	public boolean getFinesseButton()
	{
		this.finesseButton = controllerMain.getRawButton(RobotMap.BUTTON_CONTROLLER_FINESSE);
		return this.finesseButton;
	}
	
	public double applyDeadZone(double value, double deadzone) {
		double calculatedValue = 0.0;
		
		if (Math.abs(value) >= deadzone)
		{
			calculatedValue = value;
		}
		
		return calculatedValue;
	}
	
//	 public double governor(double previousGovernor, Timer timestamp)
//	 {
//		double governorPercent = RobotMap.GOVERNOR_STARTING_PERCENT;
//		governorPercent = previousGovernor * (Math.pow((2 + RobotMap.GOVERNOR_ACCELERATION_PERCENTAGE), timestamp.get())*2);
//		if(governorPercent > 1){
//			governorPercent = 1;
//		}
//		govper = governorPercent;
//		return governorPercent;
//	 }
//	 
//	 public void getGovernorTimestamp(Timer timestamp)
//	 {
//		this.timer = timestamp; 
//	 }
	
	/**
	 * The log method puts information of interest from the OI subsystem to the SmartDashboard.
	 */
	public void dashLog()
	{
		//lumberjack.dashLogError("OI:", Double.toString(govper));
	}
}

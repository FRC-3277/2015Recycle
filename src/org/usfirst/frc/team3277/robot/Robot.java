package org.usfirst.frc.team3277.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

// Each subsystem available to the robot must be included here.
import org.usfirst.frc.team3277.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3277.robot.subsystems.Elevator;
import org.usfirst.frc.team3277.robot.subsystems.Grabber;
import org.usfirst.frc.team3277.robot.subsystems.UsbCamera;
import org.usfirst.frc.team3277.robot.subsystems.LidarSensor;
import org.usfirst.frc.team3277.robot.subsystems.Accelerometer;
import org.usfirst.frc.team3277.robot.subsystems.Logger;
import org.usfirst.frc.team3277.robot.subsystems.ArduinoSerialRead;

// Commands to include
import org.usfirst.frc.team3277.robot.commands.Autonomous;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Command autonomousCommand;
	
	// Declare the subsystems
	public static OI operatorInterface;
	public static DriveTrain drivetrain;
	public static Elevator elevator;
	public static Grabber grabber;
	public static UsbCamera usbCamera;
	public static LidarSensor lidarSensor;
	public static Accelerometer accelerometer;
	public static Logger lumberjack;
	public static ArduinoSerialRead arduinoReader;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// Initialize the subsystems
		lumberjack = new Logger();  // Make first priority to enable ability to capture logging.
		try
		{
			operatorInterface = new OI();
			drivetrain = new DriveTrain();
			elevator = new Elevator();
			grabber = new Grabber();
			usbCamera = new UsbCamera();
			lidarSensor = new LidarSensor(Port.kMXP);
			lidarSensor.start();
			accelerometer = new Accelerometer();
			arduinoReader = new ArduinoSerialRead("/dev/ttyACM0");
		}
		catch(Exception e)
		{
			lumberjack.dashLogError("Robot", e.getMessage());
		}
		
		// Start reading from the Arduino
		try
		{
			//arduinoReader.initialize();
		}
		catch(Exception e)
		{
			lumberjack.dashLogError("ArduinoReader", e.getMessage());
		}
		
		// instantiate the command used for the autonomous period
		try 
		{
			autonomousCommand = new Autonomous();
		} 
		catch (Exception e) 
		{
			lumberjack.dashLogError("Robot", e.getMessage());
		}

		// Show what command your subsystem is running on the SmartDashboard
		try 
		{
			SmartDashboard.putData(drivetrain);
			SmartDashboard.putData(elevator);
			SmartDashboard.putData(grabber);
			SmartDashboard.putData(usbCamera);
			SmartDashboard.putData(lidarSensor);
			SmartDashboard.putData(accelerometer);
			SmartDashboard.putData(arduinoReader);
		} 
		catch (Exception e) 
		{
			lumberjack.dashLogError("Robot", e.getMessage());
		}
	}

	/**
	 * This function is called when the autonomous period starts.
	 */
	public void autonomousInit() {
		// Launches the Autonomous command within the autonomous periodic
		// Scheduler
		try 
		{
			autonomousCommand.start();
		} 
		catch (Exception e) 
		{
			lumberjack.dashLogError("Robot", e.getMessage());
		}
	}

	/**
	 * This function is repeatedly called periodically, 20 ms or less, during autonomous mode.
	 */
	public void autonomousPeriodic() {
		try 
		{
			Scheduler.getInstance().run();
			this.dashLog();
		} 
		catch (Exception e) 
		{
			lumberjack.dashLogError("Robot", e.getMessage());
		}
	}

	/**
	 * This function is called when the teleop period starts.
	 */
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		try 
		{
			autonomousCommand.cancel();
		} 
		catch (Exception e) 
		{
			lumberjack.dashLogError("Robot", e.getMessage());
		}
	}

	/**
	 * This function is repeatedly called periodically, 20 ms or less during operator control.
	 */
	public void teleopPeriodic() {
		try 
		{
			Scheduler.getInstance().run();
			this.dashLog();
		} 
		catch (Exception e) 
		{
			lumberjack.dashLogError("Robot", e.getMessage());
		}
	}

	/**
	 * This function is repeatedly called periodically, 20 ms or less during test mode
	 */
	public void testPeriodic() 
	{
		// Not sure what this does.
		try 
		{
			LiveWindow.run();
			this.dashLog();
		} 
		catch (Exception e) 
		{
			lumberjack.dashLogError("Robot", e.getMessage());
		}
	}
	
	/**
	 * The log method puts information of interest of the subsystems to the SmartDashboard.
	 */
    private void dashLog() {
        try 
        {
			elevator.dashLog();
			drivetrain.dashLog();
			lidarSensor.dashLog();
			accelerometer.dashLog();
		} 
        catch (Exception e) 
        {
        	lumberjack.dashLogError("Robot", e.getMessage());
		}
    }
}

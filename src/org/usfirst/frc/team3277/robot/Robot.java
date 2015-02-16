package org.usfirst.frc.team3277.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.IterativeRobot;
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
import org.usfirst.frc.team3277.robot.subsystems.CompassSensor;

// Commands to include
import org.usfirst.frc.team3277.robot.commands.Autonomous;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	Command autonomousCommand;

	// Declare the subsystems
	public static DriveTrain drivetrain;
	public static Elevator elevator;
	public static Grabber grabber;
	public static UsbCamera usbCamera;
	public static LidarSensor lidarSensor;
	public static Accelerometer accelerometer;
	public static Logger lumberjack;
	public static CompassSensor compassSensor;
	public static OI operatorInterface;
	
	/*
	 * Avoiding calls to subsystems that could not initialize.
	 */
	boolean bDrivetrain = false, bElevator = false, bGrabber = false, bUsbCamera = false,
			bLidarSensor = false, bAccelerometer = false, bCompassSensor = false, bOperatorInterface = false;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		// Initialize the subsystems
		lumberjack = new Logger(); // Make first priority to enable ability to capture logging.
		
		try
		{
			drivetrain = new DriveTrain();
			bDrivetrain = true;
		} catch (Exception e)
		{
			lumberjack.dashLogError("Robot", "Fatal Error DriveTrain: " + e.getMessage());
		}

		try
		{
			elevator = new Elevator();
			bElevator = true;
		} catch (Exception e)
		{
			lumberjack.dashLogError("Robot", "Fatal Error Elevator: " + e.getMessage());
		}

		try
		{
			grabber = new Grabber();
			bGrabber = true;
		} catch (Exception e)
		{
			lumberjack.dashLogError("Robot", "Fatal Error Grabber: " + e.getMessage());
		}

		try
		{
			usbCamera = new UsbCamera();
			bUsbCamera = true;
		} catch (Exception e)
		{
			lumberjack.dashLogError("Robot", "Fatal Error UsbCamera: " + e.getMessage());
		}

		try
		{
			lidarSensor = new LidarSensor();
			bLidarSensor = true;
		} catch (Exception e)
		{
			lumberjack.dashLogError("Robot", "Fatal Error Lidar: " + e.getMessage());
		}

		try
		{
			accelerometer = new Accelerometer();
			bAccelerometer = true;
		} catch (Exception e)
		{
			lumberjack.dashLogError("Robot", "Fatal Error Accelerometer: " + e.getMessage());
		}

		try
		{
			compassSensor = new CompassSensor();
			bCompassSensor = true;
		} catch (Exception e)
		{
			lumberjack.dashLogError("Robot", "Fatal Error CompassSensor: " + e.getMessage());
		}
		
		try
		{
			operatorInterface = new OI();
			bOperatorInterface = true;
		} catch (Exception e)
		{
			lumberjack.dashLogError("Robot", "Fatal Error OI: " + e.getMessage());
		}

		// Start reading the Lidar Sensor
		try
		{
			if (bLidarSensor)
			{
				lidarSensor.start();
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("LidarSensor", "Fatal Error LidarSensor: " + e.getMessage());
		}

		// Start reading from the Arduino
		try
		{
			if (bCompassSensor)
			{
				compassSensor.start();
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("compassSensor", "Fatal Error CompassSensor Start: " + e.getMessage());
		}

		// instantiate the command used for the autonomous period
		try
		{
			autonomousCommand = new Autonomous();
		} catch (Exception e)
		{
			lumberjack.dashLogError("Robot", "autonomousCommand Failed: " + e.getMessage());
		}

		// Show what command your subsystem is running on the SmartDashboard
		try
		{
			if (bDrivetrain)
			{
				SmartDashboard.putData(drivetrain);
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotDriveTrain", "DriveTrain SmartDashboard Error: " + e.getMessage());
		}
		
		try
		{
			if (bElevator)
			{
				SmartDashboard.putData(elevator);
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotElevator", "Elevator SmartDashboard Error: " + e.getMessage());
		}
		
		try
		{
			if (bGrabber)
			{
				SmartDashboard.putData(grabber);
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotGrabber", "Grabber SmartDashboard Error: " + e.getMessage());
		}
		
		try
		{
			if (bUsbCamera)
			{
				SmartDashboard.putData(usbCamera);
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotUsbCamera", "UsbCamera SmartDashboard Error: " + e.getMessage());
		}
		
		try
		{
			if (bLidarSensor)
			{
				SmartDashboard.putData(lidarSensor);
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotLidarSensor", "LidarSensor SmartDashboard Error: " + e.getMessage());
		}
		
		try
		{
			if (bAccelerometer)
			{
				SmartDashboard.putData(accelerometer);
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotAccelerometer", "Accelerometer SmartDashboard Error: " + e.getMessage());
		}
		
		try
		{
			if (bCompassSensor)
			{
				SmartDashboard.putData(compassSensor);
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotCompassSensor", "CompassSensor SmartDashboard Error: " + e.getMessage());
		}
	}

	/**
	 * This function is called when the autonomous period starts.
	 */
	public void autonomousInit()
	{
		// Launches the Autonomous command within the autonomous periodic
		// Scheduler
		try
		{
			autonomousCommand.start();
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotAutonomousInit", e.getMessage());
		}
	}

	/**
	 * This function is repeatedly called periodically, 20 ms or less, during
	 * autonomous mode.
	 */
	public void autonomousPeriodic()
	{
		try
		{
			Scheduler.getInstance().run();
			this.dashLog();
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotAutonomous", e.getMessage());
		}
	}

	/**
	 * This function is called when the teleop period starts.
	 */
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		try
		{
			autonomousCommand.cancel();
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotAutonomousCancel", e.getMessage());
		}
	}

	/**
	 * This function is repeatedly called periodically, 20 ms or less during
	 * operator control.
	 */
	public void teleopPeriodic()
	{
		try
		{
			Scheduler.getInstance().run();
			this.dashLog();
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotTeleop", e.getMessage());
		}
	}

	/**
	 * This function is repeatedly called periodically, 20 ms or less during
	 * test mode
	 */
	public void testPeriodic()
	{
		// Not sure what this does.
		try
		{
			LiveWindow.run();
			this.dashLog();
		} catch (Exception e)
		{
			lumberjack.dashLogError("Robot", e.getMessage());
		}
	}

	/**
	 * The log method puts information of interest of the subsystems to the
	 * SmartDashboard.
	 */
	private void dashLog()
	{
		try
		{
			if (bElevator)
			{
				elevator.dashLog();
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotElevator", e.getMessage());
		}
		
		try
		{
			if (bGrabber)
			{
				grabber.dashLog();
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotGrabber", e.getMessage());
		}
		
		try
		{
			if (bDrivetrain)
			{
				drivetrain.dashLog();
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotDriveTrain", e.getMessage());
		}
		
		try
		{
			if (bLidarSensor)
			{
				lidarSensor.dashLog();
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotLidarSensor", e.getMessage());
		}
		
		try
		{
			if (bAccelerometer)
			{
				accelerometer.dashLog();
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotAccelerometer", e.getMessage());
		}
		
		try
		{
			if (bCompassSensor)
			{
				compassSensor.dashLog();
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("RobotCopmpassSensor", e.getMessage());
		}
		
		try
		{
			if (bOperatorInterface)
			{
				operatorInterface.dashLog();
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("OperatorInterface", e.getMessage());
		}
		
	}
}

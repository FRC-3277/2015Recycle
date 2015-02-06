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

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// Initialize the subsystems
		operatorInterface = new OI();
		drivetrain = new DriveTrain();
		elevator = new Elevator();
		grabber = new Grabber();
		usbCamera = new UsbCamera();
		lidarSensor = new LidarSensor(Port.kMXP);
		lidarSensor.start();
		accelerometer = new Accelerometer();
		

		// instantiate the command used for the autonomous period
		autonomousCommand = new Autonomous();

		// Show what command your subsystem is running on the SmartDashboard
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(elevator);
		SmartDashboard.putData(grabber);
		SmartDashboard.putData(usbCamera);
		SmartDashboard.putData(lidarSensor);
		SmartDashboard.putData(accelerometer);
	}

	/**
	 * This function is called when the autonomous period starts.
	 */
	public void autonomousInit() {
		// Launches the Autonomous command within the autonomous periodic
		// Scheduler
		autonomousCommand.start();
	}

	/**
	 * This function is repeatedly called periodically, 20 ms or less, during autonomous mode.
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		this.dashLog();
	}

	/**
	 * This function is called when the teleop period starts.
	 */
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		autonomousCommand.cancel();
	}

	/**
	 * This function is repeatedly called periodically, 20 ms or less during operator control.
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		this.dashLog();
	}

	/**
	 * This function is repeatedly called periodically, 20 ms or less during test mode
	 */
	public void testPeriodic() 
	{
		// Not sure what this does.
		LiveWindow.run();
		this.dashLog();
	}
	
	/**
	 * The log method puts information of interest of the subsystems to the SmartDashboard.
	 */
    private void dashLog() {
        elevator.dashLog();
        drivetrain.dashLog();
        lidarSensor.dashLog();
        accelerometer.dashLog();
    }
}

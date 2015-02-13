package org.usfirst.frc.team3277.robot.subsystems;

import org.usfirst.frc.team3277.robot.RobotMap;
import org.usfirst.frc.team3277.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Gyro;

/**
 *
 */
public class DriveTrain extends Subsystem {
	// Subsystem devices
	private CANTalon LeftFront;
	@SuppressWarnings("unused")
	private CANTalon LeftRear;
	private CANTalon RightFront;
	@SuppressWarnings("unused")
	private CANTalon RightRear;
	private RobotDrive drive;
	private Encoder rightEncoder, leftEncoder;
	GyroSensor gyro;

	// Safety
	MotorSafety motorSafety;
	MotorSafetyHelper watchdog;

	static private Logger lumberjack;

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public DriveTrain()
    {
    	lumberjack = new Logger();
    	
    	try
    	{
    		gyro = new GyroSensor(RobotMap.GYRO_ANALOG_CHANNEL, RobotMap.GYRO_SENSITIVITY);
    		gyro.reset();
    	}
    	catch (Exception e)
    	{
    		lumberjack.dashLogError("DriveTrain", "Gyro Sensor - " + e.getMessage());
    	}
    	
    	try 
    	{
			LeftFront = Talon.initTalon(RobotMap.FRONT_LEFT_DRIVE);
			LeftFront.enableControl();
	
		} 
    	catch (Exception e) 
    	{
    		lumberjack.dashLogError("DriveTrain", "LeftFront motor controller Failure: " + e.getMessage());
		}
    	
    	try 
    	{
    		LeftRear = Talon.initTalon(RobotMap.REAR_LEFT_DRIVE);
    		LeftRear.enableControl();
    		
		} 
    	catch (Exception e) 
    	{
    		lumberjack.dashLogError("DriveTrain", "LeftRear motor controller Failure: " + e.getMessage());
		}
    	
    	try 
    	{
			RightFront = Talon.initTalon(RobotMap.FRONT_RIGHT_DRIVE);
			RightFront.enableControl();
		} 
    	catch (Exception e) 
    	{
    		lumberjack.dashLogError("DriveTrain", "RightFront motor controller Failure: " + e.getMessage());
		}
    	
    	try 
    	{
			RightRear = Talon.initTalon(RobotMap.REAR_RIGHT_DRIVE);
			RightRear.enableControl();
		} 
    	catch (Exception e) 
    	{
    		lumberjack.dashLogError("DriveTrain", "RightRear motor controller Failure: " + e.getMessage());
		}
    	
    	try 
    	{
    		// Change the robot drive frame type here by adding or removing expected motor controllers.  Leave the declaration of unused variables be.
			this.drive = new RobotDrive(LeftFront, LeftRear, RightFront, RightRear);
			// For the mechanum drive
			this.drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
			this.drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		} 
    	catch (Exception e) 
    	{
    		lumberjack.dashLogError("DriveTrain", "RobotDrive Failure: " + e.getMessage());
		}
    	// Don't disable.  Handle it!
    	drive.setSafetyEnabled(true);
    	
    	watchdog = new MotorSafetyHelper(motorSafety);
		watchdog.setExpiration(RobotMap.DRIVE_TRAIN_DEFAULT_DISABLE_TIMEOUT);
    }

	/**
	 * @param joystick
	 *            joystick to use as the input for tank drive.
	 */
	public void tankDrive(Joystick joystick) {
		drive.tankDrive(joystick.getY(), joystick.getRawAxis(4));
	}

	public void arcadeDrive(Joystick joystick) {
		drive.arcadeDrive(joystick.getY(), -joystick.getTwist());
		gyro.dashLog();
	}

	public void mecanumDrive(double x, double y, double twist, double sensitivity) {
		if (sensitivity > 1.0) {
			sensitivity = 1.0;
		}
		if (sensitivity < 0.0) {
			sensitivity = 0.0;
		}
		this.drive.mecanumDrive_Cartesian(x * sensitivity, y * sensitivity,
				twist * sensitivity, 0);
	}

	/**
	 * Stop the drivetrain from moving.
	 */
	public void stop() {
		drive.tankDrive(0, 0);
	}

	/**
	 * @return The encoder getting the distance and speed of left side of the
	 *         drivetrain.
	 */
	public Encoder getLeftEncoder() {
		return leftEncoder;
	}

	/**
	 * @return The encoder getting the distance and speed of right side of the
	 *         drivetrain.
	 */
	public Encoder getRightEncoder() {
		return rightEncoder;
	}

	/**
	 * The log method puts information of interest from the DriveTrain subsystem
	 * to the SmartDashboard.
	 */
	public void dashLog() {
		// SmartDashboard.putData("Key", value);
	}
}

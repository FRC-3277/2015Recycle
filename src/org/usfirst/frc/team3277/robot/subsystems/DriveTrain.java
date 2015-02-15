package org.usfirst.frc.team3277.robot.subsystems;

import org.usfirst.frc.team3277.robot.RobotMap;
import org.usfirst.frc.team3277.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;

/**
 *
 */
public class DriveTrain extends Subsystem
{
	// Subsystem devices
	private CANTalon frontLeftTalon;
	public double frontLeftPulseCount;
	@SuppressWarnings("unused")
	private CANTalon rearLeftTalon;
	public double rearLeftPulseCount;
	private CANTalon frontRightTalon;
	public double frontRightPulseCount;
	@SuppressWarnings("unused")
	private CANTalon rearRightTalon;
	public double rearRightPulseCount;
	private RobotDrive drive;
	private Encoder frontLeftEncoder, frontRightEncoder, rearLeftEncoder, rearRightEncoder;
	// If an encoder fails the bot shall live on!
	private boolean bEncoderFailure = false;

	// Gryo
	GyroSensor gyro;

	// Safety
	MotorSafety motorSafety;
	MotorSafetyHelper watchdog;

	static private Logger lumberjack;

	public void initDefaultCommand()
	{
		setDefaultCommand(new DriveWithJoystick());
	}

	public DriveTrain()
	{
		lumberjack = new Logger();

		try
		{
			gyro = new GyroSensor(RobotMap.GYRO_ANALOG_CHANNEL, RobotMap.GYRO_SENSITIVITY);
			gyro.reset();
		} catch (Exception e)
		{
			lumberjack.dashLogError("DriveTrainGryo", e.getMessage());
		}

		try
		{
			frontLeftTalon = Talon.initTalon(RobotMap.FRONT_LEFT_DRIVE);
			try
			{
				// frontLeftEncoder = new
				// Encoder(RobotMap.Encoders.FRONT_LEFT_DRIVE_A,
				// RobotMap.Encoders.FRONT_LEFT_DRIVE_B);

				// For some reason this cripples the talon...
				frontLeftTalon.changeControlMode(CANTalon.ControlMode.Position);
				frontLeftTalon.set(frontLeftPulseCount);
				frontLeftTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
				// frontLeftTalon.reverseSensor(true);
				frontLeftTalon.setP(1);
				frontLeftTalon.setPosition(0);

				// frontLeftEncoder.setDistancePerPulse(RobotMap.Encoders.DISTANCE_TRAVELLED_PER_PULSE);
				// frontLeftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
			} catch (Exception err)
			{
				bEncoderFailure = true;
				lumberjack.dashLogError("DriveTrainEncoder", "FrontLeft Encoder Failure: " + err.getMessage());
			}

			// Sets the encoder to zero as well as allows the motor to function!
			frontLeftTalon.enableControl();
		} catch (Exception e)
		{
			lumberjack.dashLogError("DriveTrain", "FrontLeft motor controller Failure: " + e.getMessage());
		}

		try
		{
			rearLeftTalon = Talon.initTalon(RobotMap.REAR_LEFT_DRIVE);
			rearLeftTalon.enableControl();
			try
			{
				// rearLeftEncoder = new
				// Encoder(RobotMap.Encoders.REAR_LEFT_DRIVE_A,
				// RobotMap.Encoders.REAR_LEFT_DRIVE_B);
				// frontRightEncoder.setDistancePerPulse(RobotMap.Encoders.DISTANCE_TRAVELLED_PER_PULSE);
				// frontRightEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
			} catch (Exception err)
			{
				bEncoderFailure = true;
				lumberjack.dashLogError("DriveTrainEncoder", "FrontLeft Encoder Failure: " + err.getMessage());
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("DriveTrain", "LeftRear motor controller Failure: " + e.getMessage());
		}

		try
		{
			frontRightTalon = Talon.initTalon(RobotMap.FRONT_RIGHT_DRIVE);
			frontRightTalon.enableControl();
			try
			{
				// frontRightEncoder = new
				// Encoder(RobotMap.Encoders.FRONT_RIGHT_DRIVE_A,
				// RobotMap.Encoders.FRONT_RIGHT_DRIVE_B);
				// rearLeftEncoder.setDistancePerPulse(RobotMap.Encoders.DISTANCE_TRAVELLED_PER_PULSE);
				// rearLeftEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
			} catch (Exception err)
			{
				bEncoderFailure = true;
				lumberjack.dashLogError("DriveTrainEncoder", "FrontLeft Encoder Failure: " + err.getMessage());
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("DriveTrain", "FrontRight motor controller Failure: " + e.getMessage());
		}

		try
		{
			rearRightTalon = Talon.initTalon(RobotMap.REAR_RIGHT_DRIVE);
			rearRightTalon.enableControl();
			try
			{
				// rearRightEncoder = new
				// Encoder(RobotMap.Encoders.REAR_RIGHT_DRIVE_A,
				// RobotMap.Encoders.REAR_RIGHT_DRIVE_B);
				// rearRightEncoder.setDistancePerPulse(RobotMap.Encoders.DISTANCE_TRAVELLED_PER_PULSE);
				// rearRightEncoder.setPIDSourceParameter(PIDSourceParameter.kRate);
			} catch (Exception err)
			{
				bEncoderFailure = true;
				lumberjack.dashLogError("DriveTrainEncoder", "FrontLeft Encoder Failure: " + err.getMessage());
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("DriveTrain", "RearRight motor controller Failure: " + e.getMessage());
		}

		try
		{
			// Change the robot drive frame type here by adding or removing
			// expected motor controllers. Leave the declaration of unused
			// variables be.
			this.drive = new RobotDrive(frontLeftTalon, rearLeftTalon, frontRightTalon, rearRightTalon);
			// For the mechanum drive
			this.drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
			this.drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		} catch (Exception e)
		{
			lumberjack.dashLogError("DriveTrain", "RobotDrive Failure: " + e.getMessage());
		}
		// Don't disable. Handle it!
		drive.setSafetyEnabled(true);

		watchdog = new MotorSafetyHelper(motorSafety);
		watchdog.setExpiration(RobotMap.DRIVE_TRAIN_DEFAULT_DISABLE_TIMEOUT);
	}

	/**
	 * @param joystick
	 *            joystick to use as the input for tank drive.
	 */
	public void tankDrive(Joystick joystick)
	{
		drive.tankDrive(joystick.getY(), joystick.getRawAxis(4));
		gyro.dashLog();
	}

	public void arcadeDrive(Joystick joystick)
	{
		drive.arcadeDrive(joystick.getY(), -joystick.getTwist());
		gyro.dashLog();
	}

	public void mecanumDrive(double x, double y, double twist, double sensitivity)
	{
		if (sensitivity > 1.0)
		{
			sensitivity = 1.0;
		}
		if (sensitivity < 0.0)
		{
			sensitivity = 0.0;
		}
		this.drive.mecanumDrive_Cartesian(x * sensitivity, y * sensitivity, twist * sensitivity, gyro.getAngle());
		gyro.dashLog();
	}

	/**
	 * Stop the tank drivetrain from moving.
	 */
	public void stopTankDrive()
	{
		this.drive.tankDrive(0, 0);
		gyro.dashLog();
	}

	public void stopMecanumDrive()
	{
		this.drive.mecanumDrive_Cartesian(0, 0, 0, 0);
	}

	/**
	 * @return The distance of the encoder.
	 */
	public double getEncoderDistance(Encoder encoder)
	{
		return encoder.getDistance();
	}

	/**
	 * @return The rate (distance/time) of the encoder.
	 */
	public double getEncoderRate(Encoder encoder)
	{
		return encoder.getRate();
	}

	/**
	 * @return The direction of the encoder.
	 */
	public boolean getEncoderDirection(Encoder encoder)
	{
		return encoder.getDirection();
	}

	/**
	 * @return The flag identifying rotating status of the encoder.
	 */
	public boolean isEncoderStopped(Encoder encoder)
	{
		return encoder.getStopped();
	}

	/**
	 * The log method puts information of interest from the DriveTrain subsystem
	 * to the SmartDashboard.
	 */
	public void dashLog()
	{
		if (!bEncoderFailure)
		{
			// Encoder feedback
			lumberjack.dashLogNumber("EncoderFrontLeft", frontLeftPulseCount);
			// lumberjack.dashLogNumber("EncoderFrontRight",
			// getEncoderDistance(frontRightEncoder));
			// lumberjack.dashLogNumber("EncoderRearLeft",
			// getEncoderDistance(rearLeftEncoder));
			// lumberjack.dashLogNumber("EncoderRearRight",
			// getEncoderDistance(rearRightEncoder));
		}
	}
}

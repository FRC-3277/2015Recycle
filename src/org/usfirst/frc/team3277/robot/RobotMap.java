package org.usfirst.frc.team3277.robot;

// TODO: Auto-generated Javadoc
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap
{
	/*************************** BEGIN AUTONOMOUS *****************************************/
	/*
	 * Timeouts: All timeouts are in seconds.
	 */
	public final static int AUTONOMOUS_TIMEOUT_GRABBER_CLOSE_TOTE = 10, AUTONOMOUS_TIMEOUT_ELEVATOR_RAISE_TOTE = 2, AUTONOMOUS_TIMEOUT_DRIVE_TRAIN_TURN = 4,
			AUTONOMOUS_TIMEOUT_DRIVE_TRAIN_DRIVE_FORWARD = 10;
	
	/*
	 * Drive Train turn speed
	 */
	public final static double AUTONOMOUS_DRIVE_TRAIN_TURN_SPEED = 0.6;
	
	/*
	 * Drive Train travel speed.
	 */
	public final static double AUTONOMOUS_DRIVE_TRAIN_TRAVEL_SPEED = 0.8;
	
	/*
	 * Drive Train turn degrees.  Positive for CW turning.  Negative for CCW turning.
	 */
	public final static double AUTONOMOUS_DRIVE_TRAIN_TURN_DEGREES = 90;
	
	/*
	 * Drive Train travel distance to travel in cm.
	 */
	public final static double AUTONOMOUS_DRIVE_TRAIN_TRAVEL_DISTANCE = 10;
	
	/*************************** END AUTONOMOUS *******************************************/
	/*
	 * Safety First! If the robot goes rogue this is the time in seconds which
	 * the robot will stop moving.
	 */
	public final static int DRIVE_TRAIN_DEFAULT_DISABLE_TIMEOUT = 15;

	/*
	 * Operator input device port
	 */
	public final static int OPERATOR_INPUT_DEVICE_JOYSTICK = 0, OPERATOR_INPUT_DEVICE_CONTROLLER = 1;

	/*
	 * DriveTrain Motors - Keep the same for ProtoBot, Swerve, or Mecanum drive.
	 * Use the convention read like a book, front to back left to right to
	 * order. Skip #1 since it should be reserved to give to the RoboRio if
	 * need.
	 * 
	 * In a two motor bot such as the ProtoBot the FRONT motors are the ones to
	 * reference.
	 */
	public final static int FRONT_LEFT_DRIVE = 1, FRONT_RIGHT_DRIVE = 2, REAR_LEFT_DRIVE = 3, REAR_RIGHT_DRIVE = 4;

	/*
	 * Ordered as a book is read front to back left to right.
	 */
	public static class Encoders {
		public static final int FRONT_LEFT_DRIVE_A = 10;
		public static final int FRONT_LEFT_DRIVE_B = 11;
		public static final int FRONT_RIGHT_DRIVE_A = 12;
		public static final int FRONT_RIGHT_DRIVE_B = 13;
		public static final int REAR_LEFT_DRIVE_A = 14;
		public static final int REAR_LEFT_DRIVE_B = 15;
		public static final int REAR_RIGHT_DRIVE_A = 16;
		public static final int REAR_RIGHT_DRIVE_B = 17;
		/* Visually presented calculation for human eyes :)
		 *  5.95:1 gearbox ratio
		 *  1 revolution of the motor shaft = 1440 pulses by way of the encoder.
		 *  Tire size = 8 inches
		 *  25.13272 = Circumference
		 *  
		 *  We be metric!  Not crashing into mars with this bot :)
		 *  http://www.cnn.com/TECH/space/9909/30/mars.metric.02/
		 *  
		 *  Units of measurement is cm.
		 */
		public static final double DISTANCE_TRAVELLED_PER_PULSE = (25.13272/(5.95*1440)*2.54);
		
		
	}
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	/*
	 * Elevator setpoints broken down into 1 percent increments from zero to
	 * hundred.
	 */
	public final static int ELEVATOR_FLOOR = 0, ELEVATOR_MAX = 100;

	/*
	 * Set the speed of the elevator motor.
	 */
	public final static double ELEVATOR_MOTOR_UP_SPEED = 1.0, ELEVATOR_MOTOR_DOWN_SPEED = 0.7, ELEVATOR_MOTOR_HOLD_SPEED = 0.15;

	/*
	 * Elevator Motors
	 */
	public final static int ELEVATOR_MOTOR = 5;

	/*
	 * Elevator joystick buttons
	 */
	public final static int BUTTON_JOYSTICK_ELEVATOR_UP = 8, BUTTON_JOYSTICK_ELEVATOR_HOLD = 10, BUTTON_JOYSTICK_ELEVATOR_DOWN = 12;
	
	/*
	 * Controller Axis
	 */
	public final static int CONTROLLER_AXIS_LEFT_RIGHT_X = 0, CONTROLLER_AXIS_FWD_REV_Y = 1, CONTROLLER_AXIS_CRAB_X = 4;
	
	/*
	 * XBOX Controller dead zone
	 */
	public final static double CONTROLLER_DEAD_ZONE = 0.175;
	
	/*
	 * Elevator controller buttons
	 */
	public final static int BUTTON_CONTROLLER_ELEVATOR_UP = 4, BUTTON_CONTROLLER_ELEVATOR_HOLD = 6, BUTTON_CONTROLLER_ELEVATOR_DOWN = 1;
	
	/*
	 * Grabber motor
	 */
	public final static int GRABBER_MOTOR = 6;

	public final static double GRABBER_MOTOR_SPEED = 0.9;

	/*
	 * Grabber joystick buttons
	 */
	public final static int BUTTON_JOYSTICK_GRABBER_OPEN = 3, BUTTON_JOYSTICK_GRABBER_CLOSE = 4;
	
	/*
	 * Grabber controller buttons
	 */
	public final static int BUTTON_CONTROLLER_GRABBER_OPEN = 2, BUTTON_CONTROLLER_GRABBER_CLOSE = 3;
	
//	/*
//	 * Governor Values
//	 */
//	public final static double GOVERNOR_STARTING_PERCENT = 0.2;
//	public final static double GOVERNOR_ACCELERATION_PERCENTAGE = 0.5;
	/*
	 * Finesse Button Values
	 */
	public final static double FINESSE_VELOCITY_PERCENTAGE = 0.40, STANDARD_VELOCITY_PERCENTAGE = 0.8;
	public final static int BUTTON_CONTROLLER_FINESSE = 5; //5 = Left Bumper
	

	/*************************** BEGIN SENSORS ***************************************/
	/*
	 * Gyro
	 */
	public final static int GYRO_ANALOG_CHANNEL = 0;
	// See Page 3 of 13 from gyro ADW22307 documentation for mV/degree/s
	public final static double GYRO_SENSITIVITY = 0.07;

	/*
	 * Usb Camera
	 */
	public final static String USB_CAMERA_INSTANCE = "cam0";
	/*
	 * Failover in case the wrong USB port was selected since the camera is
	 * critical and also failure to fire up the camera will result in the bot to
	 * fail.
	 */
	public final static String USB_CAMERA_WRONG_WIRING = "cam1";

	public final static int USB_CAMERA_QUALITY = 50;

	/*
	 * LIDAR - DO NOT ALTER! Derived from documentation.
	 */
	// public final static Port LIDAR_CHANNEL;
	public final static int LIDAR_ADDR = 0x62;
	public final static int LIDAR_CONFIG_REGISTER = 0x00;
	public final static int LIDAR_DISTANCE_REGISTER = 0x8f;

	/*
	 * Compass - Code mapped to that which is defined in the Arduino Sketch.
	 */
	// I2C ADDRESS/BITS
	public final static int HMC5883_ADDRESS_MAG = 0x3C; //(0x3C >> 1); // 0011110x

	// REGISTERS
	public final static int HMC5883_REGISTER_MAG_CRA_REG_M = 0x00, HMC5883_REGISTER_MAG_CRB_REG_M = 0x01,
			HMC5883_REGISTER_MAG_MR_REG_M = 0x02, HMC5883_REGISTER_MAG_OUT_X_H_M = 0x03, HMC5883_REGISTER_MAG_OUT_X_L_M = 0x04,
			HMC5883_REGISTER_MAG_OUT_Z_H_M = 0x05, HMC5883_REGISTER_MAG_OUT_Z_L_M = 0x06, HMC5883_REGISTER_MAG_OUT_Y_H_M = 0x07,
			HMC5883_REGISTER_MAG_OUT_Y_L_M = 0x08, HMC5883_REGISTER_MAG_SR_REG_Mg = 0x09, HMC5883_REGISTER_MAG_IRA_REG_M = 0x0A,
			HMC5883_REGISTER_MAG_IRB_REG_M = 0x0B, HMC5883_REGISTER_MAG_IRC_REG_M = 0x0C, HMC5883_REGISTER_MAG_TEMP_OUT_H_M = 0x31,
			HMC5883_REGISTER_MAG_TEMP_OUT_L_M = 0x32;

	// MAGNETOMETER GAIN SETTINGS
	public final static int HMC5883_MAGGAIN_1_3 = 0x20, // +/- 1.3
			HMC5883_MAGGAIN_1_9 = 0x40, // +/- 1.9
			HMC5883_MAGGAIN_2_5 = 0x60, // +/- 2.5
			HMC5883_MAGGAIN_4_0 = 0x80, // +/- 4.0
			HMC5883_MAGGAIN_4_7 = 0xA0, // +/- 4.7
			HMC5883_MAGGAIN_5_6 = 0xC0, // +/- 5.6
			HMC5883_MAGGAIN_8_1 = 0xE0; // +/- 8.1

	// CHIP ID
	public final static int HMC5883_ID = (0b11010100);

	// Constants
	public final static int SENSORS_GAUSS_TO_MICROTESLA = (100);
	/** < Gauss to micro-Tesla multiplier */

	// Sensor type
	public final static int SENSOR_TYPE_MAGNETIC_FIELD = (2);

	/*
	 * Critical to correct/calibrate this for each competition. Failure to do so
	 * will result in Autonomous mode fireworks.
	 * 
	 * The variable works only when assigned values 0 - 360. 0 and 360 should be
	 * the same value which is magnetic north. (Need to verify that magnetic
	 * north is the value and not true north). The reason for this value is is
	 * to have some means of waypoint to reference against throughout the game.
	 */
	public final static int COMPASS_ALLIANCE_WALL_HEADING_WAYPOINT = 0;

	/*
	 * End Compass section
	 */
	/*************************** END SENSORS *****************************************/
}

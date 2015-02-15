package org.usfirst.frc.team3277.robot.subsystems;

import java.util.TimerTask;

import org.usfirst.frc.team3277.robot.RobotMap;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CompassSensor extends Subsystem
{
	// Subsystem Devices
	// N/A - Component Level

	private I2C i2c;
	private java.util.Timer updater;
	private CompassUpdater task;
	private CompassData compassDataSet;
	private CompassMagneticData compassMagneticDataSet;

	static private Logger lumberjack;

	static float _hmc5883_Gauss_LSB_XY = 1100.0F; // Varies with gain
	static float _hmc5883_Gauss_LSB_Z = 980.0F; // Varies with gain
	int _magGain;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand()
	{
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public CompassSensor()
	{
		lumberjack = new Logger();
		try
		{
			try
			{
				// Enable I2C
				i2c = new I2C(Port.kMXP, RobotMap.HMC5883_ADDRESS_MAG);
			} catch (Exception e)
			{
				lumberjack.dashLogError("CompassSensorEnableI2C", e.getMessage());
			}
			
			if (!begin())
			{
				lumberjack.dashLogError("CompassSensor", "HMC5883 not detected ... Check your wiring!");
			} 
//			else
//			{
//				boolean bSensorAlive = i2c.addressOnly();
//				if (bSensorAlive)
//				{
//					lumberjack.dashLogError("CompassSensorAlive", "Device did not respond");
//				}
//			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("CompassSensorBeginRemoveMe", e.getMessage());
		}

		try
		{
			task = new CompassUpdater();
			updater = new java.util.Timer();

			compassDataSet = new CompassData();
			compassMagneticDataSet = new CompassMagneticData();
		} catch (Exception e)
		{
			lumberjack.dashLogError("CompassSensorBeginEndRemoveMe", "Error: " + e.getMessage());
		}
	}

	/***************************************************************************
	 * PRIVATE FUNCTIONS
	 ***************************************************************************/

	/**************************************************************************/
	/*
	 * !
	 * 
	 * @brief Abstract away platform differences in Arduino wire library
	 */
	/**************************************************************************/
	/*
	 * Not Used? private byte read8(byte address, byte reg) { byte value;
	 * 
	 * Wire.beginTransmission(address); Wire.write((int) reg);
	 * Wire.endTransmission(); Wire.requestFrom(address, (byte) 1); value =
	 * Wire.read(); Wire.endTransmission();
	 * 
	 * return value; }
	 */

	/**************************************************************************/
	/*
	 * !
	 * 
	 * @brief Reads the raw data from the sensor
	 */
	/**************************************************************************/
	private void read(CompassData compassDataSet)
	{
		try
		{
			byte dataRead[] = null;

			// Read the magnetometer
			i2c.write(RobotMap.HMC5883_ADDRESS_MAG, RobotMap.HMC5883_REGISTER_MAG_OUT_X_H_M);
			i2c.read(RobotMap.HMC5883_ADDRESS_MAG, 6, dataRead);
			// Wait around until enough data is available
			// while (Wire.available() < 6);

			// Note high before low (different than accel)
			byte xhi = dataRead[0];
			byte xlo = dataRead[1];
			byte zhi = dataRead[2];
			byte zlo = dataRead[3];
			byte yhi = dataRead[4];
			byte ylo = dataRead[5];

			// Shift values to create properly formed integer (low byte first)
			compassDataSet.x = (int) Integer.toUnsignedLong(xlo | (xhi << 8));
			compassDataSet.y = (int) Integer.toUnsignedLong(ylo | (yhi << 8));
			compassDataSet.z = (int) Integer.toUnsignedLong(zlo | (zhi << 8));

			// ToDo: Calculate orientation
			// _magData.orientation = 0.0;
		} catch (Exception e)
		{
			lumberjack.dashLogError("CompassSensor", e.getMessage());
		}
	}

	/***************************************************************************
	 * PUBLIC FUNCTIONS
	 ***************************************************************************/

	/**************************************************************************/
	/*
	 * !
	 * 
	 * @brief Setups the HW
	 */
	/**************************************************************************/
	public boolean begin()
	{
		try
		{
			// Enable the magnetometer
			i2c.write(RobotMap.HMC5883_REGISTER_MAG_MR_REG_M, 0x00);
		} catch (Exception e)
		{
			lumberjack.dashLogError("CompassSensorBeginEnable", e.getMessage());
		}

		try
		{
			// Set the gain to a known level
			setMagGain(RobotMap.HMC5883_MAGGAIN_1_3);
		} catch (Exception e)
		{
			lumberjack.dashLogError("CompassSensorSetGainBegin", e.getMessage());
		}

		return true;
	}

	/**************************************************************************/
	/*
	 * !
	 * 
	 * @brief Sets the magnetometer's gain
	 */
	/**************************************************************************/
	public void setMagGain(int gain)
	{
		try
		{
			i2c.write(RobotMap.HMC5883_REGISTER_MAG_CRB_REG_M, gain);

			_magGain = gain;

			switch (gain)
			{
			case RobotMap.HMC5883_MAGGAIN_1_3:
				_hmc5883_Gauss_LSB_XY = 1100;
				_hmc5883_Gauss_LSB_Z = 980;
				break;
			case RobotMap.HMC5883_MAGGAIN_1_9:
				_hmc5883_Gauss_LSB_XY = 855;
				_hmc5883_Gauss_LSB_Z = 760;
				break;
			case RobotMap.HMC5883_MAGGAIN_2_5:
				_hmc5883_Gauss_LSB_XY = 670;
				_hmc5883_Gauss_LSB_Z = 600;
				break;
			case RobotMap.HMC5883_MAGGAIN_4_0:
				_hmc5883_Gauss_LSB_XY = 450;
				_hmc5883_Gauss_LSB_Z = 400;
				break;
			case RobotMap.HMC5883_MAGGAIN_4_7:
				_hmc5883_Gauss_LSB_XY = 400;
				_hmc5883_Gauss_LSB_Z = 255;
				break;
			case RobotMap.HMC5883_MAGGAIN_5_6:
				_hmc5883_Gauss_LSB_XY = 330;
				_hmc5883_Gauss_LSB_Z = 295;
				break;
			case RobotMap.HMC5883_MAGGAIN_8_1:
				_hmc5883_Gauss_LSB_XY = 230;
				_hmc5883_Gauss_LSB_Z = 205;
				break;
			}
		} catch (Exception e)
		{
			lumberjack.dashLogError("CompassSensorSetGain", e.getMessage());
		}
	}

	/**************************************************************************/
	/*
	 * !
	 * 
	 * @brief Gets the most recent sensor event
	 */
	/**************************************************************************/
	public void getEvent()
	{
		try
		{
			/* Clear the event */
			// memset(event, 0, sizeof(sensors_event_t));

			/* Read new data */
			read(compassDataSet);

			// event->version = sizeof(sensors_event_t);
			// event->sensor_id = _sensorID;
			// event->type = RobotMap.SENSOR_TYPE_MAGNETIC_FIELD;
			// event->timestamp = 0;
			compassMagneticDataSet.x = compassDataSet.x / _hmc5883_Gauss_LSB_XY * RobotMap.SENSORS_GAUSS_TO_MICROTESLA;
			compassMagneticDataSet.y = compassDataSet.y / _hmc5883_Gauss_LSB_XY * RobotMap.SENSORS_GAUSS_TO_MICROTESLA;
			compassMagneticDataSet.z = compassDataSet.z / _hmc5883_Gauss_LSB_Z * RobotMap.SENSORS_GAUSS_TO_MICROTESLA;
		} catch (Exception e)
		{
			lumberjack.dashLogError("CompassSensorGetEvent", e.getMessage());
		}
	}

	/**************************************************************************/
	/*
	 * !
	 * 
	 * @brief Gets the sensor_t data
	 */
	/**************************************************************************/
	// public void getSensor(sensor_t *sensor) {
	// /* Clear the sensor_t object */
	// memset(sensor, 0, sizeof(sensor_t));
	//
	// /* Insert the sensor name in the fixed length char array */
	// strncpy (sensor->name, "HMC5883", sizeof(sensor->name) - 1);
	// sensor->name[sizeof(sensor->name)- 1] = 0;
	// sensor->version = 1;
	// sensor->sensor_id = _sensorID;
	// sensor->type = RobotMap.SENSOR_TYPE_MAGNETIC_FIELD;
	// sensor->min_delay = 0;
	// sensor->max_value = 800; // 8 gauss == 800 microTesla
	// sensor->min_value = -800; // -8 gauss == -800 microTesla
	// sensor->resolution = 0.2; // 2 milligauss == 0.2 microTesla
	// }

	public class CompassData
	{
		public float x;
		public float y;
		public float z;
		// public float orientation;
	}

	public class CompassMagneticData
	{
		public float x = 0;
		public float y = 0;
		public float z = 0;
	}

	// Start 10Hz polling
	public void start()
	{
		updater.scheduleAtFixedRate(task, 0, 100);
	}

	// Timer task to keep compass data updated
	private class CompassUpdater extends TimerTask
	{
		public void run()
		{
			while (true)
			{
				getEvent();
				try
				{
					Thread.sleep(10);
				} catch (InterruptedException e)
				{
					lumberjack.dashLogError("CompassSensorUpdater", e.getMessage());
				}
			}
		}
	}

	/**
	 * The log method puts information of interest from the CompassSensor
	 * subsystem to the SmartDashboard.
	 */
	public void dashLog()
	{
		lumberjack.dashLogNumber("CompassX", compassMagneticDataSet.x);
		lumberjack.dashLogNumber("CompassY", compassMagneticDataSet.y);
		lumberjack.dashLogNumber("CompassZ", compassMagneticDataSet.z);
		// SmartDashboard.putNumber("CompassHeading",
		// compassMagneticDataSet.heading);
	}
}

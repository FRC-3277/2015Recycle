package org.usfirst.frc.team3277.robot.subsystems;
/*
 * 3rd and final attempt using an Arduino along with one other I2C device communicating
 * with the roborio.  The problem appears to be that there cannot be two master devices on
 * the I2C bus.  Arduino units can operate in MultiMaster mode, but according to literature
 * they switch roles of which is master and slave based on the write and receive operations.
 */

/*package org.usfirst.frc.team3277.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import java.util.TimerTask;

import org.usfirst.frc.team3277.robot.RobotMap;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;
*/

/*
 *
 *
public class ArduinoI2CRead extends Subsystem {

	private I2C i2c;
	private byte[] arduinoData;
	private java.util.Timer updater;
	private ArduinoSerialUpdater task;
	String arduinoString;

	static private Logger lumberjack;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public ArduinoI2CRead() {
		lumberjack = new Logger();

		i2c = new I2C(Port.kMXP, RobotMap.ARDUINO_ADDR);

		arduinoData = new byte[5];

		task = new ArduinoSerialUpdater();
		updater = new java.util.Timer();
	}

	// Start 10Hz polling
	public void start() {
		updater.scheduleAtFixedRate(task, 0, 100);
	}

	// Start polling for period in milliseconds
	public void start(int period) {
		updater.scheduleAtFixedRate(task, 0, period);
	}

	public void stop() {
		updater.cancel();
	}

	public void update() {
		try {
			i2c.readOnly(arduinoData, 5); // Read in measurement
			// arduinoString = arduinoData.toString();
			Timer.delay(0.01); // Delay to prevent over polling
		} catch (Exception e) {
			lumberjack.dashLogError("ArduinoRead", e.getMessage());
		}

	}

	// Get Heading
	public int getHeading() {
		return (int) Integer.toUnsignedLong(arduinoData[0] << 8) + Byte.toUnsignedInt(arduinoData[1]);
	}

	// Timer task to keep distance updated
	private class ArduinoSerialUpdater extends TimerTask {
		public void run() {
			while (true) {
				update();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					lumberjack.dashLogError("ArduinoI2CRead", e.getMessage());
				}
			}
		}
	}
	*/

	/**
	 * The log method puts information of interest from the LidarSensor
	 * subsystem to the SmartDashboard.
	 *
	public void dashLog() {
		lumberjack.dashLogNumber("Arduino", getHeading());
	}
}
*/

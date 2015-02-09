package org.usfirst.frc.team3277.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * The LIDAR sensor is for distance measurement.  This code was sourced
 * from: https://gist.github.com/anonymous/4aac6d39f0de0e0faac0
 * Note - a forked, FRC team tech2077, version exists at: https://gist.github.com/tech2077/c4ba2d344bdfcddd48d2
 * Important - We experienced failure to acquire data like many posts read about the kOnboard i2c.  We had success with the MXP connector.
 */
public class LidarSensor extends Subsystem implements PIDSource 
{
	private I2C i2c;
	private byte[] distance;
	private java.util.Timer updater;
	private LIDARUpdater task;
	
	private final int LIDAR_ADDR = 0x62;
	private final int LIDAR_CONFIG_REGISTER = 0x00;
	private final int LIDAR_DISTANCE_REGISTER = 0x8f;
	
	static private Logger lumberjack;
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	 
	public LidarSensor() 
	{
		lumberjack = new Logger();
		
		i2c = new I2C(Port.kMXP, LIDAR_ADDR);
		
		distance = new byte[2];
 
		task = new LIDARUpdater();
		updater = new java.util.Timer();
	}
	
	// Distance in cm
	public int getDistance() {
		return (int)Integer.toUnsignedLong(distance[0] << 8) + Byte.toUnsignedInt(distance[1]);
	}
 
	public double pidGet() {
		return getDistance();
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
	
	// Update distance variable.  See the Quick Start Guide for documentation
	public void update() {
		i2c.write(LIDAR_CONFIG_REGISTER, 0x04); // Initiate measurement
		Timer.delay(0.04); // Delay for measurement to be taken
		i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance); // Read in measurement
		Timer.delay(0.01); // Delay to prevent over polling
	}
	
	// Timer task to keep distance updated
	private class LIDARUpdater extends TimerTask {
		public void run() 
		{
			while(true) 
			{
				update();
				try 
				{
					Thread.sleep(10);
				} 
				catch (InterruptedException e) 
				{
					lumberjack.dashLogError("LidarSensor",  e.getMessage());
				}
			}
		}
	}
	
	/**
	 * The log method puts information of interest from the LidarSensor subsystem to the SmartDashboard.
	 */
    public void dashLog() 
    {
    	lumberjack.dashLogNumber("LidarDistance", pidGet());
    }
}
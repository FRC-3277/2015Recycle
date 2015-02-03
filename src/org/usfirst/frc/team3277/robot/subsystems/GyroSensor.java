package org.usfirst.frc.team3277.robot.subsystems;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team3277.robot.Robot;
import org.usfirst.frc.team3277.robot.RobotMap;

/**
 *
 */
public class GyroSensor extends Subsystem {
	// Subsystem devices
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Gyro gyro;
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public GyroSensor()
	{
		super();
		// Configure gyro
		gyro = new Gyro(RobotMap.GYRO_CHANNEL);
		if (Robot.isReal()) 
		{
			gyro.setSensitivity(RobotMap.GYRO_SENSATIVITY); // TODO: Handle more gracefully?
		}
		LiveWindow.addSensor("DriveTrain", "Gyro", gyro);
	}
    
    /**
	 * @return The current angle of the drivetrain.
	 */
	public double getAngle() {
		return gyro.getAngle();
	}
	
	/**
	 * The log method puts information of interest from the GyroSensor subsystem to the SmartDashboard.
	 */
    public void dashLog() 
    {
        //SmartDashboard.putData("Key", value);
    }
}


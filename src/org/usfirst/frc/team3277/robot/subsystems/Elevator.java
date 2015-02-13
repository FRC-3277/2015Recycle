package org.usfirst.frc.team3277.robot.subsystems;

import org.usfirst.frc.team3277.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	// Subsystem devices
	public CANTalon elevatorMotor;

	static private Logger lumberjack;

    public void initDefaultCommand() {
        
    }
    
    public Elevator()
    {
    	lumberjack = new Logger();
    	
    	try 
    	{
    		elevatorMotor = Talon.initTalon(RobotMap.ELEVATOR_MOTOR);
			// Override the default behavior which is to enable.
    		elevatorMotor.enableBrakeMode(true);
    		elevatorMotor.enableControl();
	
		} 
    	catch (Exception e) 
    	{
    		lumberjack.dashLogError("Grabber", "Motor controller failure: " + e.getMessage());
		}
    }
    
    /**
     * Actively raise elevator as long as the button is being pressed.
     */
    public void activeRaiseElevator()
    {
    	elevatorMotor.set(RobotMap.ELEVATOR_MOTOR_SPEED);
    }
    
    /**
     * Actively lower elevator as long as the button is being pressed.
     */
    public void activeLowerElevator()
    {
    	elevatorMotor.set(-RobotMap.ELEVATOR_MOTOR_SPEED);
    }
    
    public void stopMotor()
    {
    	elevatorMotor.set(0);
    }
    
    /**
	 * The log method puts information of interest from the Elevator subsystem to the SmartDashboard.
	 */
    public void dashLog() 
    {
        //SmartDashboard.putData("Key", value);
    }
}


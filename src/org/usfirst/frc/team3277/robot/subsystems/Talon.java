package org.usfirst.frc.team3277.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.can.CANNotInitializedException;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Talon extends Subsystem {
	// Subsystem devices
	// N/A This is the component level
	
	static private Logger lumberjack;
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public Talon()
    {
    	lumberjack = new Logger();
    }
    
    public static CANTalon initTalon(int motor) 
    {
    	boolean bEnableBrakeMode = false;
        int maxRetries = 0;
        CANTalon talon = null;
        
        // Attempt to initialize up to 10 times
        for (maxRetries = 0; talon == null && maxRetries < 10; maxRetries++) {
            try {
                talon = new CANTalon(motor);
            } 
            catch (CANNotInitializedException e) 
            {
            	lumberjack.dashLogError("Talon", "CANTalon(" + motor + ") \"" + "\" error " + e.getMessage());
                Timer.delay(.1);
            }
        }
        
        if (talon == null) 
        {
        	lumberjack.dashLogError("Talon", "CANTalon(" + motor + ") \"" + "\" did not initialize, cannot drive");
        } 
        else 
        {
            try 
            {
                // Set up the encoders
//                talon.configEncoderCodesPerRev(codesPerRev);
//                talon.setSpeedReference(SpeedReference.kEncoder);
//                talon.setPositionReference(CANTalon.PositionReference.kQuadEncoder);
                talon.enableBrakeMode(bEnableBrakeMode);
                System.out.println("CANTalon(" + motor + ") \"" + "\" is set up correctly");
            } 
            catch (CANNotInitializedException ex) 
            {
            	lumberjack.dashLogError("Talon", "CANTalon(" + motor + ") \"" + "\" error configuring encoder: " + ex.getMessage());
            }
        }
        
        return talon;
    }
    
    /**
	 * The log method puts information of interest from the Talon subsystem to the SmartDashboard.
	 */
    public void dashLog() 
    {
        //SmartDashboard.putData("Key", value);
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team3277.robot;
import edu.wpi.first.wpilibj.CANJaguar;

/**
 *
 * @author Robot_2
 */
public class FRCDriveControl {
   private final int LEFT_FRONT_JAG = 2;
   private final int LEFT_REAR_JAG = 3;
   private final int RIGHT_FRONT_JAG = 4;
   private final int RIGHT_REAR_JAG = 1;
   private CANJaguar LeftFront;
   private CANJaguar LeftRear;
   private CANJaguar RightFront;
   private CANJaguar RightRear;
   private RobotDrive FRCRobotDrive;
   
   public FRCDriveControl()
   {
       LeftFront = Jaguar.initJag(LEFT_FRONT_JAG);
       LeftRear = Jaguar.initJag(LEFT_REAR_JAG);
       RightFront = Jaguar.initJag(RIGHT_FRONT_JAG);
       RightRear = Jaguar.initJag(RIGHT_REAR_JAG);
       FRCRobotDrive = new RobotDrive(LeftFront, LeftRear, RightFront, RightRear);
       FRCRobotDrive.setSafetyEnabled(false);

       FRCRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
       FRCRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
   }

   public void Drive(double x, double y, double twist)
   {
       Drive(x, y, twist, 1.0);
   }
   
   public void Drive(double x, double y, double twist, double sensitivity)
   {
       if (sensitivity > 1.0)
           sensitivity = 1.0;
       if (sensitivity < 0.0)
           sensitivity = 0.0;
       FRCRobotDrive.mecanumDrive_Cartesian(x * sensitivity, y * sensitivity, twist * sensitivity, 0);
   }
    
   
}

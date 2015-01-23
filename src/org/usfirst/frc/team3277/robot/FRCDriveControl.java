/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team3277.robot;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 *
 * @author Robot_2
 */
public class FRCDriveControl {
   private final int LEFT_FRONT_JAG = 1;
 //  private final int LEFT_REAR_JAG = 3;
   private final int RIGHT_FRONT_JAG = 2;
 //  private final int RIGHT_REAR_JAG = 1;
   private CANJaguar LeftFront;
//   private CANJaguar LeftRear;
   private CANJaguar RightFront;
//   private CANJaguar RightRear;
   private RobotDrive FRCRobotDrive;
   
   public FRCDriveControl()
   {
       LeftFront = new CANJaguar(LEFT_FRONT_JAG);
    //   LeftRear = Jaguar.initJag(LEFT_REAR_JAG);
       RightFront = new CANJaguar(RIGHT_FRONT_JAG);
      // RightRear = Jaguar.initJag(RIGHT_REAR_JAG);
       FRCRobotDrive = new RobotDrive(LeftFront, RightFront);
       FRCRobotDrive.setSafetyEnabled(false);

       FRCRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
   //    FRCRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
   }

   public void Drive(double x, double y, double twist)
   {
	   //TODO: What does this do?
       Drive(x, y, twist);
   }
   
   public void Drive(int y, int x)
   {
       
       FRCRobotDrive.arcadeDrive(y, x);
   }
    
   
}

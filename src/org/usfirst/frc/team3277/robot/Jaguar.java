/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team3277.robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Robot_2
 */
public class Jaguar {
    public static CANJaguar initJag(int motor) {
        return initJag(motor, CANJaguar.NeutralMode.Brake);
    }

    public static CANJaguar initJag(int motor, CANJaguar.NeutralMode neutralMode) {
        int maxRetries = 0;
        CANJaguar jag = null;
        // Attempt to initialize up to 10 times
        for (maxRetries = 0; jag == null && maxRetries < 10; maxRetries++) {
            try {
                jag = new CANJaguar(motor);
            } catch (Exception e) {
                System.out.println("CANJaguar(" + motor + ") \"" + "\" error " + e.getMessage());
                Timer.delay(.1);
            }
        }
        if (jag == null) {
            System.out.println("CANJaguar(" + motor + ") \"" + "\" did not initialize, cannot drive");
        } else {
            try {
                // Set up the encoders
//                jag.configEncoderCodesPerRev(codesPerRev);
//                jag.setSpeedReference(SpeedReference.kEncoder);
//                jag.setPositionReference(CANJaguar.PositionReference.kQuadEncoder);
                jag.configNeutralMode(neutralMode);
                System.out.println("CANJaguar(" + motor + ") \"" + "\" is set up correctly");
            } catch (CANTimeoutException ex) {
                System.out.println("CANJaguar(" + motor + ") \"" + "\" error configuring encoder: " + ex.getMessage());
            }
        }
        return jag;
    }
}

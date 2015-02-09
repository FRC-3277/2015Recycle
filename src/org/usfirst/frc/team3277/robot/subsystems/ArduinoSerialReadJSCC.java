/*
 *  Removed since jSCC was not able to build successfully as a WPILib project, but would run
 *  as a Java application without fail.  Preserved for code snippets.
 */
//
//package org.usfirst.frc.team3277.robot.subsystems;
//
//import edu.wpi.first.wpilibj.command.Subsystem;
//
//import jssc.SerialPort;
////import jssc.SerialPortEvent;
////import jssc.SerialPortEventListener;
//import jssc.SerialPortException;
//
///**
// *
// */
//public class ArduinoSerialReadJSCC extends Subsystem {
//	SerialPort serialPort;
//	byte[] buffer;
//	
//    // Put methods for controlling this subsystem
//    // here. Call these from Commands.
//
//    public void initDefaultCommand() {
//        // Set the default command for a subsystem here.
//        //setDefaultCommand(new MySpecialCommand());
//    }
//    
//    public ArduinoSerialReadJSCC(String portName)
//    {
//    	/*
//    	 * Options are:
//    	 * /dev/tty.usbserial-A9007UX1", // OSX
//    	 * "/dev/ttyACM0", // Raspberry Pi
//    	 * "/dev/ttyUSB0", // Linux
//    	 * "COM3", // Windows
//    	 */
//    	
//    	serialPort = new SerialPort(portName);
//    	
//    	try {
//            serialPort.openPort();//Open serial port
//            serialPort.setParams(9600, 8, 1, 0);//Set params.
//            buffer = serialPort.readBytes(10);//Read 10 bytes from serial port
//            System.out.println(buffer.toString());
//            serialPort.closePort();//Close serial port
//        }
//        catch (SerialPortException ex) {
//            System.out.println(ex);
//        }
//    }
//}
//

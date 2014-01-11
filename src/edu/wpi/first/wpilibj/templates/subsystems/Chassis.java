/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import Team102Lib.MathLib;
import Team102Lib.MaxbotixSonar;
import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoysticks;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.DriveWithXBox;

/**
 *
 * @author Administrator
 */
public class Chassis extends Subsystem {

    RobotDrive drive;
    public Gyro gyro;
    public MaxbotixSonar rangeFinder;
    double range;
    double righty;
    double lefty;
    double x;
    double y;
    double leftJoyY;
    double leftJoyX;
    double rightJoyY;
    double rightJoyX;

/*    double change;
    double temperature;
    AnalogChannel temp =  new AnalogChannel(2);
 * 
 */

    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithXBox());
    }

    public Chassis() {
        drive = new RobotDrive(RobotMap.frontLeftMotor, RobotMap.rearLeftMotor, RobotMap.frontRightMotor, RobotMap.rearRightMotor);
        drive.setSafetyEnabled(false);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);

        // Gyro setup
        gyro = new Gyro(RobotMap.gyroPort);
        rangeFinder = new MaxbotixSonar(RobotMap.ultrasonic);
        range = rangeFinder.getRangeInches();
        gyro.reset();


     /*   change = temp.getVoltage() - 2.5;
        change *= 200;
        temperature = 77 + change;
      *
      */
    }

    public void straight() { // sets the motor speeds to drive straight (no turn)
        drive.arcadeDrive(1.0, 0.0);
    }

    public void turnLeft() { // sets the motor speeds to start a left turn
        System.out.println(":) Turning Left.");
        drive.arcadeDrive(0.0, 0.5);
    }

    public void turnRight() {
        System.out.println(":) Turning Right.");
        drive.arcadeDrive(0.0, -0.5);
    }


    public void driveWithJoystick(Joystick stick) {
//        drive.arcadeDrive(stick);

        DriverStation ds = DriverStation.getInstance();
        DriverStationLCD lcd = DriverStationLCD.getInstance();

        x = stick.getX();
        y = stick.getY();
        double twist = stick.getTwist();


//        lcd.println(DriverStationLCD.Line.kMain6, 1, "PreX: " + (int)(x * 100));
//        lcd.println(DriverStationLCD.Line.kMain6, 1, "                     ");
//        lcd.println(DriverStationLCD.Line.kUser2, 1, "                     ");
//        lcd.println(DriverStationLCD.Line.kUser2, 1, "PreY: " + (int) (y * 100));
//        lcd.println(DriverStationLCD.Line.kUser3, 1, "                     ");
//        lcd.println(DriverStationLCD.Line.kUser3, 1, "PreTw: " + (int) (twist * 100));
//
//        System.out.print(":) PreX: ");
//        System.out.println(x);
//        System.out.print(":) PreY: ");
//        System.out.println(y);
//        System.out.print(":) PreTw: ");
//        System.out.println(twist);

        x = RobotMap.stickDeadBand.Deaden(x);
        y = RobotMap.stickDeadBand.Deaden(y);
        twist = RobotMap.twistDeadBand.Deaden(twist + RobotMap.twistCorrection);

//        lcd.println(DriverStationLCD.Line.kUser4, 1, "                     ");
//        lcd.println(DriverStationLCD.Line.kUser4, 1, "X: " + (int) (MathLib.round(x, 3) * 100));
//        lcd.println(DriverStationLCD.Line.kUser5, 1, "                     ");
//        lcd.println(DriverStationLCD.Line.kUser5, 1, "Y: " + (int) (MathLib.round(y, 3) * 100));
//        lcd.println(DriverStationLCD.Line.kUser6, 1, "                     ");
//        lcd.println(DriverStationLCD.Line.kUser6, 1, "Tw: " + (int) (MathLib.round(twist, 3) * 100));

//        lcd.println(DriverStationLCD.Line.kUser4, 1, "                     ");
//        lcd.println(DriverStationLCD.Line.kUser4, 1, "X: " + (int)(x * 100));
//        lcd.println(DriverStationLCD.Line.kUser5, 1, "                     ");
//        lcd.println(DriverStationLCD.Line.kUser5, 1, "Y: " + (int) (y * 100));
//        lcd.println(DriverStationLCD.Line.kUser6, 1, "                     ");
//        lcd.println(DriverStationLCD.Line.kUser6, 1, "Tw: " + (int) (twist * 100));


//        System.out.print(":) X: ");
//        System.out.println(x);
//        System.out.print(":) Y: ");
//        System.out.println(y);
//        System.out.print(":) Tw: ");
//        System.out.println(twist);

        lcd.println(DriverStationLCD.Line.kMain6, 1, "                     ");
        lcd.println(DriverStationLCD.Line.kMain6, 1, "GyRem: " + (int) (gyro.getAngle() % 360));
        lcd.println(DriverStationLCD.Line.kUser2, 1, "                     ");
        lcd.println(DriverStationLCD.Line.kUser2, 1, "Gyro: " + (int) (gyro.getAngle()));
        
        lcd.updateLCD();
    }

     public void driveWithJoysticks(Joystick leftstick, Joystick rightstick) {

        righty = rightstick.getY();
        lefty = leftstick.getY();

        righty = RobotMap.stickDeadBand.Deaden(righty);
        lefty = RobotMap.stickDeadBand.Deaden(lefty);

        drive.tankDrive(lefty, righty);
    }
     public void driveWithXBox(Joystick xBox) {

        leftJoyX = xBox.getRawAxis(RobotMap.xBoxLeftXAxis);
        leftJoyY = xBox.getRawAxis(RobotMap.xBoxLeftYAxis);
        rightJoyX = xBox.getRawAxis(RobotMap.xBoxRightXAxis);
//        rightJoyY = xBox.getRawAxis(RobotMap.xBoxRightYAxis);

        leftJoyX = RobotMap.stickDeadBand.Deaden(leftJoyX);
        leftJoyY = RobotMap.stickDeadBand.Deaden(leftJoyY);
        rightJoyX = RobotMap.twistDeadBand.Deaden(rightJoyX + RobotMap.twistCorrection);

        MessageLogger.LogMessage("Joysticks LX, LY, RX, RY: " 
                + MathLib.round(leftJoyX, 3) 
                + ", " + MathLib.round(leftJoyY , 3)
                + ", " + MathLib.round(rightJoyX, 3)
                + ", " + MathLib.round(rightJoyY, 3));

        drive.mecanumDrive_Cartesian(leftJoyX, rightJoyX, leftJoyY, 0);
//        drive.tankDrive(rightJoyY, leftJoyY);

    }
     public void updateStatus()
    {
        SmartDashboard.putDouble("X: ", x);
        SmartDashboard.putDouble("Y: ", y);
        SmartDashboard.putDouble("Gyro: ", gyro.getAngle());
        SmartDashboard.putDouble("Gyro Rem: ", (gyro.getAngle() % 360));
        SmartDashboard.putDouble("Range: ", rangeFinder.getRangeInches());
     }
}

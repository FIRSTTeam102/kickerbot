package edu.wpi.first.wpilibj.templates;

import Team102Lib.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.

    // PWM Ports
    public static final int frontLeftMotor = 1;
    public static final int frontRightMotor = 2;
    public static final int rearLeftMotor = 3;
    public static final int rearRightMotor = 4;

    // Analog Input Ports
    public static final int gyroPort = 1;
    public static final int ultrasonic = 2;

    // Joystick Setup
    public static final double joystickRange = 1.0d; // the range of the joystick around 0.0
    public static final double flatDeadband = 0.02d;        // The amount of flat space in the deadband (around 0.0)
    public static Deadband stickDeadBand = null;    // Used to create a smooth deadband for the stick.
    public static Deadband twistDeadBand = null;    // Used to create a smooth deadband for the twist.
    public static final double twistCorrection = +0.07;

    //Digital Outputs
    public static final int compressorSwitchChannel = 1;

    //Digital Inputs
    public static final int winchLimitSwitchport = 3;
    public static final int encoderPortA = 14;
    public static final int encoderPortB = 13;

    // Solenoid Modules
    public static final int solenoidModule = 2;
    public static final int clutchSolenoidReleasePort = 3;
    public static final int clutchSolenoidHoldPort = 4;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
}

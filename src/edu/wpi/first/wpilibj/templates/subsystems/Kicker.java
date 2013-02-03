/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.SetSetPointWithTrigger;

/**
 *
 * @author Administrator
 */
public class Kicker extends Subsystem {

	// CONSTANTS
    final int ENCODER_MAX_VALUE = 700;
    final double MOTOR_SCALE_FACTOR = 0.7;
    final double SET_POINT_SCALE_VALUE = 20.0;
	public static final double TIME_TO_RELEASE_CLUTCH = 0.4;
	
	
	// SENSORS AND ACTUATORS
    Encoder encoderWinch; // = new Encoder(RobotMap.encoderPortA, RobotMap.encoderPortB);
    Victor winchMotor;
    Solenoid solenoid1;
    Solenoid solenoid2;
    Solenoid clutchSolenoidRelease;
    Solenoid clutchSolenoidHold;
    DigitalInput winchLimitSwitch;
	
	// OTHER MEMBERS
	int winchEncoderSetPoint = 0;

    public Kicker()
    {
        winchMotor = new Victor(5);
        winchLimitSwitch = new DigitalInput(RobotMap.winchLimitSwitchport);
        solenoid1 = new Solenoid(RobotMap.solenoidModule, 1);
        solenoid2 = new Solenoid(RobotMap.solenoidModule, 2);
        clutchSolenoidRelease = new Solenoid(RobotMap.solenoidModule, RobotMap.clutchSolenoidReleasePort);
        clutchSolenoidHold = new Solenoid(RobotMap.solenoidModule,RobotMap.clutchSolenoidHoldPort);
        encoderWinch = new Encoder(RobotMap.encoderPortA, RobotMap.encoderPortB);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new SetSetPointWithTrigger());    // Always look to set the setPoint using the xBox trigger.
    }
    public void engageSprings()
    {
        solenoid1.set(false);
        solenoid2.set(true);
    }
    public void disengageSprings()
    {
        solenoid1.set(true);
        solenoid2.set(false);
    }

    public void engageClutch()
    {
        clutchSolenoidRelease.set(false);
        clutchSolenoidHold.set(true);
    }
    public void disengageClutch()
    {
        System.out.println("disengageClutch");
        clutchSolenoidRelease.set(true);
        clutchSolenoidHold.set(false);
        zeroEncoder();                      // Auto zero the encoder after releasing to prevent overwinding.
    }
    public void zeroEncoder()
    {
        encoderWinch.reset();
        encoderWinch.start();
    }
    public void turnOnWinchMotor()
    {
        System.out.println("Turning on winch Motor");
        engageClutch();
        winchMotor.set(0.40);
    }
    public void turnOffWinchMotor()
    {
        System.out.println("Turning off winch Motor");
        winchMotor.set(0.0);
    }
    public boolean winchLimitReached()
    {
//        System.out.println("winchLimitSwitch: " + winchLimitSwitch.get());
        return !winchLimitSwitch.get();
    }
    public boolean encoderLimitReached(int limit)
    {
//        System.out.println("encoderWinch/limit: " + encoderWinch.get() + "/" + limit);
        return (encoderWinch.get() >= limit);
    }
    public boolean winchSetPointReached()
    {
        return encoderLimitReached(winchEncoderSetPoint);
    }

    public void updateStatus()
    {
        SmartDashboard.putInt("Winch Encoder:", encoderWinch.get());
        SmartDashboard.putInt("Winch Set Point:", winchEncoderSetPoint);
    }

	
	// NOTE: These two functions both use the xBox Trigger.  ONLY ONE OF THEM SHOULD BE USED 
	// AND IT SHOULD BE THE DEFAULT COMMAND FOR THIS SUBSYSTEM.
	
    // Arm the kicker using the right trigger of the xBox controller.
    public void armWithTrigger(Joystick xBox) {
        double triggerValue = xBox.getAxis(Joystick.AxisType.kZ);
        if((triggerValue < 0)  // 0 -> -1 is the right trigger on the xBox
           && !encoderLimitReached(ENCODER_MAX_VALUE)
           && !winchLimitReached())
            winchMotor.set(-triggerValue * MOTOR_SCALE_FACTOR);
        else
            winchMotor.set(0.0);
    }
    // Change the set point of the encoder using the xBox Right and Left triggers.
    public void setSetPointWithTrigger(Joystick xBox) {
        double triggerValue = -xBox.getAxis(Joystick.AxisType.kZ);
        winchEncoderSetPoint += triggerValue * SET_POINT_SCALE_VALUE;
		if(winchEncoderSetPoint > ENCODER_MAX_VALUE)
			winchEncoderSetPoint = ENCODER_MAX_VALUE;
		else if(winchEncoderSetPoint < 0)
			winchEncoderSetPoint = 0;
    }
}
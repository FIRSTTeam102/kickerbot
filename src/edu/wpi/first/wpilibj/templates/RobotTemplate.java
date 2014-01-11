/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import Team102Lib.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.EngageSprings;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    Command autonomousCommand;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() { // called on robot start (powered on)
        try
        {
            CommandBase.init(); // initialize commands and the OI (created by Netbeans)
            SmartDashboard.putData("Scheduler", Scheduler.getInstance());
            updateStatus();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In disabledInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }

    public void autonomousInit() {
        try
        {
        // schedule the autonomous command (example)
            updateStatus();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In disabledInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

  public void startCompetiton() {
      
  }
    public void teleopInit() {
        try
        {
            // This makes sure that the autonomous stops running when
            // teleop starts running. If you want the autonomous to 
            // continue until interrupted by another command, remove
            // this line or comment it out.
    //        autonomousCommand.cancel();
            DriverStation ds = DriverStation.getInstance();

            // ATTENTION: getAnalog does not work in robotInit()!!  (except in debug mode :()
            double stickDeadening = ds.getAnalogIn(1) + 1;
            double twistDeadening = ds.getAnalogIn(2) + 1;
            double speedScale = ds.getAnalogIn(3) / 5.0;
            RobotMap.stickDeadBand = new Deadband(RobotMap.joystickRange, RobotMap.flatDeadband, stickDeadening, speedScale);
            RobotMap.twistDeadBand = new Deadband(RobotMap.joystickRange, RobotMap.flatDeadband, twistDeadening, speedScale);

            Scheduler.getInstance().add(new EngageSprings(true));
            updateStatus();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In disabledInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        try
        {
            Scheduler.getInstance().run();
            updateStatus();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In disabledInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }
    public void updateStatus()
    {
        CommandBase.chassis.updateStatus();
        CommandBase.kicker.updateStatus();
        CommandBase.pnuematics.updateStatus();        
    }
    public void teleopDisable()
    {
        try
        {
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In disabledInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }
    public void disabledInit()
    {
        try
        {
        Scheduler.getInstance().add(new EngageSprings(false));
        Scheduler.getInstance().run();
        updateStatus();
        }
        catch(Exception ex1)
        {
            MessageLogger.LogError("Exception In disabledInit.");
            MessageLogger.LogError(ex1.toString());
            ex1.printStackTrace();
        }
    }
}

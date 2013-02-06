/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.commands.GetRange;


/**
 *
 * @author Admin
 */
public class RangeSensor extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
   AnalogChannel m_inputChannel = new AnalogChannel(3);
   double input = 0.0;
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GetRange());
    }
    
    public void getRange() {
        input = (m_inputChannel.getValue()); 
//        MessageLogger.LogMessage("value: " + input);
    }
 }


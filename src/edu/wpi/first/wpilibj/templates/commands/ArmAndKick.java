/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Administrator
 */
public class ArmAndKick extends CommandGroup {

    public ArmAndKick(int power) {
        addSequential(new ArmKicker(power));
        addSequential(new Kick());
    }
}
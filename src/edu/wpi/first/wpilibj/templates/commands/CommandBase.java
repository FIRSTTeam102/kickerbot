package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.Chassis;
import edu.wpi.first.wpilibj.templates.subsystems.Kicker;
import edu.wpi.first.wpilibj.templates.subsystems.Pnuematics;
import edu.wpi.first.wpilibj.templates.subsystems.RangeSensor;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static Chassis chassis = new Chassis();
    public static Pnuematics pnuematics = new Pnuematics();
    public static Kicker kicker = new Kicker();
    public static RangeSensor rangeSensor = new RangeSensor();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
/*        SmartDashboard.putData(chassis);
        SmartDashboard.putData(pnuematics);
        SmartDashboard.putData(kicker);
        SmartDashboard.putData(new CompressorOn());
        SmartDashboard.putData(new CompressorOff());
        SmartDashboard.putData(new ArmAndKickOld());
        SmartDashboard.putData(new EngageSprings(true));
        SmartDashboard.putData("Disengage Springs", new EngageSprings(false));
        SmartDashboard.putData(new EngageClutch(true));
        SmartDashboard.putData("Disengage Clutch", new EngageClutch(false));
        SmartDashboard.putData(new ArmKicker(150));
        SmartDashboard.putData(new Kick());
*/

    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}



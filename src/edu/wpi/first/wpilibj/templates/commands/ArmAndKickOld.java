/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;


/**
 *
 * @author Administrator
 */
public class ArmAndKickOld extends CommandBase {

    public ArmAndKickOld() {
        requires(kicker);
        this.setTimeout(0.5);   // While testing only wind for short time.
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        kicker.turnOnWinchMotor();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (kicker.winchLimitReached() || this.isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
        kicker.turnOffWinchMotor();
        kicker.disengageClutch();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        kicker.turnOffWinchMotor();
    }
}
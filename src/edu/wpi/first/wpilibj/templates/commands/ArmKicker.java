/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;


/**
 *
 * @author Administrator
 */
public class ArmKicker extends CommandBase {

    int encoderTarget;
    public ArmKicker(int target) {
        // Use requires() here to declare subsystem dependencies
        requires(kicker);
        this.encoderTarget = target;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // make sure if this is run twice we do not overwind.
        if(!kicker.encoderLimitReached(encoderTarget))
        {
            kicker.zeroEncoder();
            kicker.turnOnWinchMotor();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (kicker.winchLimitReached() || kicker.encoderLimitReached(encoderTarget));
    }

    // Called once after isFinished returns true
    protected void end() {
        kicker.turnOffWinchMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
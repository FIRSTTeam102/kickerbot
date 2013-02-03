/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author Administrator
 */
public class DriveToZero extends CommandBase {

    public DriveToZero() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double angleRemainder =  chassis.gyro.getAngle() % 360;
        if (angleRemainder > 0.0 && angleRemainder <= 180.0) {
            chassis.turnLeft();
        } else if (angleRemainder > 180.0 && angleRemainder < 360.0) {
            chassis.turnRight();
        } else if (angleRemainder >= -180.0 && angleRemainder < 0.0) {
            chassis.turnRight();
        } else if (angleRemainder < -180.0 && angleRemainder > -360.0) {
            chassis.turnLeft();
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        double angle = chassis.gyro.getAngle() % 360;

        if ((angle > -5.0) && (angle < 5.0)) {
            return true;
        } else {
            return false;
        }

    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

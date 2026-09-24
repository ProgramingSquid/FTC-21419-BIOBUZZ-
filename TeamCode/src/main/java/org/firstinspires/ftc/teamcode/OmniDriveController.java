package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;


//This is a class containing all logic for handling robot-relative Omni-Drive, decoupled from hardware and OpMode(s)
//Adapted from OmniDrive_LinearOoMode
public class OmniDriveController {
    public static class DriveInput {
        private final double drive;
        private final double strafe;
        private final double turn;
        private final double speedCoefficient;

        public DriveInput(double driveInput, double strafeInput, double turnInput,
                          double speedCoefficient) {
            this.drive = driveInput;
            this.strafe = strafeInput;
            this.turn = turnInput;
            this.speedCoefficient = speedCoefficient;
        }
    }

    /**
     * Compute auto-drive commands to approach an AprilTag and apply them to the robot.
     * Uses constants from RobotUtility.
     */
    public boolean autoDriveToAprilTag(AprilTagDetection desiredTag, Telemetry telemetry) {
        return autoDriveToAprilTag(desiredTag, telemetry, 1, RobotUtility.DEFAULT_DESIRED_DISTANCE);
    }
    public boolean autoDriveToAprilTag(AprilTagDetection desiredTag, Telemetry telemetry, double aimErrorCoef) {
        return autoDriveToAprilTag(desiredTag, telemetry, aimErrorCoef, RobotUtility.DEFAULT_DESIRED_DISTANCE);
    }
    public boolean autoDriveToAprilTag(AprilTagDetection desiredTag, Telemetry telemetry, double aimErrorCoef, double targetDist) {
        if (desiredTag == null) return false;

        double rangeError = (desiredTag.ftcPose.range - targetDist) * aimErrorCoef;
        double headingError = desiredTag.ftcPose.bearing;
        double yawError = desiredTag.ftcPose.yaw * aimErrorCoef;

        double drive = Range.clip(rangeError * RobotUtility.SPEED_GAIN, -RobotUtility.MAX_AUTO_SPEED, RobotUtility.MAX_AUTO_SPEED);
        double turn = Range.clip(headingError * RobotUtility.TURN_GAIN, -RobotUtility.MAX_AUTO_TURN, RobotUtility.MAX_AUTO_TURN);
        double strafe = Range.clip(-yawError * RobotUtility.STRAFE_GAIN, -RobotUtility.MAX_AUTO_STRAFE, RobotUtility.MAX_AUTO_STRAFE);

        moveRobot(-drive, strafe, turn);
        if (telemetry != null) telemetry.addData("Auto","Drive %5.2f, Strafe %5.2f, Turn %5.2f ", drive, strafe, turn);

        return Math.abs(rangeError) <= RobotUtility.DESTINATION_ERROR_BUFFER
                && Math.abs(headingError) <= RobotUtility.DESTINATION_ERROR_BUFFER
                && Math.abs(yawError) <= RobotUtility.DESTINATION_ERROR_BUFFER;
    }

    private final RobotHardware hardware;
    private double leftFrontPower;
    private double rightFrontPower;
    private double leftBackPower;
    private double rightBackPower;

    public OmniDriveController(RobotHardware hardware) {
        this.hardware = hardware;
    }

    public void moveRobot(double drive, double strafe, double turn, double speedMult) {
        DriveInput input = new DriveInput(drive, strafe, turn, speedMult);
        moveRobot(input);
    }
    public void moveRobot(double drive, double strafe, double turn) {
        moveRobot(drive, strafe, turn, 1f);
    }
    public void moveRobot(DriveInput input) {
        leftFrontPower  = input.drive + input.strafe + input.turn;
        rightFrontPower = input.drive - input.strafe - input.turn;
        leftBackPower   = input.drive - input.strafe + input.turn;
        rightBackPower  = input.drive + input.strafe - input.turn;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        double max;

        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

        //Ensure speedCoefficient is between 0 & 1
        double speedCoefficient = Range.clip(input.speedCoefficient, 0, 1);

        // Send calculated power to wheels
        hardware.leftFrontMotor.setPower(leftFrontPower * speedCoefficient);
        hardware.rightFrontMotor.setPower(rightFrontPower * speedCoefficient);
        hardware.leftBackMotor.setPower(leftBackPower * speedCoefficient);
        hardware.rightBackMotor.setPower(rightBackPower * speedCoefficient);
    }

    public void printHeader(Telemetry telemetry) {
        telemetry.addLine(" ");
        telemetry.addLine("===================================");
        telemetry.addLine("OmniDrive Info:");
    }

    public void printMotorPowerInfo(Telemetry telemetry) {
        telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
        telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
    }
}


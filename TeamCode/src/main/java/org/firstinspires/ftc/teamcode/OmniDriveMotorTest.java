package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Hardware and mixer test for the four-wheel omni drive. Hold a control while
 * the robot is safely lifted or supported.
 */
@TeleOp(name = "Omni Drive Motor Test", group = "Robot Test")
public class OmniDriveMotorTest extends LinearOpMode {

    private static final double TEST_POWER = 0.25;

    @Override
    public void runOpMode() {
        RobotHardware hardware = new RobotHardware(hardwareMap);
        OmniDriveController driveController = new OmniDriveController(hardware);

        setAllPower(hardware, 0);
        telemetry.addLine("Omni drive motor test ready");
        telemetry.addLine("Lift/support robot before testing");
        telemetry.addLine("D-pad: forward/back/strafe left/strafe right");
        telemetry.addLine("Bumpers: turn left/turn right");
        telemetry.addLine("X/A/Y/B: front-left/back-left/front-right/back-right");
        telemetry.update();

        waitForStart();

        try {
            while (opModeIsActive()) {
                TestCommand command = selectedCommand();
                applyCommand(hardware, driveController, command);

                telemetry.addData("Test", command.label);
                telemetry.addData("Power", "%.2f", TEST_POWER);
                telemetry.addData("front_left_drive", "%.2f", hardware.leftFrontMotor.getPower());
                telemetry.addData("back_left_drive", "%.2f", hardware.leftBackMotor.getPower());
                telemetry.addData("front_right_drive", "%.2f", hardware.rightFrontMotor.getPower());
                telemetry.addData("back_right_drive", "%.2f", hardware.rightBackMotor.getPower());
                telemetry.update();
            }
        } finally {
            setAllPower(hardware, 0);
        }
    }

    private TestCommand selectedCommand() {
        if (gamepad1.x) {
            return new TestCommand("Individual: front left", 1, 0, 0, 0);
        }
        if (gamepad1.a) {
            return new TestCommand("Individual: back left", 0, 1, 0, 0);
        }
        if (gamepad1.y) {
            return new TestCommand("Individual: front right", 0, 0, 1, 0);
        }
        if (gamepad1.b) {
            return new TestCommand("Individual: back right", 0, 0, 0, 1);
        }

        if (gamepad1.dpad_up) {
            return new TestCommand("Omni: forward", 1, 0, 0, 0);
        }
        if (gamepad1.dpad_down) {
            return new TestCommand("Omni: backward", -1, 0, 0, 0);
        }
        if (gamepad1.dpad_left) {
            return new TestCommand("Omni: strafe left", 0, -1, 0, 0);
        }
        if (gamepad1.dpad_right) {
            return new TestCommand("Omni: strafe right", 0, 1, 0, 0);
        }
        if (gamepad1.left_bumper) {
            return new TestCommand("Omni: turn left", 0, 0, -1, 0);
        }
        if (gamepad1.right_bumper) {
            return new TestCommand("Omni: turn right", 0, 0, 1, 0);
        }

        return new TestCommand("Stopped", 0, 0, 0, 0);
    }

    private void applyCommand(RobotHardware hardware, OmniDriveController driveController,
            TestCommand command) {
        if (command.individualMotor != 0) {
            hardware.leftFrontMotor.setPower(command.individualMotor == 1 ? TEST_POWER : 0);
            hardware.leftBackMotor.setPower(command.individualMotor == 2 ? TEST_POWER : 0);
            hardware.rightFrontMotor.setPower(command.individualMotor == 3 ? TEST_POWER : 0);
            hardware.rightBackMotor.setPower(command.individualMotor == 4 ? TEST_POWER : 0);
            return;
        }

        driveController.moveRobot(command.drive, command.strafe, command.turn, TEST_POWER);
    }

    private void setAllPower(RobotHardware hardware, double power) {
        hardware.leftFrontMotor.setPower(power);
        hardware.leftBackMotor.setPower(power);
        hardware.rightFrontMotor.setPower(power);
        hardware.rightBackMotor.setPower(power);
    }

    private static class TestCommand {

        private final String label;
        private final double drive;
        private final double strafe;
        private final double turn;
        private final int individualMotor;

        private TestCommand(String label, double drive, double strafe, double turn, int individualMotor) {
            this.label = label;
            this.drive = drive;
            this.strafe = strafe;
            this.turn = turn;
            this.individualMotor = individualMotor;
        }
    }
}

package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="OmniDrive_LinearOpMode_Delegated", group="Robot")
public class LinearOpModeDelegatedController extends LinearOpMode {
    private final ElapsedTime runtime = new ElapsedTime();
    private OmniDriveController driveController;


    @Override
    public void runOpMode() {
        RobotHardware hardware = new RobotHardware(hardwareMap);
        driveController = new OmniDriveController(hardware);

        // Wait for driver to press start
        telemetry.addLine("Robot Ready.");
        telemetry.update();

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {
            telemetry.addData("Status", "Run Time: " + runtime.toString());

            //handleAutoDrive();
            handleDrive();

            driveController.printHeader(telemetry);
            driveController.printMotorPowerInfo(telemetry);

            // Shooting logic and telemetry now handled by ShootingController
            //shootingController.update();
            
            telemetry.update();
        }
    }

    private void handleDrive() {
        double speedCoefficient = RobotUtility.DEFAULT_SPEED_COEF;

        if (gamepad1.left_bumper) {
            speedCoefficient = RobotUtility.SLOW_SPEED_COEF;
        } else if (gamepad1.right_bumper) {
            speedCoefficient = RobotUtility.FAST_SPEED_COEF;
        }

        double yawFromTriggers = (-gamepad1.left_trigger + gamepad1.right_trigger) * 0.85;
        OmniDriveController.DriveInput input = new OmniDriveController.DriveInput(
                gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -(gamepad1.right_stick_x + yawFromTriggers),
                speedCoefficient
        );

        driveController.moveRobot(input);
    }
}

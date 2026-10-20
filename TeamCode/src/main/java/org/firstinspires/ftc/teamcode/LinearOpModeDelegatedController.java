package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="OmniDrive_LinearOpMode_Delegated", group="Robot")
public class LinearOpModeDelegatedController extends LinearOpMode {
    private final ElapsedTime runtime = new ElapsedTime();
    private OmniDriveController driveController;
    private boolean activateFlywheel;


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

            if(gamepad1.aWasReleased())
                activateFlywheel = !activateFlywheel;

            if(activateFlywheel)
                hardware.shootMotor.setVelocity(0.55f * hardware.shootMotor.getMotorType().getAchieveableMaxTicksPerSecond());
            else
                hardware.shootMotor.setVelocity(0f);

            if(gamepad1.left_trigger_pressed) //Front Intake
                hardware.intakeMotor.setVelocity(0.4f  * hardware.intakeMotor.getMotorType().getAchieveableMaxTicksPerSecond());
            else if (gamepad1.dpad_down)
                hardware.intakeMotor.setVelocity(-1f);
            else
                hardware.intakeMotor.setVelocity(0f);


            if(gamepad1.right_trigger_pressed) //Midde Intake
                hardware.intakeStage2.setVelocity(0.6f  * hardware.intakeStage2.getMotorType().getAchieveableMaxTicksPerSecond());
            else
                hardware.intakeStage2.setVelocity(0f);

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

        OmniDriveController.DriveInput input = new OmniDriveController.DriveInput(
                gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                -(gamepad1.right_stick_x),
                speedCoefficient
        );

        driveController.moveRobot(input);

    }
}

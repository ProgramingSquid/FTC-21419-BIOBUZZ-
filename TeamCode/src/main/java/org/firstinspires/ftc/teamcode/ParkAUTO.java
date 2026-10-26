package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Parking Auto", group="Robot")
public class ParkAUTO extends LinearOpMode {
    private final ElapsedTime runtime = new ElapsedTime();
    private OmniDriveController driveController;

    @Override
    public void runOpMode() throws InterruptedException {
        RobotHardware hardware = new RobotHardware(hardwareMap);
        driveController = new OmniDriveController(hardware);

        // Wait for driver to press start
        telemetry.addLine("Robot Ready.");
        telemetry.update();

        waitForStart();
        runtime.reset();


        while (opModeIsActive())
        {
            if(runtime.seconds() < 0.6f)
                driveController.moveRobot(0.6f, 0,0);
            else
                break;
        }

        telemetry.addLine("Robot Is Now Parked.");
        telemetry.update();
        
    }
}

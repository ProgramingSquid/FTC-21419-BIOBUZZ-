package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

/** Looks up and configures the hardware used by the robot. */
public final class RobotHardware {
    public final DcMotorEx leftFrontMotor;
    public final DcMotorEx leftBackMotor;
    public final DcMotorEx rightFrontMotor;
    public final DcMotorEx rightBackMotor;
    public final DcMotorEx shootLeftMotor;
    public final DcMotorEx shootRightMotor;

    public RobotHardware(HardwareMap hardwareMap) {
        MotorDefinition[] driveMotors = RobotUtility.DEFAULT_DRIVE_MOTOR_DEFINITIONS;
        MotorDefinition[] shootingMotors = RobotUtility.DEFAULT_SHOOT_MOTOR_DEFINITIONS;

        leftFrontMotor = driveMotors[0].configure(hardwareMap);
        leftBackMotor = driveMotors[1].configure(hardwareMap);
        rightFrontMotor = driveMotors[2].configure(hardwareMap);
        rightBackMotor = driveMotors[3].configure(hardwareMap);
        shootLeftMotor = shootingMotors[0].configure(hardwareMap);
        shootRightMotor = shootingMotors[1].configure(hardwareMap);
    }
}
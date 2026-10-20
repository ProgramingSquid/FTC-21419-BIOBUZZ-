package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;
import com.qualcomm.robotcore.robot.Robot;

/** Looks up and configures the hardware used by the robot. */
public final class RobotHardware {
    public final DcMotorEx leftFrontMotor;
    public final DcMotorEx leftBackMotor;
    public final DcMotorEx rightFrontMotor;
    public final DcMotorEx rightBackMotor;
    public final DcMotorEx intakeMotor;
    public final DcMotorEx  shootMotor;

    public final DcMotorEx intakeStage2;

    public RobotHardware(HardwareMap hardwareMap) {

        //.configure() actually converts MotorDefinitions which just contain settings and parameters
        //for motors into actual DcMotorEx hardware objects which can be used in code to tell the hardware when
        //and how fast to run the motors.

        leftFrontMotor  = RobotUtility.FRONT_LEFT_MOTOR.configure(hardwareMap);
        leftBackMotor   = RobotUtility.BACK_LEFT_MOTOR.configure(hardwareMap);
        rightFrontMotor = RobotUtility.FRONT_RIGHT_MOTOR.configure(hardwareMap);
        rightBackMotor  = RobotUtility.BACK_RIGHT_MOTOR.configure(hardwareMap);

        intakeMotor = RobotUtility.INTAKE_MOTOR.configure(hardwareMap);
        intakeStage2 = RobotUtility.INTAKE_MOTOR2.configure(hardwareMap);

        shootMotor = RobotUtility.SHOOT_MOTOR.configure(hardwareMap);

        //Again because servos are much simpler we can just do hardware acquisition and setup here...
        //(This is what .configure looks like but for servos)

    }
}
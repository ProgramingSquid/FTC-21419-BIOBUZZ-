package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class RobotUtility {
// TELEOP Drive Settings______________________________________________________________________________
    public static final float DEFAULT_SPEED_COEF = 0.5f;
    public static final float SLOW_SPEED_COEF = 0.25f;
    public static final float FAST_SPEED_COEF = 1.0f;

//DRIVE MOTORS ____________________________________________________________________________________________
    public static final MotorDefinition FRONT_LEFT_MOTOR = new MotorDefinition(
            "front_left_drive",
            DcMotor.Direction.FORWARD,
            true
    );
    public static final MotorDefinition BACK_LEFT_MOTOR = new MotorDefinition(
            "back_left_drive",
            DcMotor.Direction.FORWARD,
            true
    );
    public static final MotorDefinition FRONT_RIGHT_MOTOR = new MotorDefinition(
            "front_right_drive",
            DcMotor.Direction.FORWARD,
            true
    );
    public static final MotorDefinition BACK_RIGHT_MOTOR = new MotorDefinition(
            "back_right_drive",
            DcMotor.Direction.FORWARD,
            true
    );
//Shoot Hardware_____________________________________________________________
    public static final MotorDefinition INTAKE_MOTOR = new MotorDefinition(
            "intake",
            DcMotor.Direction.FORWARD,
            false
    );
    public static final MotorDefinition INTAKE_MOTOR2 = new MotorDefinition(
            "intake-2",
            DcMotor.Direction.FORWARD,
            false
    );
    public static final MotorDefinition SHOOT_MOTOR = new MotorDefinition(
            "shoot",
            DcMotor.Direction.FORWARD,
            false
    );
//AUTO Settings _____________________________________________________________________________________________________
    public static final String DEFAULT_WEBCAM_NAME = "Webcam 1";
    /*    public static final int EXPOSURE = 6; //Uncomment when we create AUTO
    public static final int CAM_GAIN = 100;*/


    //Auto-drive to April tag settings __________________________________________________________
    public static final double DEFAULT_DESIRED_DISTANCE = 12.0 * 4.2; //in inches (4.2ft)

    public static final double SPEED_GAIN = 0.6 / 25; // Ramp up to ___% power at a 25 inch error.   (0.___ / 25.0)
    public static final double STRAFE_GAIN = 0.5 / 25.0;
    public static final double TURN_GAIN = 0.5 / 25.0;
    public static final double DESTINATION_ERROR_BUFFER = 2; //Auto drive is consider complete when distance/drive error <= to this

    public static final double MAX_AUTO_SPEED = 1;
    public static final double MAX_AUTO_STRAFE = 1;
    public static final double MAX_AUTO_TURN = 1;
//______________________________________________________________________________
}

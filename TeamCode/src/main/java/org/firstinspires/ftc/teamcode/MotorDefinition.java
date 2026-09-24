package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public final class MotorDefinition {
    private final String hardwareName;
    private final DcMotorEx.Direction direction;
    private final boolean useEncoder;

    public DcMotorEx configure(HardwareMap hardwareMap) {
        DcMotorEx hardware = hardwareMap.get(DcMotorEx.class, hardwareName);
        hardware.setDirection(direction);

        if (useEncoder) {
            hardware.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        }

        return hardware;
    }

    public MotorDefinition(String hardwareName, DcMotorEx.Direction direction, boolean useEncoder) {
        this.hardwareName = hardwareName;
        this.direction = direction;
        this.useEncoder = useEncoder;
    }
}
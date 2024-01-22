package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Distance  extends LinearOpMode {
    private DistanceSensor sensorDistance;
    @Override

    public void runOpMode() {
        sensorDistance = hardwareMap.get(DistanceSensor.class, "sensor_distance");

        waitForStart();
        resetRuntime();

        while (opModeIsActive()) {
            telemetry.addData("range", String.format("%.01f cm", sensorDistance.getDistance(DistanceUnit.CM)));
        }
    }
}

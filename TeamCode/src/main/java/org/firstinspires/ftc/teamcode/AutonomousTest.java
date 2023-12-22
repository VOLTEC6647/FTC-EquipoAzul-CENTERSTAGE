package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class AutonomousTest extends LinearOpMode {

    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor intakeMotor;
    private Blinker control_hub;
    private Servo rightArm;
    private Servo leftArm;

    @Override

    public void runOpMode() throws InterruptedException     {

        backLeftMotor = hardwareMap.get(DcMotor.class, "BackLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "BackRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "FrontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "FrontRightMotor");
        //intakeMotor = hardwareMap.get(DcMotor.class, "IntakeMotor");
        rightArm = hardwareMap.get(Servo.class, "RightArm");
        leftArm = hardwareMap.get(Servo.class, "LeftArm");

        double x;
        double y;
        double r;
        double theta;
        double power;
        double sin;
        double cos;
        double max;
        boolean buttonA;
        int intakePower;
        double speed;
        int valorA = 0;
        double buttonRT;
        double buttonLT;
        boolean rightBumper;

        waitForStart();
        resetRuntime();

        while (opModeIsActive()) {

            x = this.gamepad1.left_stick_x;
            y = this.gamepad1.left_stick_y;
            r = this.gamepad1.right_stick_x;
            theta = Math.atan2(y, x);
            power = Math.hypot(x, y);
            sin = Math.sin(theta - Math.PI / 4);
            cos = Math.cos(theta - Math.PI / 4);
            max = Math.max(Math.abs(sin), Math.abs(cos));
            buttonA = this.gamepad1.a;
            intakePower = 0;


        }
    }


}

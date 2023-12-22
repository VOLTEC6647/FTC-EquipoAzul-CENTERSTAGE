package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.arcrobotics.ftclib.geometry.Pose2d;
import com.arcrobotics.ftclib.geometry.Rotation2d;
import com.arcrobotics.ftclib.geometry.Translation2d;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveKinematics;
import com.arcrobotics.ftclib.kinematics.wpilibkinematics.MecanumDriveOdometry;

import android.util.Log;
import com.qualcomm.hardware.kauailabs.NavxMicroNavigationSensor;
import com.kauailabs.navx.ftc.AHRS;
import com.kauailabs.navx.ftc.navXPIDController;
@TeleOp

public class ControlTest extends LinearOpMode {
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor intakeMotor;
    private Blinker control_hub;
    private Servo rightArm;
    private Servo leftArm;
    //private CRServo crtest;
    private AHRS navx_device;
    private navXPIDController yawPIDController;
    TouchSensor touch;
    ColorSensor color;

    @Override

    public void runOpMode() {
        backLeftMotor = hardwareMap.get(DcMotor.class, "BackLeftMotor");
        backRightMotor = hardwareMap.get(DcMotor.class, "BackRightMotor");
        frontLeftMotor = hardwareMap.get(DcMotor.class, "FrontLeftMotor");
        frontRightMotor = hardwareMap.get(DcMotor.class, "FrontRightMotor");
        //intakeMotor = hardwareMap.get(DcMotor.class, "IntakeMotor");
        rightArm = hardwareMap.get(Servo.class, "RightArm");
        leftArm = hardwareMap.get(Servo.class, "LeftArm");
        touch = hardwareMap.get(TouchSensor.class, "TouchSensor");
        color = hardwareMap.get(ColorSensor.class, "ColorSensor");

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
        String pressed;
        double runtime;

        waitForStart();
        resetRuntime();

        while (opModeIsActive()) {
            x = this.gamepad1.left_stick_x;
            y = this.gamepad1.left_stick_y;
            r = this.gamepad1.right_stick_x;
            theta = Math.atan2(r, x);
            power = Math.hypot(x, r);
            sin = Math.sin(theta - Math.PI / 4);
            cos = Math.cos(theta - Math.PI / 4);
            max = Math.max(Math.abs(sin), Math.abs(cos));
            buttonA = this.gamepad1.a;
            intakePower = 0;
            buttonRT = this.gamepad1.right_trigger;
            buttonLT = this.gamepad1.left_trigger;
            rightBumper = this.gamepad1.right_bumper;
            runtime = getRuntime();


            double frontLeftPower;
            double backLeftPower;
            double frontRightPower;
            double backRightPower;

            frontLeftMotor.setPower(y);
            frontRightMotor.setPower(y);
            backLeftMotor.setPower(-y);
            backRightMotor.setPower(y);


        }

    }
}
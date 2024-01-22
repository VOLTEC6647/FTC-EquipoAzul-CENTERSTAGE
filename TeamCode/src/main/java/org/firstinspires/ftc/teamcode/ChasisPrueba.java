package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp

public class ChasisPrueba extends LinearOpMode {
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor intakeMotor;
    private Blinker control_hub;
    private Servo rightArm;
    private Servo leftArm;
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
            buttonRT = this.gamepad1.right_trigger;
            buttonLT = this.gamepad1.left_trigger;
            rightBumper = this.gamepad1.right_bumper;

            if(x <= 0.1) {
                x = 0;
            } else if(y <= 0.1) {
                y = 0;
            } else if (r <= 0.1) {
                r = 0;
            }

            //For this type of servo just give the input of
            //a desired position such as line 1, 2, or 3 in the backdrop

            if (buttonRT == 1) {
                rightArm.setPosition(1);
                leftArm.setPosition(1);
            }
            if (buttonLT == 1) {
                rightArm.setPosition(0);
                leftArm.setPosition(0);
            }

            if (buttonA) {
                valorA++;
                sleep(300);
            }
            if (valorA > 1) {
                valorA = 0;
            }

            intakePower = valorA;

            if (rightBumper) {
                speed = 0.5;
            } else {
                speed = 1;
            }

            double frontLeftPower;
            double frontRightPower;
            double backLeftPower;
            double backRightPower;



            frontLeftPower = power * cos / max + r;
            frontRightPower = power * sin / max - r;
            backLeftPower = power * sin / max + r;
            backRightPower = power * cos / max - r;


            if ((power + Math.abs(r)) > 1) {
                frontLeftPower /= power + r;
                frontRightPower /= power + r;
                backRightPower /= power + r;
                backLeftPower /= power + r;
            }


            frontLeftMotor.setPower(frontLeftPower * speed);
            frontRightMotor.setPower(frontRightPower * speed);
            backLeftMotor.setPower(backLeftPower * speed);
            backRightMotor.setPower(backRightPower * speed);
            //intakeMotor.setPower(intakePower);

            telemetry.addData("Status", "Initialized");
            telemetry.addData("Status", "Run Time: " + getRuntime());
            /*telemetry.addData("Pressed", pressed);
            telemetry.addData("Red", color.red());
            telemetry.addData("Blue", color.blue());
            telemetry.addData("Green", color.green());*/
            telemetry.update();
        }

    }
}
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
//import com.qualcomm.robotcore.
//import com.qualcomm.robotcore.hardware.IMU;


import org.firstinspires.ftc.teamcode.subsystems.ChassisSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.ElevatorSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.GyroscopeSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.PivotSubsystem;

@TeleOp
public class TeleOp_23481 extends LinearOpMode {
    private Blinker control_Hub;
    private double speed=1;
//    private IMU imu;
    public double baseSpeed=1;
//    private Orientation orientation;
    public double gyr;

    public ChassisSubsystem chassis;

    public static Gamepad controller1;
    public static Gamepad controller2;



    void ChassisMethods(ChassisSubsystem chassis, GyroscopeSubsystem gyroscope){
        //IMUMethods();
        gyr = gyroscope.getRotation();
        //gyr=0;
        telemetry.addData("gyr",gyr);
        if(this.gamepad1.back){
            if(this.gamepad1.a){
                gyroscope.reset();
                chassis.updateTargetAngle();
            }

        }
        if(this.gamepad1.y){
            chassis.FrontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            chassis.FrontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            chassis.BackLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            chassis.BackRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        }else{
            chassis.FrontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            chassis.FrontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            chassis.BackLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            chassis.BackRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
        if(this.gamepad1.start){
            if (this.gamepad1.dpad_up){
                gyroscope.setOffset(180);
                chassis.updateTargetAngle();
            }
            if (this.gamepad1.dpad_right){
                gyroscope.setOffset(270);
                chassis.updateTargetAngle();
            }
            if (this.gamepad1.dpad_down){
                gyroscope.setOffset(0);
                chassis.updateTargetAngle();
            }
            if (this.gamepad1.dpad_left){
                gyroscope.setOffset(90);
                chassis.updateTargetAngle();
            }
        }
        chassis.arcadeDrive(this.gamepad1.left_stick_x,-this.gamepad1.left_stick_y,this.gamepad1.right_stick_x,speed,gyr);

        if(this.gamepad1.right_trigger>0.1){
            speed=baseSpeed*(1.4-this.gamepad1.right_trigger);
        }else{
            speed=baseSpeed;
        }



    }

    void ElevatorMethods(ElevatorSubsystem elevator){


        elevator.getPosition(telemetry);
        if(!controller2.start) {

            if(Math.abs(controller2.right_stick_y)>0.3) {
                elevator.DebugSpeed = Math.abs(controller2.right_stick_y);
                if (controller2.right_stick_y > 0.3) {
                    elevator.goUp();
                } else if (controller2.right_stick_y < -0.3) {
                    elevator.goDown();
                }
            }else {
                elevator.stop();
            }
        }
    }

    void PivotMethods (PivotSubsystem pivot) {
        if (this.gamepad1.a) {
            pivot.open();
        } else if (this.gamepad1.b) {
            pivot.close();
        }

        if (this.gamepad1.right_bumper) {
            pivot.up();
        } else if (this.gamepad1.left_bumper) {
            pivot.down();
        }
        pivot.printPositions();

    }


    @Override
    public void runOpMode() {
        //this.telemetry = telemetry;
        control_Hub = hardwareMap.get(Blinker.class, "Control Hub");
        //imu = hardwareMap.get(IMU.class, "imu");



        telemetry.addData("Status", "Initialized");
        /////////////////////////////
        ChassisSubsystem chassis=ChassisSubsystem.getInstance(hardwareMap,telemetry);
        PivotSubsystem pivot = PivotSubsystem.getInstance(hardwareMap, telemetry);
        ElevatorSubsystem elevator = ElevatorSubsystem.getInstance(hardwareMap);
        //Odometry odometry = Odometry.getInstance(hardwareMap,chassis);
        GyroscopeSubsystem gyroscope = GyroscopeSubsystem.getInstance(hardwareMap);
        //orientation=imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        //GyroscopeSubsystem gyroscope=GyroscopeSubsystem.getInstance();

        //telemetry.addData("GyroX", orientation.secondAngle);
        //telemetry.addData("GyroY", AndroidOrientation().getAngle().secondPosition());
        ////telemetry.update();

        waitForStart();

        //arm.setZero();

        while(opModeIsActive()) {

            ChassisMethods(chassis,gyroscope);
            PivotMethods(pivot);
            ElevatorMethods(elevator);

            telemetry.update();



        }
        /*
        odometry.update();
        telemetry.addData("X", odometry.position().x);

        if(hasTarget){
            odometry.moveToCoords(2000,3000);
navigation
        }
        */

    }



}

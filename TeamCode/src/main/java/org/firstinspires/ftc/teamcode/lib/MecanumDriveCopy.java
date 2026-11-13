package org.firstinspires.ftc.teamcode.lib;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDriveCopy {
    public IMU imu;
    public DcMotorEx leftFront, leftBack, rightFront, rightBack, taco;
    public Servo tacopt2;
    public void init(HardwareMap hwMap) {

        //create motorex variables
        leftFront = hwMap.get(DcMotorEx.class, "leftFront");
        leftBack = hwMap.get(DcMotorEx .class, "leftBack");
        rightFront = hwMap.get(DcMotorEx.class, "rightFront");
        rightBack = hwMap.get(DcMotorEx.class, "rightBack");

        taco = hwMap.get(DcMotorEx.class, "random morter");
        tacopt2 = hwMap.get(Servo.class, "tacopt2");

        //set reverse if needed
        leftBack.setDirection(DcMotorEx.Direction.FORWARD);
        leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        rightBack.setDirection(DcMotorEx.Direction.FORWARD);
        rightFront.setDirection(DcMotorEx.Direction.FORWARD);
        taco.setDirection(DcMotorEx.Direction.FORWARD);

        //create imu
        imu = hwMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

        imu.initialize(new IMU.Parameters(RevOrientation));

    }
    public void caca(double rotate, double power){
        double tacopoo = power;
        double tacopt2poo = rotate;

        taco.setPower(tacopoo);
        tacopt2.setPosition(tacopt2poo);



    }
    public void drive(double forward, double strafe, double rotate) {

        // creates values for power
        double frontLeftPower = forward + strafe + rotate;
        double frontRightPower = forward - strafe - rotate;
        double backLeftPower = forward - strafe + rotate;
        double backRightPower = forward + strafe - rotate;

        // sets power
        leftBack.setPower(backLeftPower);
        leftFront.setPower(frontLeftPower);
        rightFront.setPower(frontRightPower);
        rightBack.setPower(backRightPower);

    }

    public void driveFieldRelative(double forward, double strafe, double rotate) {

        // angle
        double theta = Math.atan2(forward, strafe);

        // magnitude
        double r = Math.hypot(forward, strafe);

        theta = AngleUnit.normalizeRadians(theta - imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

        // force
        double newForward = r * Math.sin(theta);
        double newStrafe = r * Math.cos(theta);

        this.drive(newForward, newStrafe, rotate);

    }


}

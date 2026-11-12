package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class MecanumDrive {
    public IMU imu;
    public DcMotorEx leftFront, leftBack, rightFront, rightBack, Launcher, IntakeBack, IntakeFront;
    public void init(HardwareMap hwMap) {

        //create motorex variables
        leftFront = hwMap.get(DcMotorEx.class, "leftFront");
        leftBack = hwMap.get(DcMotorEx .class, "leftBack");
        rightFront = hwMap.get(DcMotorEx.class, "rightFront");
        rightBack = hwMap.get(DcMotorEx.class, "rightBack");

        Launcher = hwMap.get(DcMotorEx.class, "Launcher");

        IntakeBack = hwMap.get(DcMotorEx.class, "Intake");
        IntakeFront = hwMap.get(DcMotorEx.class, "Intake");

        //set reverse if needed
        leftBack.setDirection(DcMotorEx.Direction.FORWARD);
        leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        rightBack.setDirection(DcMotorEx.Direction.FORWARD);
        rightFront.setDirection(DcMotorEx.Direction.FORWARD);

        Launcher.setDirection(DcMotorEx.Direction.FORWARD);

        IntakeBack.setDirection(DcMotorEx.Direction.FORWARD);
        IntakeFront.setDirection(DcMotorEx.Direction.FORWARD);

        //create imu
        imu = hwMap.get(IMU.class, "imu");

        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);

        imu.initialize(new IMU.Parameters(RevOrientation));

    }

    public void LauncherPower(double power) {
        Launcher.setPower(power);
    }

    public void IntakePower(double power) {
        IntakeBack.setPower(power);
        IntakeFront.setPower(power);
    }

    public void WheelTest(String wheel, double Power) {

        switch(wheel) {
            case "LB":
                leftBack.setPower(Power);
                break;
            case "LF":
                leftFront.setPower(Power);
                break;
            case "RF":
                rightFront.setPower(Power);
                break;
            case "RB":
                rightBack.setPower(Power);
                break;
            case "LA":
                Launcher.setPower(Power);
                break;
            case "IN1":
                IntakeBack.setPower(Power);
                break;
            case "IN2":
                IntakeFront.setPower(Power);
                break;
            default:
                System.out.println("Errorrrrrrr");

        }

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

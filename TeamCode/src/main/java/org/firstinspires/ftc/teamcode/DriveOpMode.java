package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Drive", group = "TeleOp")
public class DriveOpMode extends OpMode {

    // makes new MacanumDrive Class
    MecanumDrive drive = new MecanumDrive();

    // Sets variables before execution
    double forward, strafe, rotate;

    @Override
    public void init() {
        // initializes motors
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {

        driveLoop();
        ShootingLoop();

    }

    public void driveLoop() {
        // sets controller input
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        // call driveFieldRelative class from MecanumDrive
        drive.driveFieldRelative(forward, strafe, rotate);

    }

    public void ShootingLoop() {

        if (gamepad2.aWasPressed()) {
            drive.LauncherPower(1);
            drive.IntakePower(1);
        }

        if (gamepad2.yWasPressed()) {
            drive.LauncherPower(0);
            drive.IntakePower(0);
        }

        if (gamepad2.xWasPressed()) {
            drive.LauncherPower(-1);
            drive.IntakePower(-1);
        }

    }
}
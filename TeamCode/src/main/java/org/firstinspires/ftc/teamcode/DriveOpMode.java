package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
public class DriveOpMode extends OpMode {

    MecanumDrive drive = new MecanumDrive();

    double forward, strafe, rotate;

    public void init() {
        drive.init(hardwareMap);
    }

    public void loop() {

        forward = gamepad1.left_stick_x;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        drive.driveFieldRelative(forward, strafe, rotate);

    }

}

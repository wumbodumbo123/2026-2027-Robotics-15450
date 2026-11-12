package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(group = "TeleOp", name = "Mecanum Wheel Direction")
public class MotorDirectionTester extends OpMode {

    MecanumDrive drive = new MecanumDrive();

    @Override
    public void init() {
        drive.init(hardwareMap);
    }

    @Override
    public void loop() {
        TestLoop();
    }

    public void TestLoop() {

        if (gamepad1.yWasPressed()) {
            drive.WheelTest("RF", 1);
        } else if (gamepad1.yWasReleased()) {
            drive.WheelTest("RF", 0);
        }

        if (gamepad1.xWasPressed()) {
            drive.WheelTest("RB", 1);
        } else if (gamepad1.xWasReleased()) {
            drive.WheelTest("RB", 0);
        }

        if (gamepad1.aWasPressed()) {
            drive.WheelTest("LF", 1);
        } else if (gamepad1.aWasReleased()) {
            drive.WheelTest("LF", 0);
        }

        if (gamepad1.bWasPressed()) {
            drive.WheelTest("LB", 1);
        } else if (gamepad1.bWasReleased()) {
            drive.WheelTest("LB", 0);
        }

        if (gamepad2.aWasPressed()) {
            drive.WheelTest("LA", 1);
        }   else if (gamepad2.aWasReleased()) {
            drive.WheelTest("LA", 0);
        }

        if (gamepad2.bWasPressed()) {
            drive.WheelTest("IN1", 1);
        } else if (gamepad2.bWasReleased()) {
            drive.WheelTest("IN1", 0);
        }

        if (gamepad2.xWasPressed()) {
            drive.WheelTest("IN2", 1);
        } else if (gamepad2.xWasReleased()) {
            drive.WheelTest("IN2", 0);
        }
    }
}

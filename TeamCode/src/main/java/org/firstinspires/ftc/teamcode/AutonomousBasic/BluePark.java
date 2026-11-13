package org.firstinspires.ftc.teamcode.AutonomousBasic;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.MecanumDrive;

@Autonomous(group = "Auto", name = "BluePark")
public class BluePark extends OpMode {

    MecanumDrive Drive = new MecanumDrive();

    private enum State {
        Drive1,
        Wait1,
        Stop1,
        Wait2,
        Drive2,
        Wait3,
        Stop2,
    }

    private State state = State.Drive1;

    private ElapsedTime time = new ElapsedTime();

    @Override
    public void init() {
        Drive.init(hardwareMap);
    }

    @Override
    public void start() {
        time.reset();
    }

    @Override
    public void loop() {

        AutoLoop();

    }

    public void AutoLoop() {

        switch (state) {
            case Drive1:
                Drive.drive(1,0,0);
                state = State.Wait1;
                ;;
            case Wait1:
                if (time.seconds() > 0.4) {
                    time.reset();
                    state = State.Stop1;
                }
                ;;
            case Stop1:
                Drive.drive(0,0,0);
                ;;
            case Wait2:
                if (time.seconds() > 1) {
                    time.reset();
                    state = State.Drive2;
                }
                ;;
            case Drive2:
                Drive.drive(-1,0,0);
                state = State.Wait3;
                ;;
            case Wait3:
                if (time.seconds() > 0.4) {
                    time.reset();
                    state = State.Stop2;
                }
                ;;
            case Stop2:
                Drive.drive(0,0,0);
                ;;
        }
    }
}
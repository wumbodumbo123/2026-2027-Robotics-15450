package org.firstinspires.ftc.teamcode.AutonomousBasic;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.lib.MecanumDrive;

@Autonomous(group = "Auto", name = "RedShoot")
public class RedShoot extends OpMode {

    MecanumDrive Drive = new MecanumDrive();

    private enum State {
        Drive1,
        Stop1,
        Wait1,
        Shoot,
        Wait2,
        Stop2,
        Wait3,
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
                Drive.drive(0, 1, 0);
                state = State.Wait1;
                ;;
            case Wait1:
                if (time.seconds() > 0.4) {
                    state = State.Stop1;
                    time.reset();
                }
                ;;
            case Stop1:
                Drive.drive(0,0,0);
                state = State.Wait2;
                ;;
            case Wait2:
                if (time.seconds() > 1) {
                    time.reset();
                    state = State.Shoot;
                }
                ;;
            case Shoot:
                Drive.LauncherPower(1);
                Drive.IntakePower(1);
                state = State.Wait3;
                ;;
            case Wait3:
                if (time.seconds() > 1) {
                    state = State.Stop2;
                    time.reset();
                }
                ;;
            case Stop2:
                    Drive.LauncherPower(0);
                    Drive.IntakePower(0);
                ;;
        }
    }
}

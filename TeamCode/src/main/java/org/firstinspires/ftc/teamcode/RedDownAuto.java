package org.firstinspires.ftc.teamcode;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(group = "Auto", name = "RedUpAuto")
public class RedDownAuto extends OpMode {

    private Follower follower;
    MecanumDrive drive = new MecanumDrive();
    private final PoseFactory p = PoseFactory.degrees();

    private final Pose startPose = p.of(9, 24, 90);
    private final Pose shootingPoseStart = p.of(60, 24, 90);
    private final Pose ShootingPoseControl1 = p.of(60, 54, 90);
    private final Pose ShootingPoseControl2 = p.of(8, 15, 90);
    private final Pose endPose = p.of(12, 90, 90);

    public void init() {


        follower = Constants.create(hardwareMap);
        drive.init(hardwareMap);

        follower.setPose(startPose);

        Command StartShoot = sequential(
                follow(follower, StartToShoot())
        );

        Command ShootPark = sequential(
                follow(follower, ShootToPark())
        );

        Command shoot = Command.build()
                .setStart(() -> {})
                .setExecute(() -> { drive.LauncherPower(1.0); drive.IntakePower(1.0); })
                .setDone(() -> { return true;})
                .setEnd(endCondition -> {});

        Scheduler.schedule(StartShoot);
        Scheduler.schedule(shoot);
        Scheduler.schedule(ShootPark);

    }

    public void loop() {

        Scheduler.execute();

    }

    public Path StartToShoot() {
        return line(startPose, shootingPoseStart).linear(startPose, shootingPoseStart);
    }

    public Path ShootToPark() {
        return curve(shootingPoseStart, ShootingPoseControl1, ShootingPoseControl2, endPose).constant(endPose);
    }

}

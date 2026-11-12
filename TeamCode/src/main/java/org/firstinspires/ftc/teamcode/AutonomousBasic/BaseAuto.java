package org.firstinspires.ftc.teamcode.AutonomousBasic;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.pedropathing.ivy.Command;

import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.*;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.pedro.Constants;

// change name to something else.
@Autonomous(name="BaseAuto", group="Auto")
public class BaseAuto extends OpMode {
    private Follower follower;
    MecanumDrive drive = new MecanumDrive();
    private final PoseFactory p = PoseFactory.degrees();

    // Make new poses (Where you want the robot to go) here
    // heading is the degree angle in which you want the robot to face
    private final Pose startPose = p.of(1, 2, 3);
    private final Pose controlPose = p.of(1, 2, 3);
    private final Pose endPose = p.of(1, 2, 3);

    @Override
    public void init() {
        // Initializes The Robot for the autonomous
        follower = Constants.create(hardwareMap);
        drive.init(hardwareMap);

        follower.setPose(startPose);

        // makes a step in the process of following the autonomous.
        // Command to follow path
        Command OnetoTwo = sequential(
                follow(follower, one())
        );

        // makes a more complex command
        // used for movement of extra objects on robot
        Command claw = Command.build()
                // does something at the start of the command
                .setStart(() -> {})
                // continues doing the execute until goal is reached
                .setExecute(() -> { drive.LauncherPower(1.0); })
                // the goal
                .setDone(() -> { return true;})
                // the type of end it reached (Interrupted, Blocked, Conflict)
                .setEnd(endCondition -> {});

        // Schedules the command made

        Scheduler.schedule(claw);
        Scheduler.schedule(OnetoTwo);

    }

    @Override
    public void loop() {

        // Runs all commands scheduled
        Scheduler.execute();

    }

    // every Path from one point to the next should be a different function (private Path)
    // remember everything has to be named accurately. I used one and two as an example but the name
    // should explain what the path is doing while still being somewhat short
    // example: startToShoot or shootToPark

    // This makes a straight line
    private Path one() {
        // line is start point to end point linear is talking about the direction the robot is facing
        return line(startPose, endPose).linear(startPose, endPose);
    }

    // This makes a curve
    private Path two() {
        // curve path requires a start as the first pose an ending as the last pose and the poses in the middle control how to curve is shapped
        // linear does the same thing as the line linear. It decides the heading
        return curve(startPose, controlPose, endPose).linear(startPose, endPose);
    }

}

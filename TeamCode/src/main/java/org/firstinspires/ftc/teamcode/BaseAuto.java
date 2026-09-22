package org.firstinspires.ftc.teamcode;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

// change name to something else.
@Autonomous(name="BaseAuto", group="Auto")
public class BaseAuto extends OpMode {

    private Follower follower;
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
        follower.setPose(startPose);

    }

    @Override
    public void loop() {

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

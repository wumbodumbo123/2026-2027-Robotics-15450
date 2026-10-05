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
public class IvanAuto extends OpMode {

    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    // Make new poses (Where you want the robot to go) here
    // heading is the degree angle in which you want the robot to face
    private final Pose startleavehub = p.of(8.149214659685862,102.27032886125656,0);
    private final Pose endleavehub = p.of(23.706806282722493,101.89991001308903,0);
    private final Pose startlongdown = p.of(23.706806282722493,101.89991001308903,0);
    private final Pose endlongdown = p.of(23.878272251308903,23.619764397905712,0);
    private final Pose startfronthive = p.of(23.878272251308903,23.619764397905712,0);
    private final Pose endfronthive = p.of(58.775523560209436,23.477094240837715,0);
    private final Pose startshoot = p.of(58.775523560209436,23.477094240837715,90);
    private final Pose endshoot = p.of(58.775523560209415,30.17604712041889,90);
    private final Pose startawayhive = p.of(58.775523560209415,30.17604712041889,-180);
    private final Pose endawayhive = p.of(32.77028795811519,29.99149214659685,-180);
    private final Pose startbacktohub = p.of(32.77028795811519,29.99149214659685,109);
    private final Pose endbacktohub = p.of(11.4011780104712,92.59751308900522,109);






    @Override
    public void init() {
        // Initializes The Robot for the autonomous
        follower = Constants.create(hardwareMap);
        follower.setPose(startleavehub);

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
        return line(startleavehub, endleavehub).constant(startleavehub);

    }
    private Path two(){
        return line(startlongdown, endlongdown).linear(startlongdown, endlongdown);
    }
    private Path three(){
        return line(startfronthive, endfronthive).linear(startfronthive, endfronthive);
    }
    private Path four(){
        return line(startshoot, endshoot).tangent();
    }
    private Path five(){
        return line(startawayhive, endawayhive).tangent();
    }
    private Path six(){
        return line(startbacktohub, endbacktohub).tangent();
    }
}

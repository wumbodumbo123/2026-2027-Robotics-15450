package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLResultTypes.ColorResult;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import static com.pedropathing.api.Paths.*;

import androidx.annotation.ColorRes;

import com.pedropathing.paths.Path;
import java.util.List;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name="RedUpAuto", group = "Auto")
public class RedUpAuto extends OpMode {

    private Limelight3A limelight;

    private Follower follower;
    private final PoseFactory p = PoseFactory.degrees();

    //poses

    private final Pose startPose = p.of(1,2,3);
    private final Pose endPose = p.of(1,2,3);

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(10);
        limelight.start();

        // sets april tag vs color oriented
        limelight.pipelineSwitch(0);

        follower = Constants.create(hardwareMap);
        follower.setPose(startPose);
    }

    @Override
    public void loop() {

        // Aprill Tags
        LLResult result = limelight.getLatestResult();

        // Aprill Tag results
        if (result != null && result.isValid()) {
            double Tx = result.getTx(); // how far left or right
            double Ty = result.getTy(); // how far up or down
            double Ta = result.getTa(); // how big the image is (0 - 100 %)

            telemetry.addData("Target X", Tx);
            telemetry.addData("Target Y", Ty);
            telemetry.addData("Target Area", Ta);
        } else {
            telemetry.addLine("Not Active");
        }

        // color
        List<ColorResult> color = result.getColorResults();

        // Color results
        for (ColorResult colorTarget : color) {
            double x = colorTarget.getTargetXDegrees();
            double y = colorTarget.getTargetYDegrees();
            double area = colorTarget.getTargetArea();
            telemetry.addData("color target", "takes up " + area + "% of the image");
        }
    }

}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.geometry;

import static edu.wpi.first.units.Units.Meters;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.wpi.first.math.geometry.Rotation2d;
//import edu.wpi.first.math.geometry.proto.Pose2dProto;
//import edu.wpi.first.math.geometry.struct.Pose2dStruct;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.interpolation.Interpolatable;
import edu.wpi.first.units.Distance;
import edu.wpi.first.units.Measure;
import edu.wpi.first.util.protobuf.ProtobufSerializable;
import edu.wpi.first.util.struct.StructSerializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** Represents a 2D Rectangular Pose Zone*/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE)
public class RectangularPoseZone2d {
    private final Translation2d m_toplefttranslation;
    private final Translation2d m_bottomrighttranslation;
    private final Translation2d m_looktranslation;

    public RectangularPoseZone2d(){
        m_toplefttranslation = new Translation2d();
        m_bottomrighttranslation = new Translation2d();
        m_looktranslation = new Translation2d();
    }

    /**
   * Constructs a Rectangular Pose Zone with the specified translations.
   *
   * @param topLeft The top left point of the rectangular zone
   * @param bottomRight The bottom right point of the rectangular zone
   * @param pointToLook The point the robot should look at while in the zone
   */
    @JsonCreator
    public RectangularPoseZone2d(
        @JsonProperty(required = true, value = "Top Left Translation") Translation2d topLeft,
        @JsonProperty(required = true, value = "Bottom Right Translation") Translation2d bottomRight,
        @JsonProperty(required = true, value = "Point to Look At") Translation2d pointToLook) {
        m_toplefttranslation = topLeft;
        m_bottomrighttranslation = bottomRight;
        m_looktranslation = pointToLook;
    }

    /**
     * Constructs a pose with x and y translations instead of a separate Translation2d.
     *
     * @param x The x component of the translational component of the pose.
     * @param y The y component of the translational component of the pose.
     * @param rotation The rotational component of the pose.
     */
    public RectangularPoseZone2d(double x1, double y1, double x2, double y2, double x3, double y3) {
        m_toplefttranslation = new Translation2d(x1, y1);
        m_bottomrighttranslation = new Translation2d(x1, y1);
        m_looktranslation = new Translation2d(x1, y1);
    }

    /**
     * Constructs a pose with x and y translations instead of a separate Translation2d. The X and Y
     * translations will be converted to and tracked as meters.
     *
     * @param x The x component of the translational component of the pose.
     * @param y The y component of the translational component of the pose.
     * @param rotation The rotational component of the pose.
     */
    public RectangularPoseZone2d(Measure<Distance> x1, Measure<Distance> y1, Measure<Distance> x2, Measure<Distance> y2, Measure<Distance> x3, Measure<Distance> y3) {
        this(x1.in(Meters), y1.in(Meters), x2.in(Meters), y2.in(Meters), x3.in(Meters), y3.in(Meters));
    }

    public boolean isPoseinZone(Pose2d Pose){
        if(m_toplefttranslation.getX() <= Pose.getX() && m_bottomrighttranslation.getX() >= Pose.getX()){
            if(m_toplefttranslation.getY() >= Pose.getY() && m_bottomrighttranslation.getY() <= Pose.getY()){
                return true;
            }
        }
        return false;
    }
}

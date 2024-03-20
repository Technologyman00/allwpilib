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
public class Rectangle2d {
    private final Translation2d m_center;
    private final double m_xWidth;
    private final double m_yWidth;
    private final Rotation2d m_rotation;

    public Rectangle2d(){
        m_center = new Translation2d();
        m_xWidth = 1.0;
        m_yWidth = 1.0;
        m_rotation = new Rotation2d();
    }

    /**
   * Constructs a Rectangular Pose Zone with the specified translations.
   *
   * @param center Center Translation of the Rectangle
   * @param xWidth The bottom right point of the rectangular zone
   * @param yWidth The point the robot should look at while in the zone
   * @param rotation The rectangles rotatation based on 
   */
    @JsonCreator
    public Rectangle2d(
        @JsonProperty(required = true, value = "Center") Translation2d center,
        @JsonProperty(required = true, value = "X Width") double xWidth,
        @JsonProperty(required = true, value = "Y Width") double yWidth,
        @JsonProperty(required = true, value = "Rotation") Rotation2d rotation) {
        m_center = center;
        m_xWidth = xWidth;
        m_yWidth = yWidth;
        m_rotation = rotation;
    }

    /**
     * Gets the Center of the Rectangle
     * @return location of the center of the Rectangle
     */
    public Translation2d getCenter(){
        return m_center;
    }

    /**
     * Gets the X Width of the Rectangle
     * @return X Width of the Rectangle
     */
    public double getXWidth(){
        return m_xWidth;
    }

    /**
     * Gets the Y Width of the Rectangle
     * @return Y Width of the Rectangle
     */
    public double getYWidth(){
        return m_yWidth;
    }    
    
    /**
     * Gets the Rotation of the Rectangle
     * @return rotation of the center of the Rectangle
     */
    public Rotation2d getRotation(){
        return m_rotation;
    }

    /**
     * Checks if a given Pose is within the zone
     * 
     * @param Pose The pose to check if within the zone
     * @return if the pose is in the zone
     */
    public boolean isPoseinZone(Pose2d Pose){
        Translation2d rotatedPoseLocation = Pose.getTranslation().rotateBy(m_rotation);
        double xPose = rotatedPoseLocation.getX();
        double yPose = rotatedPoseLocation.getY();

        if(m_center.getX()+(m_xWidth/2) >= xPose && m_center.getX()-(m_xWidth/2) <= xPose &&
           m_center.getY()+(m_yWidth/2) >= yPose && m_center.getY()-(m_yWidth/2) <= yPose)
        {
            return true;
        }
        return false;
    }


}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.geometry.struct;

import edu.wpi.first.math.geometry.Rectangle2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.util.struct.Struct;
import java.nio.ByteBuffer;

public class Rectangle2dStruct implements Struct<Rectangle2d> {
  @Override
  public Class<Rectangle2d> getTypeClass() {
    return Rectangle2d.class;
  }

  @Override
  public String getTypeString() {
    return "struct:Rectangle2d";
  } 

  @Override
  public int getSize() {
    return Translation2d.struct.getSize() + kSizeDouble + kSizeDouble + Rotation2d.struct.getSize();
  }

  @Override
  public String getSchema() {
    return "Translation2d center;double xWidth;double yWidth;Rotation2d rotation";
  }

  @Override
  public Struct<?>[] getNested() {
    return new Struct<?>[] {Translation2d.struct, Rotation2d.struct};
  }

  @Override
  public Rectangle2d unpack(ByteBuffer bb) {
    Translation2d center = Translation2d.struct.unpack(bb);
    double xWidth = bb.getDouble();
    double yWidth = bb.getDouble();
    Rotation2d rotation = Rotation2d.struct.unpack(bb);
    return new Rectangle2d(center, xWidth, yWidth, rotation);
  }

  @Override
  public void pack(ByteBuffer bb, Rectangle2d value) {
    Translation2d.proto.pack(bb, value.getTranslation());
    bb.putDouble(value.getXWidth());
    bb.putDouble(value.getYWidth());
    Rotation2d.proto.pack(bb, value.getRotation());
    
  }
}

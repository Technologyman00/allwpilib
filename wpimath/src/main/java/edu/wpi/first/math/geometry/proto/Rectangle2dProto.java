// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package edu.wpi.first.math.geometry.proto;

import edu.wpi.first.math.geometry.Rectangle2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.proto.Geometry2D.ProtobufRectangle2d;
import edu.wpi.first.util.protobuf.Protobuf;
import us.hebi.quickbuf.Descriptors.Descriptor;

public class Rectangle2dProto implements Protobuf<Rectangle2d, ProtobufRectangle2d> {
  @Override
  public Class<Rectangle2d> getTypeClass() {
    return Rectangle2d.class;
  }

  @Override
  public Descriptor getDescriptor() {
    return ProtobufRectangle2d.getDescriptor();
  }

  @Override
  public ProtobufRectangle2d createMessage() {
    return ProtobufRectangle2d.newInstance();
  }

  @Override
  public Rectangle2d unpack(ProtobufRectangle2d msg) {
    return new Rectangle2d(
      Translation2d.proto.unpack(msg.getCenter()),
      msg.getXWidth(),
      msg.getYWidth(),
      Rotation2d.proto.unpack(msg.getRotation()));
  }

  @Override
  public void pack(ProtobufRectangle2d msg, Rectangle2d value) {
    Translation2d.proto.pack(msg.getMutableTranslation(), value.getTranslation());
    msg.setXWidth(value.getXWidth());
    msg.setYWidth(value.getYWidth());
    Rotation2d.proto.pack(msg.getMutableRotation(), value.getRotation());
    
  }
}

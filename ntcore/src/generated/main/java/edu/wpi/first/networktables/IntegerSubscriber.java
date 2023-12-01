// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// THIS FILE WAS AUTO-GENERATED BY ./ntcore/generate_topics.py. DO NOT MODIFY

package edu.wpi.first.networktables;

import java.util.function.LongSupplier;

/** NetworkTables Integer subscriber. */
@SuppressWarnings("PMD.MissingOverride")
public interface IntegerSubscriber extends Subscriber, LongSupplier {
  /**
   * Get the corresponding topic.
   *
   * @return Topic
   */
  @Override
  IntegerTopic getTopic();

  /**
   * Get the last published value.
   * If no value has been published, returns the stored default value.
   *
   * @return value
   */
  long get();

  /**
   * Get the last published value.
   * If no value has been published, returns the passed defaultValue.
   *
   * @param defaultValue default value to return if no value has been published
   * @return value
   */
  long get(long defaultValue);

  @Override
  default long getAsLong() {
    return get();
  }

  /**
   * Get the last published value along with its timestamp
   * If no value has been published, returns the stored default value and a
   * timestamp of 0.
   *
   * @return timestamped value
   */
  TimestampedInteger getAtomic();

  /**
   * Get the last published value along with its timestamp
   * If no value has been published, returns the passed defaultValue and a
   * timestamp of 0.
   *
   * @param defaultValue default value to return if no value has been published
   * @return timestamped value
   */
  TimestampedInteger getAtomic(long defaultValue);

  /**
   * Get an array of all value changes since the last call to readQueue.
   * Also provides a timestamp for each value.
   *
   * <p>The "poll storage" subscribe option can be used to set the queue
   * depth.
   *
   * @return Array of timestamped values; empty array if no new changes have
   *     been published since the previous call.
   */
  TimestampedInteger[] readQueue();

  /**
   * Get an array of all value changes since the last call to readQueue.
   *
   * <p>The "poll storage" subscribe option can be used to set the queue
   * depth.
   *
   * @return Array of values; empty array if no new changes have been
   *     published since the previous call.
   */
  long[] readQueueValues();
}

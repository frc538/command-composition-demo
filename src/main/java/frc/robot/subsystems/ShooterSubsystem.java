// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSubsystem extends SubsystemBase {
  private final PWMSparkMax top;
  private final PWMSparkMax bottom;

  /** Creates a new ShooterSubsystem. */
  public ShooterSubsystem() {
    top = new PWMSparkMax(0);
    bottom = new PWMSparkMax(1);
  }

  public void stop() {
    stopShoot();
    stopFeed();
  }

  public void stopShoot() {
    top.set(0);
  }

  public void stopFeed() {
    bottom.set(0);
  }

  public void intake() {
    top.set(-0.1);
    bottom.set(-0.1);
  }

  public void shoot() {
    top.set(1);
  }

  public void feedShot() {
    shoot();
    bottom.set(0.5);
  }

  public void spinUp() {
    stopFeed();
    shoot();
  }

  public Command runSpinUpCommand() {
    return Commands.run(() -> spinUp(), this);
  }

  public Command spinUpCommandForTimeCommand(double seconds) {
    return runSpinUpCommand().withTimeout(seconds);
  }

  public Command runFeedShotCommand() {
    return Commands.run(() -> feedShot(), this);
  }

  public Command feedShotForTimeCommand(double seconds) {
    return runFeedShotCommand().withTimeout(seconds);
  }

  public Command runStopCommand() {
    return Commands.run(()-> stop(), this);
  }

  public Command shootCommand() {
    return spinUpCommandForTimeCommand(2)
      .andThen(feedShotForTimeCommand(3))
      .andThen(runStopCommand());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

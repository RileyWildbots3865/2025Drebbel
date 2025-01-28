// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

import frc.robot.Constants.Extras;

public class subBlinkin extends SubsystemBase {
  /** Creates a new subBlinkin. */
  private final Spark blinkin;

  public subBlinkin() {
    blinkin = new Spark(Extras.blinkinID);
  }

  public void confetti() {
    blinkin.set(-0.87);
  }

  public void red() {
    blinkin.set(0.61 );
  }

  public void blue() {
    blinkin.set(0.87);
  }

  public void random() {
      double randDouble = 2*Math.floor(100*Math.random()) - 99;
      blinkin.set((double) randDouble / 100);
  }
  
  public void rainbow() {
    blinkin.set(-.99);
  }

  public void riley() {
    blinkin.set(0.51 );
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

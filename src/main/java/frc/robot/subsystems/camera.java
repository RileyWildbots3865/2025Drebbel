// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class camera extends SubsystemBase {
  /** Creates a new camera. */
  public camera() {
    Thread cameraThread = new Thread(() -> {
            var camera = CameraServer.getVideo().getSource();
            camera.setFPS(15);
            camera.setResolution(320, 240);
    });
    cameraThread.run();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   // SmartDashboard.setNetworkTableInstance()
  }
}

package frc.robot.subsystems;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class camera extends SubsystemBase {

  public camera() {
    Thread cameraThread = new Thread(() -> {
            var camera = CameraServer.getVideo().getSource();
            camera.setFPS(15);
            camera.setResolution(320, 240);
    });
    cameraThread.run();
  }

  @Override
  public void periodic() {}
}

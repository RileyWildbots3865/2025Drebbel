// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.classes.LimelightHelpers;

public class subLimeLight extends SubsystemBase {
  public subLimeLight() {
  }

  public double getHorizontalError(){
    // Horizontal offset from crosshair to target in degrees
    return LimelightHelpers.getTX("limelight-right");
  }
  public double getDistanceError(){
    // Target area (0% to 100% of image)
    return LimelightHelpers.getTA("limelight-right");
  }
  public boolean hasTarget(){
    // Do you have a valid target?
    return LimelightHelpers.getTV("limelight-right");
  }

  @Override
  public void periodic() {
    System.out.println("subLimeLight periodic running...");
    // SmartDashboard.putBoolean("Limelight Has Target", LimelightHelpers.getTV("limelight-right"));
    // SmartDashboard.putNumber("Limelight Horizontal Error", getHorizontalError()-Constants.LimeLightOffsets.HorizontalOffset);
    // SmartDashboard.putNumber("Limelight Distance Error", getDistanceError()-Constants.LimeLightOffsets.DistanceOffset);
  }

  
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechConstants;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

public class subFunnel extends SubsystemBase {
  /** Creates a new subFunnel. */
  public SparkMax funnelMotor1;
  public SparkMax funnelMotor2;
  
  SparkMaxConfig funnelConfig = new SparkMaxConfig();

  public subFunnel() {
    funnelConfig.follow(funnelMotor1, true);

    funnelMotor1 = new SparkMax(MechConstants.funnel1CanId, MotorType.kBrushless);
    funnelMotor1.configure(null, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    funnelMotor2 = new SparkMax(MechConstants.funnel2CanId, MotorType.kBrushless);
    funnelMotor2.configure(funnelConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

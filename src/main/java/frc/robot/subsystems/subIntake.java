// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.Intake;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;

public class subIntake extends SubsystemBase {
  /** Creates a new subIntake. */
  public SparkMax intakeMotor1;
  public SparkMax intakeMotor2;

  SparkMaxConfig intakeConfig = new SparkMaxConfig();

  public subIntake() {
    intakeConfig.follow(intakeMotor1, true);

    intakeMotor1 = new SparkMax(Intake.intake1CanId, MotorType.kBrushless);
    intakeMotor1.configure(null, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    intakeMotor2 = new SparkMax(Intake.intake2CanId, MotorType.kBrushless);
    intakeMotor2.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

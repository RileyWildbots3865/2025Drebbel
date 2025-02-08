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

public class subElevator extends SubsystemBase {
  /** Creates a new subElevator. */
  public SparkMax elevatorMotor1;
  public SparkMax elevatorMotor2;

  public subElevator() {
    elevatorMotor1 = new SparkMax(MechConstants.elevator1CanId, MotorType.kBrushless);
    elevatorMotor1.configure(null, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    elevatorMotor2 = new SparkMax(MechConstants.elevator2CanId, MotorType.kBrushless);
    elevatorMotor2.configure(null, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

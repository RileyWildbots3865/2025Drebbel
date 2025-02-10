// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Elevator;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class subElevator extends SubsystemBase {
  /** Creates a new subElevator. */
  public SparkMax elevatorMotor1;
  public SparkMax elevatorMotor2;

  public RelativeEncoder ElevatorEncoder;

  private PIDController ElevatorPid = new PIDController(0.4, 0, 0);
  private double L4 = 100.0;
  private double L3 = 70.0;
  private double L2 = 40.0;
  private double L1 = 13.5;

  SparkMaxConfig elevator1Config = new SparkMaxConfig();
  SparkMaxConfig elevator2Config = new SparkMaxConfig();

  public subElevator() {
    elevator1Config.idleMode(IdleMode.kBrake);
    elevator2Config.follow(elevatorMotor1, true);
    elevator2Config.idleMode(IdleMode.kBrake);
    
    elevatorMotor1 = new SparkMax(Elevator.elevator1CanId, MotorType.kBrushless);
    elevatorMotor1.configure(elevator1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    elevatorMotor2 = new SparkMax(Elevator.elevator2CanId, MotorType.kBrushless);
    elevatorMotor2.configure(elevator2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    ElevatorEncoder = elevatorMotor1.getEncoder();

    ElevatorPid.setTolerance(5);
    ElevatorPid.setIntegratorRange(-1, 1);
  }

  public void goToPosL4() {
    elevatorMotor1.set(ElevatorPid.calculate(getEncoderValue(),L4));
  }

  public void goToPosL3() {
    elevatorMotor1.set(ElevatorPid.calculate(getEncoderValue(),L3));
  }

  public void goToPosL2() {
    elevatorMotor1.set(ElevatorPid.calculate(getEncoderValue(),L2));
  }
  
  public void goToPosL1() {
    elevatorMotor1.set(ElevatorPid.calculate(getEncoderValue(),L1));
  }

  public void goToPos(double L) {
    elevatorMotor1.set(ElevatorPid.calculate(getEncoderValue(),L));
  }

  public double getEncoderValue() {
    return ElevatorEncoder.getPosition();
  }

  public void ResetEncoder() {
    ElevatorEncoder.setPosition(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Elevator Height", getEncoderValue());
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.Elevator;
import frc.robot.commands.cmdElevator_Auto;

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

  private PIDController ElevatorPid = new PIDController(0.1,0,0);

  SparkMaxConfig elevator1Config = new SparkMaxConfig();
  SparkMaxConfig elevator2Config = new SparkMaxConfig();

  public double ElpidSetPoint;

  public subElevator() {
    elevatorMotor1 = new SparkMax(Elevator.elevator1CanId, MotorType.kBrushless);
    elevatorMotor2 = new SparkMax(Elevator.elevator2CanId, MotorType.kBrushless);

    ElevatorEncoder = elevatorMotor1.getEncoder();

    elevator1Config.idleMode(IdleMode.kBrake);
    elevator2Config.follow(elevatorMotor1, true);
    elevator2Config.idleMode(IdleMode.kBrake);
    elevatorMotor1.configure(elevator1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    elevatorMotor2.configure(elevator2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    ElevatorPid.setTolerance(0.01);
    ElevatorPid.setIntegratorRange(-1, 1);
    ResetEncoder();
    ElpidSetPoint = Constants.Elevator.L2;
    setDefaultCommand(new cmdElevator_Auto(this, Constants.Elevator.L1));
  }

  public void gotoElevatorPos() {
    ElevatorPid.setSetpoint(ElpidSetPoint);
    elevatorMotor1.set(MathUtil.clamp(ElevatorPid.calculate(getEncoderValue()), -0.3, 0.1));
  }

  public void TeleOp(double speed){
    elevatorMotor1.set(speed);
  }

  public double getEncoderValue() {
    return ElevatorEncoder.getPosition();
  }

  public void ResetEncoder() {
    ElevatorEncoder.setPosition(0);
  }

  public void stop() {
    elevatorMotor1.stopMotor();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Elevator Height", getEncoderValue());
  }
}

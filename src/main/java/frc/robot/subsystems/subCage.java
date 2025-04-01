package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Cage;

public class subCage extends SubsystemBase {
  
  public SparkMax cageMotor;
  public Servo servo = new Servo(0);

  SparkMaxConfig cageConfig = new SparkMaxConfig();

  public subCage() {
    cageMotor = new SparkMax(Cage.cageCanID, MotorType.kBrushless);
    cageConfig.openLoopRampRate(2);
    cageConfig.idleMode(IdleMode.kBrake);
    cageMotor.configure(cageConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

     

  }

  @Override
  public void periodic() {}
}

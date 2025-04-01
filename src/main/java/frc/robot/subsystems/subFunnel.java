package frc.robot.subsystems;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.Funnel;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class subFunnel extends SubsystemBase {

  public SparkMax funnelMotor1;
  public SparkMax funnelMotor2;
  
  SparkMaxConfig funnel1Config = new SparkMaxConfig();
  SparkMaxConfig funnel2Config = new SparkMaxConfig();

  public RelativeEncoder FunnelEncoder;

  private PIDController FunnelPid = new PIDController(.1, 0, 0);

  public double pidSetPoint;

  public subFunnel() {
    funnelMotor1 = new SparkMax(Funnel.funnel1CanId, MotorType.kBrushless);
    funnelMotor2 = new SparkMax(Funnel.funnel2CanId, MotorType.kBrushless);

    funnel1Config.idleMode(IdleMode.kBrake);
    funnel2Config.follow(funnelMotor1, true);
    funnel2Config.idleMode(IdleMode.kBrake);
    funnelMotor1.configure(funnel1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    funnelMotor2.configure(funnel2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    FunnelEncoder = funnelMotor1.getEncoder();
    FunnelPid.setTolerance(0.01);
    FunnelPid.setIntegratorRange(-1, 1);
    ResetEncoder();
    pidSetPoint = Constants.Funnel.LRest;
  }

  public void gotoFunnelPos() {
    FunnelPid.setSetpoint(pidSetPoint);
    funnelMotor1.set(MathUtil.clamp(FunnelPid.calculate(getEncoderValue()), -0.1, 0.1));
  }

  public void TeleOp(double speed){
    funnelMotor1.set(speed);
  }

  public double getEncoderValue() {
    return FunnelEncoder.getPosition();
  }

  public void ResetEncoder() {
    FunnelEncoder.setPosition(0);
  }

  public void stop() {
    funnelMotor1.stopMotor();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Funnel Value", getEncoderValue());
  }
}

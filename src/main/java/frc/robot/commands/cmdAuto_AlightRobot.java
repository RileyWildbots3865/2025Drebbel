package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.subLimeLight;
import frc.robot.subsystems.subSwerve;
//import swervelib.SwerveInputStream;

public class cmdAuto_AlightRobot extends Command {
  subSwerve swerve;
  subLimeLight limelight;
  PIDController pidHorizontalController = new PIDController(0.7, 0.0, 0.0);
  PIDController pidDistanceController = new PIDController(0.5, 0.0, 0.0);
  //SwerveInputStream driveAngularVelocity;

  public cmdAuto_AlightRobot(subSwerve swerve, subLimeLight lime) {
    this.swerve = swerve;
    this.limelight = lime;
    pidHorizontalController.setSetpoint(Constants.LimeLightOffsets.HorizontalOffset);
    pidDistanceController.setSetpoint(Constants.LimeLightOffsets.DistanceOffset);
    pidHorizontalController.setTolerance(2);
    pidDistanceController.setTolerance(1);
    addRequirements(swerve, limelight);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if(!limelight.hasTarget()){
      swerve.drive(0, 0, 0, true);    
      return;
    }
    swerve.drive(
      MathUtil.clamp(-pidDistanceController.calculate(limelight.getDistanceError()), -0.4, 0),
      MathUtil.clamp(-pidHorizontalController.calculate(limelight.getHorizontalError()), -0.5, 0.5), 
      0,true);     
    SmartDashboard.putBoolean("Limelight Hz OnPoint", pidHorizontalController.atSetpoint());
    SmartDashboard.putBoolean("Limelight Dt OnPoint", pidDistanceController.atSetpoint());
  }

  @Override
  public void end(boolean interrupted) {
    swerve.drive(new ChassisSpeeds(0, 0, 0).vxMetersPerSecond, new ChassisSpeeds(0, 0, 0).vyMetersPerSecond, new ChassisSpeeds(0, 0, 0).omegaRadiansPerSecond,true);    
  }

  @Override
  public boolean isFinished() {
    return pidHorizontalController.atSetpoint() && pidDistanceController.atSetpoint() ? true : false;
  }
}
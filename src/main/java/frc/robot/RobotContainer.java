// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.cmdElevator_TeleOp;
import frc.robot.commands.cmdFunnel_TeleOp;
import frc.robot.commands.cmdIntake_TeleOp;
import frc.robot.commands.cmdSwerve_TeleOp;
import frc.robot.subsystems.subElevator;
import frc.robot.subsystems.subFunnel;
import frc.robot.subsystems.subIntake;
import frc.robot.subsystems.subSwerve;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS5Controller;


public class RobotContainer {
  private final CommandPS5Controller driverOne = new CommandPS5Controller(OperatorConstants.DriverOne);
  private final CommandPS5Controller driverTwo = new CommandPS5Controller(OperatorConstants.DriverTwo);
  private final subSwerve swerve = new subSwerve();
  private final subIntake intake = new subIntake();
  private final subFunnel funnel = new subFunnel();
  private final subElevator elevator = new subElevator();

  public static boolean fieldCentric = false;

  SendableChooser<Command> chooser = new SendableChooser<>();
  public RobotContainer() {
    configureDriverOne();
    configureDriverTwo();
    addAutoOptions();
  }

  private void configureDriverOne() { // Binds for controller 1
    driverOne.cross().onTrue(new InstantCommand(() -> fieldCentric = !fieldCentric));
    swerve.setDefaultCommand(
      new cmdSwerve_TeleOp(
          swerve,
          () -> MathUtil.applyDeadband(driverOne.getLeftY(), 0.1),
          () -> MathUtil.applyDeadband(driverOne.getLeftX(), 0.1),
          () -> MathUtil.applyDeadband(driverOne.getRightX(), 0.01),
          () -> fieldCentric));

    driverOne.PS().onTrue(new InstantCommand(() -> swerve.zeroHeading()));
  }

  public void configureDriverTwo() { // Binds for controller 2
    driverTwo.povUp().whileTrue(new cmdElevator_TeleOp(elevator, true)); // Elevator = up, down
    driverTwo.povDown().whileTrue(new cmdElevator_TeleOp(elevator, false));

    driverTwo.triangle().whileTrue(new cmdIntake_TeleOp(intake, true)); // intake = triangle, circle
    driverTwo.circle().whileTrue(new cmdIntake_TeleOp(intake, false));

    driverTwo.square().whileTrue(new cmdFunnel_TeleOp(funnel, true));
    driverTwo.cross().whileTrue(new cmdFunnel_TeleOp(funnel, false));
  }

  private void addAutoOptions(){
    chooser.setDefaultOption("Do Nothing", new InstantCommand());
    SmartDashboard.putData("Auto Options", chooser);
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}

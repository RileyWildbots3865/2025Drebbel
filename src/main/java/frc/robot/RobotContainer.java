// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.cmdCage_TeleOp;
import frc.robot.commands.cmdElevator_Auto;
import frc.robot.commands.cmdElevator_TeleOp;
import frc.robot.commands.cmdFunnel_Auto;
import frc.robot.commands.cmdFunnel_TeleOp;
import frc.robot.commands.cmdIntake_TeleOp;
import frc.robot.commands.cmdSwerve_TeleOp;
import frc.robot.subsystems.subCage;
import frc.robot.subsystems.subElevator;
import frc.robot.subsystems.subFunnel;
import frc.robot.subsystems.subIntake;
import frc.robot.subsystems.subSwerve;
import edu.wpi.first.math.MathUtil;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.GenericHID.RumbleType;
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
  private final subCage cage = new subCage();

  //DigitalInput topLimitSwitch = new DigitalInput(0);
  //DigitalInput bottomLimitSwitch = new DigitalInput(1);

  public static boolean fieldCentric = false;

  SendableChooser<Command> chooser = new SendableChooser<>();
  public RobotContainer() {
    configureDriverOne();
    configureDriverTwo();
    configureSingleDriver();
    addAutoOptions();
  }
  private void configureSingleDriver(){
    swerve.setDefaultCommand(
      new cmdSwerve_TeleOp(
          swerve,
          () -> MathUtil.applyDeadband(driverOne.getLeftY(), 0.1),
          () -> MathUtil.applyDeadband(driverOne.getLeftX(), 0.1),
          () -> MathUtil.applyDeadband(driverOne.getRightX(), 0.01),
          () -> fieldCentric));

    driverOne.povUp().whileTrue(new cmdElevator_TeleOp(elevator, true)); 
    driverOne.povDown().whileTrue(new cmdElevator_TeleOp(elevator, false));

    driverOne.square().whileTrue(new cmdFunnel_TeleOp(funnel, true));
    driverOne.cross().whileTrue(new cmdFunnel_TeleOp(funnel, false));

    driverOne.R1().whileTrue(new cmdIntake_TeleOp(intake, true));
    driverOne.L1().whileTrue(new cmdIntake_TeleOp(intake, false));

    driverOne.triangle().whileTrue(new cmdCage_TeleOp(cage, true));
    driverOne.circle().whileTrue(new cmdCage_TeleOp(cage, false));

    driverOne.povLeft().whileTrue(new cmdFunnel_Auto(funnel, Constants.Funnel.LRest));
    driverOne.povRight().whileTrue(new cmdFunnel_Auto(funnel, Constants.Funnel.LUp));

    driverOne.L2().whileTrue(new cmdElevator_Auto(elevator, Constants.Elevator.L2));
    driverOne.R2().whileTrue(new cmdElevator_Auto(elevator, Constants.Elevator.L3));
  }

  private void configureDriverOne() { // Binds for controller 1
    driverOne.triangle().onTrue(new InstantCommand(() -> fieldCentric = !fieldCentric));
    swerve.setDefaultCommand(
      new cmdSwerve_TeleOp(
          swerve,
          () -> MathUtil.applyDeadband(driverOne.getLeftY(), 0.1),
          () -> MathUtil.applyDeadband(driverOne.getLeftX(), 0.1),
          () -> MathUtil.applyDeadband(driverOne.getRightX(), 0.01),
          () -> fieldCentric));

    driverOne.PS().onTrue(new InstantCommand(() -> swerve.zeroHeading()));

    //rumble ball
    /*if (topLimitSwitch.get()) {
      driverOne.setRumble(RumbleType.kBothRumble, 1);
    } else if (bottomLimitSwitch.get()) {
      driverOne.setRumble(RumbleType.kBothRumble, 1);
    }*/

    driverOne.R1().whileTrue(new cmdIntake_TeleOp(intake, true));
    driverOne.L1().whileTrue(new cmdIntake_TeleOp(intake, false));
  }

  public void configureDriverTwo() { // Binds for controller 2
    driverTwo.povUp().whileTrue(new cmdElevator_TeleOp(elevator, true));
    driverTwo.povDown().whileTrue(new cmdElevator_TeleOp(elevator, false));

    driverTwo.square().whileTrue(new cmdFunnel_TeleOp(funnel, true));
    driverTwo.cross().whileTrue(new cmdFunnel_TeleOp(funnel, false));

    driverTwo.triangle().whileTrue(new cmdCage_TeleOp(cage, true));
    driverTwo.circle().whileTrue(new cmdCage_TeleOp(cage, false));
  }

  private void addAutoOptions(){
    chooser.setDefaultOption("Do Nothing", new InstantCommand());
    SmartDashboard.putData("Auto Options", chooser);
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.cmdCage_TeleOp;
import frc.robot.commands.cmdElevator_Auto;
import frc.robot.commands.cmdElevator_TeleOp;
import frc.robot.commands.cmdFunnel_Auto;
import frc.robot.commands.cmdFunnel_TeleOp;
import frc.robot.commands.cmdIntake_Auto;
import frc.robot.commands.cmdIntake_TeleOp;
import frc.robot.commands.cmdSwerve_TeleOp;
import frc.robot.subsystems.subCage;
import frc.robot.subsystems.subElevator;
import frc.robot.subsystems.subFunnel;
import frc.robot.subsystems.subIntake;
import frc.robot.subsystems.subSwerve;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

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
  private final subCage cage = new subCage(); 



  public static boolean fieldCentric = false;

  SendableChooser<Command> chooser = new SendableChooser<>();
  public RobotContainer() {
    configureDriverOne();
    configureDriverTwo();
    swerve.setUpPathPlanner();
    commandConfigure();
    addAutoOptions();
  }

  private void configureDriverOne() { // Binds for controller 1
    driverOne.button(9).onTrue(new InstantCommand(() -> fieldCentric = !fieldCentric));
    swerve.setDefaultCommand(
      new cmdSwerve_TeleOp(
          swerve,
          () -> -MathUtil.applyDeadband(driverOne.getLeftY(), 0.1),
          () -> -MathUtil.applyDeadband(driverOne.getLeftX(), 0.1),
          () -> -MathUtil.applyDeadband(driverOne.getRightX(), 0.01),
          () -> fieldCentric));

    driverOne.PS().onTrue(new InstantCommand(() -> swerve.zeroHeading()));

    driverOne.R1().whileTrue(new cmdIntake_TeleOp(intake, true));
    driverOne.L1().whileTrue(new cmdIntake_TeleOp(intake, false));
  }

  public void configureDriverTwo() { // Binds for controller 2
    driverTwo.povUp().whileTrue(new cmdElevator_TeleOp(elevator, true)); 
    driverTwo.povDown().whileTrue(new cmdElevator_TeleOp(elevator, false));

    driverTwo.povLeft().whileTrue(new cmdFunnel_TeleOp(funnel, true));
    driverTwo.povRight().whileTrue(new cmdFunnel_TeleOp(funnel, false));

    driverTwo.L1().whileTrue(new cmdCage_TeleOp( cage, true ));
    driverTwo.R1().whileTrue(new cmdCage_TeleOp( cage, false));

    driverTwo.R2().whileTrue(new cmdFunnel_Auto(funnel, Constants.Funnel.LRest));
    driverTwo.L2().whileTrue(new cmdFunnel_Auto(funnel, Constants.Funnel.LUp));
    

    driverTwo.cross().whileTrue(new cmdElevator_Auto(elevator, Constants.Elevator.L1));
    driverTwo.square().whileTrue(new cmdElevator_Auto(elevator, Constants.Elevator.L2));
    driverTwo.triangle().whileTrue(new cmdElevator_Auto(elevator, Constants.Elevator.L3));
    driverTwo.circle().whileTrue(new cmdElevator_Auto(elevator, Constants.Elevator.L4));
  }

  public void commandConfigure() {
    NamedCommands.registerCommand("SpitCoral", new cmdIntake_Auto(intake).withTimeout(2));
    NamedCommands.registerCommand("Funnel Up", new cmdFunnel_TeleOp(funnel, fieldCentric));
    NamedCommands.registerCommand("ElevatorL2", new cmdElevator_Auto(elevator, Constants.Elevator.L2).withTimeout(2));
    NamedCommands.registerCommand("ElevatorL3", new cmdElevator_Auto(elevator, Constants.Elevator.L3).withTimeout(2));
    NamedCommands.registerCommand("ElevatorL4", new cmdElevator_Auto(elevator, Constants.Elevator.L4));
    
  }

  private void addAutoOptions(){
    chooser = AutoBuilder.buildAutoChooser(); 
    SmartDashboard.putData("Auto Options", chooser);
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
}

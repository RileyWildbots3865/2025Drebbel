package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.subIntake;


public class cmdIntake_Auto extends Command {

  subIntake intake;
  public cmdIntake_Auto(subIntake intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intake.intakeMotor1.set(Constants.Intake.kintakeSpeed);
  }

  @Override
  public void end(boolean interrupted) {
    intake.intakeMotor1.stopMotor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}

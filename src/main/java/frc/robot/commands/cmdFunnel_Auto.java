package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.subFunnel;

public class cmdFunnel_Auto extends Command {
  subFunnel funnel;
  double position;
  public cmdFunnel_Auto(subFunnel funnel, double position) {
    this.funnel = funnel;
    this.position = position;
    addRequirements(funnel);
  }

  @Override
  public void initialize() {
    funnel.pidSetPoint = position;
  }

  @Override
  public void execute() {
    funnel.gotoFunnelPos();
  }

  @Override
  public void end(boolean interrupted) {
    funnel.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}

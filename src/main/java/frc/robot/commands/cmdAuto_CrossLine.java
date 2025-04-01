package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.subSwerve;


public class cmdAuto_CrossLine extends Command {

  subSwerve swerve;
  double speed;
  Timer timer;
  public cmdAuto_CrossLine(subSwerve swerve, double speed) {

    this.swerve = swerve;
    this.speed = speed;
    addRequirements(swerve);
    timer = new Timer();
  }

  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  @Override
  public void execute() {
    if (timer.get() < 3) {
      swerve.drive(-speed, 0, 0, true);
    } else {
     swerve.drive(0, 0, 0, true); 
    }
  }

  @Override
  public void end(boolean interrupted) {
    swerve.stopModules();
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(3);
  }
}

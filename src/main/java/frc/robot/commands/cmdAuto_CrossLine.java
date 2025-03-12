// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.subSwerve;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class cmdAuto_CrossLine extends Command {
  /** Creates a new cmdAuto_CrossLine. */
  subSwerve swerve;
  double speed;
  Timer timer;
  public cmdAuto_CrossLine(subSwerve swerve, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.swerve = swerve;
    this.speed = speed;
    addRequirements(swerve);
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (timer.get() < 3) {
      swerve.drive(-speed, 0, 0, true);
    } else {
     swerve.drive(0, 0, 0, true); 
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    swerve.stopModules();
    timer.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(3);
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.subSwerve;
import frc.robot.commands.cmdAuto_AlightRobot;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class cmdAuto_Score extends Command {
  /** Creates a new cmdAuto_Score. */
  subSwerve swerve;
  double speed;
  Timer timer;
  private void addCommands(InstantCommand instantCommand, Command auto, InstantCommand instantCommand2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addCommands'");
  }
  public cmdAuto_Score() {
    // Use addRequirements() here to declare subsystem dependencies.
    this.swerve = swerve;
    this.speed = speed;
    addRequirements(swerve);
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     addCommands(
                 new InstantCommand(() -> System.out.println("Starting Auto...")),
                 AutoBuilder.buildAuto("YourAutoPath"), // This runs the auto
                 new InstantCommand(() -> System.out.println("Auto Finished!"))
             );
       }
       

     
       
      
     
       // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.Constants;
// import frc.robot.subsystems.subAlge;

// /* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
// public class CmdAlge extends Command {

//   private subAlge alge;
//   private boolean up;
//   /** Creates a new CmdAlge. */
//   public CmdAlge(subAlge alge, boolean up) {
//     // Use addRequirements() here to declare subsystem dependencies.
//     this.alge = alge;
//     this.up = up;
    
//   }

//   // Called when the command is initially scheduled.

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {

//     alge.algemotor1.set((up) ? Constants.Alge.kalgeSpeed : -Constants.Alge.kalgeSpeed);
//   }

//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {

//     alge.algemotor1.set(0);

//   }
// }

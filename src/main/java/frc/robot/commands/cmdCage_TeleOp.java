package frc.robot.commands;

 import edu.wpi.first.wpilibj2.command.Command;
 import frc.robot.Constants;
 import frc.robot.subsystems.subCage;


 public class cmdCage_TeleOp extends Command {
     private subCage cage;
     private boolean up;

     public cmdCage_TeleOp(subCage cage, boolean up) {
         this.cage = cage;
         this.up = up;
     }

     @Override
     public void execute() {
       cage.cageMotor.set((up) ? Constants.Cage.cageSpeed : -Constants.Cage.cageSpeed);
     }

     @Override
     public void end(boolean isFinished) {
       cage.cageMotor.set(0);
     }
 }
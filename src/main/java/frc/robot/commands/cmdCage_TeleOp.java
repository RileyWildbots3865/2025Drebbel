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
    public void initialize(){}

    @Override
    public void execute() {
        if(up) {
            cage.servo.setAngle(150);
            if(cage.servo.getAngle() > 147){
                cage.cageMotor.set(Constants.Cage.cageSpeed);
            }
        } 
         else{
            cage.servo.setAngle(130);
            cage.cageMotor.set(-Constants.Cage.cageSpeed);
        }
        if(cage.servo.getAngle() >= 145){
            Constants.Cage.servoisup = true;
        }
        else{
            Constants.Cage.servoisup = false; 
        }  
    }

     @Override
     public void end(boolean isFinished) {
       cage.cageMotor.set(0);
     }
 }
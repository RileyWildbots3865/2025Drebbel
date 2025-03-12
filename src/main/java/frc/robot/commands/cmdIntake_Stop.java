package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.subIntake;


public class cmdIntake_Stop extends Command {
    private subIntake intake;
    public cmdIntake_Stop(subIntake intake){
      this.intake = intake;
    }

    @Override
    public void execute() {
        intake.intakeMotor1.stopMotor();
    }

    @Override
    public void end(boolean isFinished) {
        intake.intakeMotor1.set(0);
    }
}
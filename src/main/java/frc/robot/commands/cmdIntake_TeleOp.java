package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.subIntake;


public class cmdIntake_TeleOp extends Command {
    private subIntake intake;
    private boolean in;

    public cmdIntake_TeleOp(subIntake intake, boolean in) {
        this.intake = intake;
        this.in = in;
    }

    @Override
    public void execute() {
        intake.intakeMotor1.set((in) ? Constants.Intake.kintakeSpeed : -Constants.Intake.kintakeSpeed);
    }

    @Override
    public void end(boolean isFinished) {
        intake.intakeMotor1.set(0);
    }
}
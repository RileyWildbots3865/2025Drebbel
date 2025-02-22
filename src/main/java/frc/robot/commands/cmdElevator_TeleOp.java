package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.subElevator;


public class cmdElevator_TeleOp extends Command {
    private subElevator elevator;
    private boolean up;

    public cmdElevator_TeleOp(subElevator elevator, boolean up) {
        this.elevator = elevator;
        this.up = up;
    }

    @Override
    public void execute() {
        elevator.elevatorMotor1.set((up) ? -Constants.Elevator.kelevatorSpeed : Constants.Elevator.kelevatorSpeed);
    }

    @Override
    public void end(boolean isFinished) {
        elevator.elevatorMotor1.set(0);
    }
}
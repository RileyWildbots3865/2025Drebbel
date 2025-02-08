package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.subFunnel;


public class cmdFunnel_TeleOp extends Command {
    private subFunnel funnel;
    private boolean up;

    public cmdFunnel_TeleOp(subFunnel funnel, boolean up) {
        this.funnel = funnel;
        this.up = up;
    }

    @Override
    public void execute() {
        funnel.funnelMotor1.set((up) ? Constants.MechConstants.kfunnelSpeed : -Constants.MechConstants.kfunnelSpeed);
    }

    @Override
    public void end(boolean isFinished) {
        funnel.funnelMotor1.set(0);
    }
}
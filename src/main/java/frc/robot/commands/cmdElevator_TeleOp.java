package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.subElevator;

public class cmdElevator_TeleOp extends Command{
    private subElevator elevator;
    private boolean up;
    
    public cmdElevator_TeleOp(subElevator elevator, Boolean up){

    }
}

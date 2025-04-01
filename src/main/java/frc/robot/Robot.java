package frc.robot;

import com.pathplanner.lib.pathfinding.LocalADStar;
import com.pathplanner.lib.pathfinding.Pathfinding;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Constants.VisionConstants;
import frc.robot.classes.LimelightHelpers;

public class Robot extends TimedRobot {
  
  private Command m_autonomousCommand;
  private boolean debounce = true;

  private final RobotContainer m_robotContainer;

  public Robot() {
    Pathfinding.setPathfinder(new LocalADStar());
    m_robotContainer = new RobotContainer();
    CameraServer.startAutomaticCapture(0);
  }

  @Override
  public void robotPeriodic() {
    if (!debounce && DriverStation.isDSAttached() && DriverStation.getAlliance().isPresent()){
      LimelightHelpers.SetRobotOrientation(VisionConstants.llName, DriverStation.getAlliance().get() == Alliance.Blue ? 0 : 180, 0, 0, 0, 0, 0);
      debounce = true;
    }
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {}
  

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}

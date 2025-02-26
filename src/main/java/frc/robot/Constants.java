package frc.robot;


public final class Constants {
  public static class OperatorConstants {
    public static final int DriverOne = 0;
    public static final int DriverTwo = 1;
  }
  public static class Extras {
    public static final int blinkinID = 1;
  }
  public static final class DriveConstants {
    public static final double kMaxSpeedMetersPerSecond = 2;
    public static final double kMaxAngularSpeed = 6.8 * Math.PI; // radians per second
  }
  public static final class Intake {
    public static final int intake1CanId = 9;
    public static final int intake2CanId = 10;

    public static final double kintakeSpeed = 0.4;
  }
  public static final class Funnel{
    public static final int funnel1CanId = 11;
    public static final int funnel2CanId = 12;

    public static final double kfunnelSpeed = 0.1;

    public static final double LUp = -7.785732746124268;
    public static final double LRest = -1.690476655960083;
  }
  public static final class Elevator {
    public static final int elevator1CanId = 13;
    public static final int elevator2CanId = 14;
    
    public static final double kelevatorSpeed = 0.2;

    public static final double L4 = -20;
    public static final double L3 = -47.45207977294922;
    public static final double L2 = -19.357078552246094;
    public static final double L1 = -0.095240704715252;
  }
  public static final class Cage {
    public static final int cageCanID = 15;
    public static final double cageSpeed = 0.3;
  }
}
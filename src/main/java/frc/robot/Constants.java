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
    public static final double kMaxSpeedMetersPerSecond = 1.3;
    public static final double kMaxAngularSpeed = 6.8 * Math.PI; // radians per second
  }
  public static final class MechConstants{
    public static final int intake1CanId = 9;
    public static final int intake2CanId = 10;
    public static final int funnelCanId = 11;
    public static final int elevator1CanId = 12;
    public static final int elevator2CanId = 13;

    public static final double kelevatorSpeed = 0.2;
    public static final double kintakeSpeed = 0.2;
  }
}
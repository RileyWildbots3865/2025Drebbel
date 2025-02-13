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
  public static final class Intake {
    public static final int intake1CanId = 9;
    public static final int intake2CanId = 10;

    public static final double kintakeSpeed = 0.2;
  }
  public static final class Funnel{
    public static final int funnel1CanId = 11;
    public static final int funnel2CanId = 12;

    public static final double kfunnelSpeed = 0.1;
  }
  public static final class Elevator {
    public static final int elevator1CanId = 13;
    public static final int elevator2CanId = 14;
    
    public static final double kelevatorSpeed = 0.2;

    public static final double L4 = 100;
    public static final double L3 = 70;
    public static final double L2 = 40;
    public static final double L1 = 13.5;

    public static final double pUp = 0.4;
    public static final double iUp = 0;
    public static final double dUp = 0;

    public static final double pDown = 0.1;
    public static final double iDown = 0;
    public static final double dDown = 0;
  }
}
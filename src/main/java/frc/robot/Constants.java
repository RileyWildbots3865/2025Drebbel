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
    // Robot Drive speed
    public static final double kMaxSpeedMetersPerSecond = 6;
    public static final double kMaxAngularSpeed = 6.8 * Math.PI; // radians per secon

    public static final double kMaxSpeedMetersPerSecond2 = 2;
    public static final double kMaxAngularSpeed2 = 3.4 * Math.PI; // radians per secon

  }
  public static final class Intake {
    public static final int intake1CanId = 9;
    public static final int intake2CanId = 10;
    public static final int inSensor = 0;
    //public static final int outSensor = 0;

    public static final double kintakeSpeed = 0.3;
  }
  public static final class Funnel{
    public static final int funnel1CanId = 11;
    public static final int funnel2CanId = 12;

    public static final double kfunnelSpeed = 0.2;

    public static final double LUp = -9.166702270507812;
    public static final double LRest = -2.265;
  }
  public static final class Elevator {
    public static final int elevator1CanId = 13;
    public static final int elevator2CanId = 14;
    
    public static final double kelevatorSpeed = 0.5
    ;

    public static final double L4 = -54;
    public static final double L3 = -23.90462303161621;
    public static final double L2 = -7.166679859161377;
    public static final double L1 = 0;
  }
  public static final class Cage {
    public static final int cageCanID = 15;
    public static final double cageSpeed = 1;

    public static boolean servoisup = false;
  }

  public static final class LimeLightOffsets{
    public static final double HorizontalOffset = 5.92;
    public static final double DistanceOffset = 5.1;
  }
  public static final class Alge{
    public static final int algemotor1CanId = 16;
    public static final int algemotor2CanId = 17;

    public static final double kalgeSpeed = 0.2;
  }
  
}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.config.PIDConstants;
import com.pathplanner.lib.config.RobotConfig;
import com.pathplanner.lib.controllers.PPHolonomicDriveController;
import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.AprilTagTrack;
import frc.robot.Constants.VisionConstants;
import frc.robot.classes.LimelightHelpers;
import frc.robot.classes.moduleConstants;
import frc.robot.classes.swerveModule;
import frc.robot.classes.LimelightHelpers.PoseEstimate;

public class subSwerve extends SubsystemBase {

  public static final double kFrontLeftOffset = 0.060302734375;
  public static final double kFrontRightOffset = 0.28173828125;
  public static final double kRearLeftOffset = 0.80859375 ;
  public static final double kRearRightOffset = 0.23779296875;
  // front left module
  public static final int kFrontLeftDrivingCanId = 3;
  public static final int kFrontLeftTurningCanId = 4;
  public static final int kFrontLeftCANcoder = 3;
  // front right module
  public static final int kFrontRightDrivingCanId = 7;
  public static final int kFrontRightTurningCanId = 8;
  public static final int kFrontRightCANcoder = 1;
  // rear left module
  public static final int kRearLeftDrivingCanId = 2;
  public static final int kRearLeftTurningCanId = 1;
  public static final int kRearLeftCANcoder = 2;
  // rear right module
  public static final int kRearRightDrivingCanId = 6;
  public static final int kRearRightTurningCanId = 5;
  public static final int kRearRightCANcoder = 4;

  private final swerveModule frontLeftModule = new swerveModule(kFrontLeftDrivingCanId,kFrontLeftTurningCanId,kFrontLeftCANcoder,kFrontLeftOffset);
  private final swerveModule frontRightModule = new swerveModule(kFrontRightDrivingCanId,kFrontRightTurningCanId,kFrontRightCANcoder,kFrontRightOffset);
  private final swerveModule rearLeftModule = new swerveModule(kRearLeftDrivingCanId,kRearLeftTurningCanId,kRearLeftCANcoder,kRearLeftOffset);
  private final swerveModule rearRightModule = new swerveModule(kRearRightDrivingCanId,kRearRightTurningCanId,kRearRightCANcoder,kRearRightOffset);
  
  //public Pigeon2 gyro;
  public AHRS gyro;
  public SwerveDriveOdometry odometry;
  public SwerveDrivePoseEstimator estimator;  
  public double angularVelocity;
  private PIDController desAnglePID = new PIDController(AprilTagTrack.kP, AprilTagTrack.kI, AprilTagTrack.kD);
  private PIDController desDistancePID = new PIDController(0.2, 0, 0);

  public Field2d field, llField;

  public subSwerve() {
    field = new Field2d();
    llField = new Field2d();
    gyro = new AHRS(NavXComType.kUSB1);

    desAnglePID.setSetpoint(0);
    desAnglePID.setTolerance(5);

    desDistancePID.setSetpoint(0.6);
    desDistancePID.setTolerance(0.1);

    // warning: thread may reset gyro while trying to read during odomerty intit
    new Thread(() -> {
      try {
        Thread.sleep(1000);
        gyro.reset();
        gyro.zeroYaw();
      } catch (Exception e) { }
    }).start();

    odometry = new SwerveDriveOdometry(
      moduleConstants.kDriveKinematics,
      gyro.getRotation2d(),
      new SwerveModulePosition[] {
        frontLeftModule.getPosition(),
        frontRightModule.getPosition(),
        rearLeftModule.getPosition(),
        rearRightModule.getPosition()
      });

    estimator = new SwerveDrivePoseEstimator(moduleConstants.kDriveKinematics, 
      getRotation2d(), 
      getModulePosition(), 
      getPose()
    );

  }

  public Pose2d getPose() { return odometry.getPoseMeters(); }
  public void resetPose(Pose2d pose) {
    odometry.resetPosition(
      getRotation2d(),
      new SwerveModulePosition[] {
        frontLeftModule.getPosition(),
        frontRightModule.getPosition(),
        rearLeftModule.getPosition(),
        rearRightModule.getPosition()
      },
      pose);
  }

  public void updateOdometry(){
    odometry.update(
    getRotation2d(),
      new SwerveModulePosition[] {
        frontLeftModule.getPosition(),
        frontRightModule.getPosition(),
        rearLeftModule.getPosition(),
        rearRightModule.getPosition()
      });
  }

  public void drive(double xSpeed, double ySpeed, double rot, boolean isFieldRelative) {
    var swerveModuleStates = moduleConstants.kDriveKinematics.toSwerveModuleStates(isFieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, getRotation2d()) : new ChassisSpeeds(xSpeed, ySpeed, rot));
    setModuleStates(swerveModuleStates);
  }
  

  public void setModuleStates(SwerveModuleState[] desiredStates) {
    SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.DriveConstants.kMaxSpeedMetersPerSecond);
    frontLeftModule.setDesiredState(desiredStates[0]);
    frontRightModule.setDesiredState(desiredStates[1]);
    rearLeftModule.setDesiredState(desiredStates[2]);
    rearRightModule.setDesiredState(desiredStates[3]);
  }

  public SwerveModulePosition[] getModulePosition(){
    SwerveModulePosition[] positions = new SwerveModulePosition[4];
    positions[0] = frontLeftModule.getPosition();
    positions[1] = frontRightModule.getPosition();
    positions[2] = rearLeftModule.getPosition();
    positions[3] = rearRightModule.getPosition();
    return positions;
  }

  public void stopModules(){
    frontLeftModule.stopModule();
    frontRightModule.stopModule();
    rearLeftModule.stopModule();
    rearRightModule.stopModule();
  }

  public void zeroHeading() { gyro.reset(); }
  public Rotation2d getRotation2d() { return gyro.getRotation2d();}

  public ChassisSpeeds getRobotRelativeSpeeds() {
    return moduleConstants.kDriveKinematics.toChassisSpeeds(
      frontLeftModule.getState(),
      frontRightModule.getState(),
      rearLeftModule.getState(),
      rearRightModule.getState()
    );
  }

  public void driveRobotRelative(ChassisSpeeds speeds) {
    SwerveModuleState[] states = moduleConstants.kDriveKinematics.toSwerveModuleStates(speeds);
    setModuleStates(states);
  }

  public void setUpPathPlanner() {
    RobotConfig config;
    try{
      config = RobotConfig.fromGUISettings();
      AutoBuilder.configure(
            this::getPose, // Robot pose supplier
            this::resetPose, // Method to reset odometry (will be called if your auto has a starting pose)
            this::getRobotRelativeSpeeds, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
            (speeds, feedforwards) -> driveRobotRelative(speeds), // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds. Also optionally outputs individual module feedforwards
            new PPHolonomicDriveController( // PPHolonomicController is the built in path following controller for holonomic drive trains
                    new PIDConstants(5.0, 0.0, 0.0), // Translation PID constants
                    new PIDConstants(5.0, 0.0, 0.0) // Rotation PID constants
            ),
            config, // The robot configuration
            () -> {
              // Boolean supplier that controls when the path will be mirrored for the red alliance
              // This will flip the path being followed to the red side of the field.
              // THE ORIGIN WILL REMAIN ON THE BLUE SIDE

              var alliance = DriverStation.getAlliance();
              if (alliance.isPresent()) {
                return alliance.get() == DriverStation.Alliance.Red;
              }
              return false;
            },
            this // Reference to this subsystem to set requirements
    );
    } catch (Exception e) {
      // Handle exception as needed
      e.printStackTrace();
    }
  }

  @Override
  public void periodic() {
    updateOdometry();
    estimator.update(getRotation2d(), getModulePosition());
    angularVelocity = gyro.getRate();
    field.setRobotPose(getPose());
    SmartDashboard.putData("Field", field);
    estimator.update(getRotation2d(), getModulePosition());
    SmartDashboard.putNumber("Gyro", gyro.getRotation2d().getDegrees());

    try {
      LimelightHelpers.SetRobotOrientation(VisionConstants.llName, estimator.getEstimatedPosition().getRotation().getDegrees(), 0, 0, 0, 0, 0);
      PoseEstimate mt2Results = LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2(VisionConstants.llName);
      llField.setRobotPose(mt2Results.pose);
      SmartDashboard.putData("LimelightField",llField);
      if ((Math.abs(getRobotRelativeSpeeds().vxMetersPerSecond) < VisionConstants.speedDeadbandValue) && (Math.abs(getRobotRelativeSpeeds().vyMetersPerSecond) < 2) && (Math.abs(angularVelocity) < VisionConstants.speedDeadbandValue)){
        estimator.setVisionMeasurementStdDevs(VecBuilder.fill(.7,.7,9999999));
        estimator.addVisionMeasurement(mt2Results.pose, mt2Results.timestampSeconds);
      }  
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
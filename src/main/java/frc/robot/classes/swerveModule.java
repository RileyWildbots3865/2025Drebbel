package frc.robot.classes;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class swerveModule {
    private final CANcoder rotationEncoder;

    public final SparkMax drivingSparkMax;
    private final SparkMax turningSparkMax;

    private final RelativeEncoder drivingEncoder;

    private final SparkClosedLoopController drivingPIDController; // Library replaced SparkPIDController
    private final PIDController turningPIDController;

    //private final String name;

    public swerveModule(int drivingCANId, int turningCANId, int canCoderId, double angularOffset) {
        //this.name = name;
        rotationEncoder = new CANcoder(canCoderId);
        CANcoderConfiguration canCoderConfiguration = new CANcoderConfiguration();
        canCoderConfiguration.MagnetSensor.AbsoluteSensorDiscontinuityPoint = 1; // 1 = [0,1), unsigned0_1      
        canCoderConfiguration.MagnetSensor.MagnetOffset = -angularOffset;
        rotationEncoder.getConfigurator().apply(canCoderConfiguration);
        rotationEncoder.getPosition().setUpdateFrequency(100);
        rotationEncoder.getVelocity().setUpdateFrequency(100);

        drivingSparkMax = new SparkMax(drivingCANId, MotorType.kBrushless);
        turningSparkMax = new SparkMax(turningCANId, MotorType.kBrushless);

        SparkBaseConfig driveConfig = new SparkMaxConfig(); //New config for changing sparkmax settings
        drivingEncoder = drivingSparkMax.getEncoder();
        drivingPIDController = drivingSparkMax.getClosedLoopController();
        
        driveConfig.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder);

        driveConfig.encoder.positionConversionFactor(moduleConstants.kDrivingEncoderPositionFactor)
        .velocityConversionFactor(moduleConstants.kDrivingEncoderVelocityFactor);

        driveConfig.closedLoop
        .pidf(
            moduleConstants.kDrivingP, 
            moduleConstants.kDrivingI, 
            moduleConstants.kDrivingD, 
            moduleConstants.kDrivingFF)

        .outputRange(
            moduleConstants.kDrivingMinOutput,
            moduleConstants.kDrivingMaxOutput);

        driveConfig.idleMode(moduleConstants.kDrivingMotorIdleMode);
        driveConfig.smartCurrentLimit(moduleConstants.kDrivingMotorCurrentLimit);
        driveConfig.inverted(true);

        SparkBaseConfig turnConfig = new SparkMaxConfig();
        turnConfig.inverted(true);
        turnConfig.idleMode(moduleConstants.kTurningMotorIdleMode);
        turnConfig.smartCurrentLimit(moduleConstants.kTurningMotorCurrentLimit);
        turningPIDController = new PIDController(0.01, 0, 0);
        turningPIDController.setIntegratorRange(-1, 1);
        turningPIDController.enableContinuousInput(0, 360);

        drivingSparkMax.configure(driveConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);
        turningSparkMax.configure(turnConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kPersistParameters);

        drivingEncoder.setPosition(0);
    }
    
    public SwerveModulePosition getPosition() {
        return new SwerveModulePosition(drivingEncoder.getPosition(), getRotation2d());
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(
            drivingSparkMax.getEncoder().getVelocity(),
            getRotation2d()
        );
    }
    
    public void setDesiredState(SwerveModuleState desiredState) {
        SwerveModuleState optimizedDesiredState = desiredState;
        optimizedDesiredState.optimize(getRotation2d());
        if(Math.abs(optimizedDesiredState.speedMetersPerSecond) < 0.02){
            stopModule();
            return;
        }
        drivingPIDController.setReference(optimizedDesiredState.speedMetersPerSecond, SparkMax.ControlType.kVelocity);

        turningSparkMax.set(turningPIDController.calculate(getAngle(), optimizedDesiredState.angle.getDegrees()));
    }
    public double getAngle(){
        return rotationEncoder.getAbsolutePosition().getValueAsDouble() * 360;
    }
    public double getRawAngle(){        
        return rotationEncoder.getAbsolutePosition().getValueAsDouble(); //* 360;    
    }
    public Rotation2d getRotation2d() {   
        return Rotation2d.fromDegrees(getAngle());     
    }
    public void stopModule(){         
        drivingSparkMax.stopMotor(); turningSparkMax.stopMotor();    
    }
}
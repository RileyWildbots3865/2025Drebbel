// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import com.revrobotics.spark.SparkMax;
// import com.revrobotics.spark.config.SparkMaxConfig;
// import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
// import com.revrobotics.spark.SparkBase.PersistMode;
// import com.revrobotics.spark.SparkBase.ResetMode;
// import com.revrobotics.spark.SparkLowLevel.MotorType;

// import edu.wpi.first.wpilibj2.command.SubsystemBase;


// import frc.robot.Constants;



// public class subAlge extends SubsystemBase {
//   public SparkMax algemotor1;
//   public SparkMax algemotor2;

//   SparkMaxConfig alge1config;
//   SparkMaxConfig alge2config;
//   /** Creates a new Alge. */
//   public subAlge() {
//     algemotor1 = new SparkMax(16, MotorType.kBrushless);
//     algemotor2 = new SparkMax(17, MotorType.kBrushless);


//     alge1config.idleMode(IdleMode.kBrake);
//     alge2config.follow(algemotor1, true);
//     alge2config.idleMode(IdleMode.kBrake);
//     algemotor1.configure(alge1config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
//     algemotor2.configure(alge2config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

//   }

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
// }

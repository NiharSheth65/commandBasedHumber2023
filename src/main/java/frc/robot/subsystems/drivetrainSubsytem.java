// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class drivetrainSubsytem extends SubsystemBase {

  private CANSparkMax leftMotorFront;
  private CANSparkMax leftMotorBack;
  private CANSparkMax rightMotorFront;
  private CANSparkMax rightMotorBack;

  /** Creates a new drivetrainSubsytem. */
  public drivetrainSubsytem() {
    leftMotorFront = new CANSparkMax(2, MotorType.kBrushless);
    leftMotorBack = new CANSparkMax(1, MotorType.kBrushless);
    rightMotorFront = new CANSparkMax(3, MotorType.kBrushless);
    rightMotorBack = new CANSparkMax(4, MotorType.kBrushless);

    leftMotorFront.restoreFactoryDefaults();
    leftMotorBack.restoreFactoryDefaults();
    rightMotorFront.restoreFactoryDefaults();
    rightMotorBack.restoreFactoryDefaults();


    rightMotorFront.setInverted(true);
    rightMotorBack.setInverted(true);

    // get back motors to follow front motors
    leftMotorBack.follow(leftMotorFront);
    rightMotorBack.follow(rightMotorFront);
    
    // leftMotorFront.setInverted(true);
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void set(double drive_rate, double turn_rate){
    rightMotorFront.set(drive_rate + turn_rate); 
    leftMotorFront.set(drive_rate - turn_rate);
  }

  public void stop(){
    rightMotorFront.set(0);
    leftMotorFront.set(0);
  }
}

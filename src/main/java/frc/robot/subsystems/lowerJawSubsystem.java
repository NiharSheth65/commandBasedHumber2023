// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class lowerJawSubsystem extends SubsystemBase {
  /** Creates a new lowerJawSubsystem. */

  private CANSparkMax jawLowerMotor;
  private RelativeEncoder jawLowerEncoder;


  public lowerJawSubsystem() {
    jawLowerMotor = new CANSparkMax(5, MotorType.kBrushless); 
    jawLowerMotor.restoreFactoryDefaults();
    jawLowerEncoder = jawLowerMotor.getEncoder();
    
    jawLowerMotor.getEncoder().setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getEncoder(){
    return jawLowerEncoder.getPosition(); 
  }

  public void setLowerJaw(double intakeSpeed){
    jawLowerMotor.set(intakeSpeed);
  }

  public void stop(){
    jawLowerMotor.set(0);
  }




}

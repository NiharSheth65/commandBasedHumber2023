// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class upperJawSubsystem extends SubsystemBase {

  private CANSparkMax jawUpperMotor;
  private RelativeEncoder jawUpperEncoder;

  /** Creates a new upperJawSubsystem. */
  public upperJawSubsystem() {
    jawUpperMotor = new CANSparkMax(5, MotorType.kBrushless); 
    jawUpperMotor.restoreFactoryDefaults();
    jawUpperEncoder = jawUpperMotor.getEncoder(); 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getEncoder(){
    return jawUpperEncoder.getPosition(); 
  }

  public void setUpperJaw(double speed){
    jawUpperMotor.set(speed);
  }

  public void stop(){
    jawUpperMotor.set(0);
  }

}

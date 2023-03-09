// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class lowerIntakeSubsystem extends SubsystemBase {
  
  private VictorSP lowerIntakeMotor = new VictorSP(0);

  /** Creates a new lowerIntakeSubsystem. */
  public lowerIntakeSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setIntake(double speed){
    lowerIntakeMotor.set(speed);
  }

  public void stopIntake(){
    lowerIntakeMotor.set(0);
  }
  
}

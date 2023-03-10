// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class pistonShooterSubsystem extends SubsystemBase {
  /** Creates a new pistonShooterSubsystem. */

  DoubleSolenoid pistonSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
  
  public pistonShooterSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void pistonExtend(){
    pistonSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void pistonRetract(){
    pistonSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void pistonOff(){
    pistonSolenoid.set(DoubleSolenoid.Value.kOff);
  }

}

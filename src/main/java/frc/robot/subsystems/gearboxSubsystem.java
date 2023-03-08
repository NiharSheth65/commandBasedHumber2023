// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class gearboxSubsystem extends SubsystemBase {

  DoubleSolenoid gearSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);

  /** Creates a new gearboxSubsystem. */
  public gearboxSubsystem() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void torqueMode(){
    gearSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void speedMode(){
    gearSolenoid.set(DoubleSolenoid.Value.kReverse);
  }
}

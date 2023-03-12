// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class winchSubsystem extends SubsystemBase {
  /** Creates a new winchSubsystem. */

  private VictorSP winchMotor = new VictorSP(4);

  private DigitalInput limitSwitchBack = new DigitalInput(0);
  private DigitalInput limitSwitchFront = new DigitalInput(1);

  boolean limitStateBack;
  boolean limitStateFront;

  public winchSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    limitStateBack = limitSwitchBack.get();
    limitStateFront = limitSwitchFront.get();

    SmartDashboard.putBoolean("limit state back", limitStateBack);
    SmartDashboard.putBoolean("limit state front", limitStateFront);
  }

  public boolean frontLimitSwitch() {
    return limitStateFront;
  }

  public boolean backLimitSwitch() {
    return limitStateBack;
  }

  public void runWinchOut(double speed) {
    SmartDashboard.putBoolean("winching out", true); 
    winchMotor.set(-speed);
    SmartDashboard.putBoolean("front limit switch", frontLimitSwitch());
    SmartDashboard.putBoolean("back limit switch", backLimitSwitch());  
  }

  public void runWinchIn(double speed) {
    SmartDashboard.putBoolean("winching in", true);  
    winchMotor.set(speed);
    SmartDashboard.putBoolean("front limit switch", frontLimitSwitch());
    SmartDashboard.putBoolean("back limit switch", backLimitSwitch());  
  }

  public void stopWinch() {
    winchMotor.set(0);
  }

}

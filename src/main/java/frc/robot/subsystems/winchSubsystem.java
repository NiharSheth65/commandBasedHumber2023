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

  public void runWinch(double speed, int mode){
    double winchMotorSpeed; 

    if (mode == 0) {
      winchMotorSpeed = 0;
    }

    else if (mode == 1) {
      if (limitStateFront == true) {
        winchMotorSpeed = 0;
        mode = 0;
      }

      else {
        winchMotorSpeed = -0.5;
      }
    }

    // button lb is asking to go backward, give backward speed
    else if (mode == 2) {
      if (limitStateBack == true) {
        winchMotorSpeed = 0;
        mode = 0;
      }

      else {
        winchMotorSpeed = 0.5;
      }
    }

    else {
      winchMotorSpeed = 0;
    }

    winchMotor.set(winchMotorSpeed);
  }

  public void stopWinch(){
    winchMotor.set(0);
  }

}

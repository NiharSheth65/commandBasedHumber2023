// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class intakeSubsystem extends SubsystemBase {
  /** Creates a new intakeSubsystem. */
  
  private VictorSP upperRightIntakeMotor = new VictorSP(1);
  private VictorSP upperLeftIntakeMotor = new VictorSP(2);
  private DigitalInput intakeSwitch = new DigitalInput(2);
  
  public intakeSubsystem() {
    upperRightIntakeMotor.setInverted(true);
    upperLeftIntakeMotor.setInverted(false);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("IntakeSwitch", intakeSwitch.get());
    // This method will be called once per scheduler run
  }


  public boolean intakeLimitSwitch(){
    return intakeSwitch.get(); 
  }

  public void intake(double intakeSpeed){
    upperRightIntakeMotor.set(intakeSpeed);
    upperLeftIntakeMotor.set(intakeSpeed);
    SmartDashboard.putNumber("running speed", intakeSpeed); 
  }

  public void outtake(double outtakeSpeed){
    upperRightIntakeMotor.set(outtakeSpeed);
    upperLeftIntakeMotor.set(outtakeSpeed);
  }

  public void stop(){
    upperRightIntakeMotor.set(0);
    upperLeftIntakeMotor.set(0);
  }

}

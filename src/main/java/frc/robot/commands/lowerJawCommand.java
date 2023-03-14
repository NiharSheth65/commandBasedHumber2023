// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.LowerJawConstants;
import frc.robot.subsystems.lowerJawSubsystem;

public class lowerJawCommand extends CommandBase {

  private lowerJawSubsystem LOWERJAW_SUBSYSTEM; 
  private PIDController pidController; 
  double jawSetpoint; 

  /** Creates a new lowerJawCommand. */
  public lowerJawCommand(lowerJawSubsystem jaw, double setpoint) {
    this.LOWERJAW_SUBSYSTEM = jaw; 
    this.pidController = new PIDController(LowerJawConstants.lowerJawKP, LowerJawConstants.lowerJawKI, LowerJawConstants.lowerJawKD); 
    this.jawSetpoint = setpoint; 
    addRequirements(LOWERJAW_SUBSYSTEM);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pidController.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double measurment = LOWERJAW_SUBSYSTEM.getEncoder(); 
    LOWERJAW_SUBSYSTEM.setLowerJaw(pidController.calculate(measurment, jawSetpoint));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    LOWERJAW_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if(Math.abs(LOWERJAW_SUBSYSTEM.getEncoder() - jawSetpoint) < 0.1){
      return true; 
    }
    else{
      return false;
    }    
  }
}

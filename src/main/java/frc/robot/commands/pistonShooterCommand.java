// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.pistonShooterSubsystem;

public class pistonShooterCommand extends CommandBase {

  private pistonShooterSubsystem PISTON_SUBSYSTEM; 
  int pistonMode; 
  double timer; 
  double pistonDelay; 

  /** Creates a new pistonShooterCommand. */
  public pistonShooterCommand(pistonShooterSubsystem piston, int mode, double delay) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.PISTON_SUBSYSTEM = piston; 
    this.pistonMode = mode; 
    this.pistonDelay = delay; 
    addRequirements(PISTON_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    SmartDashboard.putNumber("piston mode", pistonMode); 
    if(pistonMode == 1){
      PISTON_SUBSYSTEM.pistonExtend();
      pistonMode++;  
      timer = System.currentTimeMillis(); 
    }

    else if(pistonMode == 2){
      if(System.currentTimeMillis() > timer + pistonDelay){
        pistonMode++; 
      } 
    }

    else if(pistonMode == 3){
      PISTON_SUBSYSTEM.pistonRetract();
      pistonMode++; 
    }

    else{
      PISTON_SUBSYSTEM.pistonRetract();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    pistonMode = 1; 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(pistonMode == 4){
      return true; 
    }
    else{
      return false; 
    }
  }
}

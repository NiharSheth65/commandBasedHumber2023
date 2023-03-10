// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.pistonShooterSubsystem;

public class pistonShooterCommand extends CommandBase {

  private pistonShooterSubsystem PISTON_SUBSYSTEM; 
  int pistonMode = 0; 

  /** Creates a new pistonShooterCommand. */
  public pistonShooterCommand(pistonShooterSubsystem piston, int mode) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.PISTON_SUBSYSTEM = piston; 
    this.pistonMode = mode; 
    addRequirements(PISTON_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(pistonMode == 1){
      PISTON_SUBSYSTEM.pistonExtend();
    }else if(pistonMode == 2){
      PISTON_SUBSYSTEM.pistonRetract();
    }else{
      PISTON_SUBSYSTEM.pistonOff();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    PISTON_SUBSYSTEM.pistonOff();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

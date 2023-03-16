// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrainSubsytem;

public class dockingCommand extends CommandBase {
  
  private drivetrainSubsytem DRIVE_SUBSYSTEM; 
  double pitch; 
  double pitchTarget; 
  double outputSpeed = 0; 
  int outputMultipier = 1; 

  /** Creates a new autoDockingCommand. */
  public dockingCommand(drivetrainSubsytem drive) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.DRIVE_SUBSYSTEM = drive; 
    addRequirements(drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pitchTarget = DRIVE_SUBSYSTEM.gyroRoll(); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    pitch = DRIVE_SUBSYSTEM.gyroRoll(); 
    
    if(Math.abs(pitchTarget - pitch) > 8){
      outputSpeed = 0.3;     
    }

        
    else if(Math.abs(pitchTarget - pitch) <= 8 && Math.abs(pitchTarget - pitch) > 4){
      outputSpeed = 0.2;     
    }

    else if(Math.abs(pitchTarget - pitch) <= 4 && Math.abs(pitchTarget - pitch) > 1){
      outputSpeed = 0.1; 
    }

    else if(Math.abs(pitchTarget-pitch) <= 1){
      outputSpeed = 0.05; 
    }

    if(pitch < pitchTarget){
      outputMultipier = -1; 
    }

    else if(pitch > pitchTarget){
      outputMultipier = 1; 
    }

    outputSpeed *= outputMultipier; 
    DRIVE_SUBSYSTEM.set(outputSpeed, 0);
  }




  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

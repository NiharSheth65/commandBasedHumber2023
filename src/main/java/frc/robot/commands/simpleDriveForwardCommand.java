// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrainSubsytem;

public class simpleDriveForwardCommand extends CommandBase {
  /** Creates a new simpleDriveForwardCommand. */
  private drivetrainSubsytem DRIVE_SUBSYSTEM; 
  private double target; 
  double averageDistance; 

  public simpleDriveForwardCommand(drivetrainSubsytem drive, double encoderPosition) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.DRIVE_SUBSYSTEM = drive; 
    this.target = encoderPosition;   
    addRequirements(DRIVE_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    averageDistance = 0; 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DRIVE_SUBSYSTEM.set(0.2, 0);
    averageDistance = (DRIVE_SUBSYSTEM.rightEncoder() + DRIVE_SUBSYSTEM.leftEncoder())/2; 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DRIVE_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(averageDistance) >= Math.abs(target)){
      return true; 
    }else{
      return false;
    }
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrainSubsytem;

public class turnCommand extends CommandBase {
  /** Creates a new turnCommand. */

  private drivetrainSubsytem DRIVE_SUBSYSTEM; 
  private double turnAngle; 

  public turnCommand(drivetrainSubsytem drive, double turn) {
    this.DRIVE_SUBSYSTEM = drive; 
    this.turnAngle = turn; 
    addRequirements(DRIVE_SUBSYSTEM);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    DRIVE_SUBSYSTEM.resetEncoders();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DRIVE_SUBSYSTEM.setRight(0.5);
    DRIVE_SUBSYSTEM.setLeft(-0.5);  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DRIVE_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if(Math.abs(DRIVE_SUBSYSTEM.rightEncoder()) < 0.1){
      return true; 
    }

    else{
      return false;
    } 
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.winchSubsystem;

public class winchCommand extends CommandBase {
  /** Creates a new winchCommand. */
  
  private winchSubsystem WINCH_SUBSYSTEM; 
  double winchSpeed; 

  public winchCommand(winchSubsystem winch, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.WINCH_SUBSYSTEM = winch; 
    this.winchSpeed = speed; 

    addRequirements(WINCH_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    WINCH_SUBSYSTEM.runWinch(winchSpeed);
    System.out.println("winch should be running"); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    WINCH_SUBSYSTEM.stopWinch();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

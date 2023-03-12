// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class delayCommand extends CommandBase {
  /** Creates a new delayCommand. */
  double startTime; 
  double delay; 
  public delayCommand(double delayTime) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.delay = delayTime; 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = System.currentTimeMillis(); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(System.currentTimeMillis() > startTime + delay){
      return true; 
    }

    else{
      return false; 
    }
  }
}

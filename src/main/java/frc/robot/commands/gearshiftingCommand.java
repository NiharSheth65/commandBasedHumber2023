// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.gearboxSubsystem;

public class gearshiftingCommand extends CommandBase {
  /** Creates a new gearshiftingCommand. */


  gearboxSubsystem GEARBOX_SUBSYSTEM; 
  int gearMode; 

  public gearshiftingCommand(gearboxSubsystem gearbox, int mode) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.GEARBOX_SUBSYSTEM = gearbox; 
    this.gearMode = mode; 
    addRequirements(GEARBOX_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(gearMode == 1){
      GEARBOX_SUBSYSTEM.torqueMode();
    }

    else if(gearMode == 2){
      GEARBOX_SUBSYSTEM.speedMode(); 
    }
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

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.lowerIntakeSubsystem;

public class lowerIntakeCommand extends CommandBase {

  private lowerIntakeSubsystem LOWERINTAKE_SUBSYSTEM; 
  double intakeSpeed; 

  /** Creates a new lowerIntakeCommand. */
  public lowerIntakeCommand(lowerIntakeSubsystem intake, double speed) {
    this.LOWERINTAKE_SUBSYSTEM = intake; 
    this.intakeSpeed = speed; 
    addRequirements(LOWERINTAKE_SUBSYSTEM);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    LOWERINTAKE_SUBSYSTEM.setIntake(intakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    LOWERINTAKE_SUBSYSTEM.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

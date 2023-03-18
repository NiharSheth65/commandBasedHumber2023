// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intakeSubsystem;

public class outtakeCommand extends CommandBase {
  
  private intakeSubsystem INTAKE_SUBSYSTEM; 
  double outtakeSpeed; 
  double startTime; 

  /** Creates a new outtakeCommand. */
  public outtakeCommand(intakeSubsystem intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.INTAKE_SUBSYSTEM = intake; 
    addRequirements(INTAKE_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    outtakeSpeed = 0; 
    startTime = System.currentTimeMillis(); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    outtakeSpeed = -0.5; 
    INTAKE_SUBSYSTEM.outtake(outtakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    INTAKE_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(INTAKE_SUBSYSTEM.intakeLimitSwitch() == true){
      // if current time - start time == 100
      if(System.currentTimeMillis() > startTime + 250){
        return true; 
      }

      else {return false;} 
      
    }
    else{
      return false;
    } 
  }
}

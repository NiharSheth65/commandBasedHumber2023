// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.UpperIntakeConstants;
import frc.robot.subsystems.intakeSubsystem;

public class defaultUpperIntakeCommand extends CommandBase {
  
  private intakeSubsystem INTAKE_SUBSYSTEM; 
  private double intakeSpeed; 
  private boolean intakeLimitSwitch; 

  /** Creates a new defaultUpperIntakeCommand. */
  public defaultUpperIntakeCommand(intakeSubsystem intake, double speed) {
    this.INTAKE_SUBSYSTEM = intake; 
    this.intakeSpeed = speed; 
    addRequirements(INTAKE_SUBSYSTEM);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    intakeLimitSwitch = INTAKE_SUBSYSTEM.intakeLimitSwitch(); 

    if(intakeLimitSwitch == false && intakeSpeed == UpperIntakeConstants.upperOuttakeSpeed){
      INTAKE_SUBSYSTEM.intake(intakeSpeed);
    }

    else if(intakeLimitSwitch == false){
      INTAKE_SUBSYSTEM.intake(UpperIntakeConstants.upperIntakeDefaultSpeed);  
    }
    else{
      INTAKE_SUBSYSTEM.intake(intakeSpeed);
    }

    System.out.println("Should be running");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    INTAKE_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

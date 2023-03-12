// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.upperJawSubsystem;

public class upperJawCommand extends CommandBase {
  
  private upperJawSubsystem UPPERJAW_SUBSYSTEM; 
  private PIDController pidController; 

  double staticSpeed; 

  /** Creates a new upperIntakeCommand. */
  public upperJawCommand(upperJawSubsystem jaw, double setpoint, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.UPPERJAW_SUBSYSTEM = jaw; 
    this.pidController = new PIDController(0.0375, 0.005, 0); 
    this.staticSpeed = speed; 
    pidController.setSetpoint(setpoint);
    addRequirements(UPPERJAW_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    pidController.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // double measurment = UPPERJAW_SUBSYSTEM.getEncoder(); 
    // UPPERJAW_SUBSYSTEM.setUpperJaw(pidController.calculate(measurment));
    UPPERJAW_SUBSYSTEM.setUpperJaw(staticSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    UPPERJAW_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

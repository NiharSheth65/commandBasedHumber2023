// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ledSubsystem;

public class ledCommand extends CommandBase {
  /** Creates a new ledCommand. */
  
  private ledSubsystem LED_SUBSYSTEM; 
  int colourMode; 
  int red; 
  int green; 
  int blue; 

  public ledCommand(ledSubsystem LED, int mode) {
    this.LED_SUBSYSTEM = LED; 
    this.colourMode = mode; 

    addRequirements(LED);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(colourMode == 1){
      red = 255; 
      green = 0; 
      blue = 0; 
    }

    else if(colourMode == 2){
      red = 0; 
      green = 0; 
      blue = 255; 
    }

    else if(colourMode == 3){
      red = 0; 
      green = 255; 
      blue = 0;  
    }

    else{
      red = 100; 
      green = 100; 
      blue = 100; 
    }

    LED_SUBSYSTEM.setLED(red, green, blue);
 
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

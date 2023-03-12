// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.winchSubsystem;

public class toggleWinchCommand extends CommandBase {

  private winchSubsystem WINCH_SUBSYSTEM;
  private double winchSpeed; 
  private boolean limitFrontInitial; 
  private boolean limitBackInitial; 
  int count; 
  int countIncreaser; 
  /** Creates a new toggleWinchCommand. */
  public toggleWinchCommand(winchSubsystem winch, double speed, int increaser) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.WINCH_SUBSYSTEM = winch; 
    this.winchSpeed = speed; 
    this.countIncreaser = increaser; 
    this.count = 0; 
    addRequirements(WINCH_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limitFrontInitial = WINCH_SUBSYSTEM.frontLimitSwitch(); 
    limitBackInitial = WINCH_SUBSYSTEM.backLimitSwitch(); 
    count+= countIncreaser; 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(count % 2 == 0){
      WINCH_SUBSYSTEM.runWinchOut(winchSpeed);
    }
  

    else if(count % 2 == 1){
      WINCH_SUBSYSTEM.runWinchIn(winchSpeed);
    }

    else{
      WINCH_SUBSYSTEM.stopWinch();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if(limitFrontInitial == false && WINCH_SUBSYSTEM.frontLimitSwitch() == true){
      return true; 
    }

    else if(limitBackInitial == false && WINCH_SUBSYSTEM.backLimitSwitch() == true){
      return true; 
    }

    else{
      return false;
    }
  }
}

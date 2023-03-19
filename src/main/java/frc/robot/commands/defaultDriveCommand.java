// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.drivetrainSubsytem;

public class defaultDriveCommand extends CommandBase {
  /** Creates a new defaultDriveCommand. */

  private drivetrainSubsytem DRIVE_SUBSYSTEM; 
  private Joystick joy; 

  double drive; 
  double turn;
  double driveFactor;
  double turnFactor;  

  SlewRateLimiter drive_Limiter = new SlewRateLimiter(1); 
  SlewRateLimiter turn_Limiter = new SlewRateLimiter(1); 

  public defaultDriveCommand(drivetrainSubsytem drive, Joystick joystick, double speedSafety, double turnSafety) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.DRIVE_SUBSYSTEM = drive; 
    this.joy = joystick; 
    this.driveFactor = speedSafety; 
    this.turnFactor = turnSafety; 
    addRequirements(DRIVE_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive = 0; 
    turn = 0; 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    // drive 
    if(Math.abs(joy.getRawAxis(1)) < 0.1){
      drive = 0; 
    }
    else{
      drive = joy.getRawAxis(1);  
    }

    // turn 
    if(Math.abs(joy.getRawAxis(4)) < 0.1){
      turn = 0; 
    }
    else{
      // turn = turn_Limiter.calculate(joy.getRawAxis(4)); 
      turn = joy.getRawAxis(4); 
    }
    
    SmartDashboard.putNumber("left joystick", drive);
    SmartDashboard.putNumber("right joystick", turn); 

    drive *= driveFactor; 
    turn *= turnFactor; 
    DRIVE_SUBSYSTEM.set(drive, turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DRIVE_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

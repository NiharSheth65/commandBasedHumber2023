// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrainSubsytem;

public class autoDriveForwardCommand extends CommandBase {
  /** Creates a new autoDriveForwardCommand. */
  private drivetrainSubsytem DRIVETRAIN_SUBSYSTEM; 
  private PIDController gryoDrivePIDControllers; 
  
  private PIDController velocityRightPIDControllers;  
  private PIDController velocityLeftPIDControllers; 

  double gyroTargetPosition; 
  
  double driveForwardSetPoint; 
  double driveForwardSpeed; 

  double autonomousSpeed;
  double rightWheelOutput;
  double leftWheelOutput;

  double averageDistance;
  public autoDriveForwardCommand(drivetrainSubsytem drive, double driveDistance, double driveSpeed) {
    this.DRIVETRAIN_SUBSYSTEM = drive; 

    this.gryoDrivePIDControllers = new PIDController(0.005, 0, 0); 
    this.velocityLeftPIDControllers = new PIDController(0.0003,0.0001,0); 
    this.velocityRightPIDControllers = new PIDController(0.0003,0.0001,0);
    this.driveForwardSetPoint = driveDistance; 
    this.driveForwardSpeed = driveSpeed; 

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(DRIVETRAIN_SUBSYSTEM);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    gyroTargetPosition = DRIVETRAIN_SUBSYSTEM.gyroYaw(); 
    averageDistance = 0; 
    velocityLeftPIDControllers.reset();
    velocityRightPIDControllers.reset();
    gryoDrivePIDControllers.reset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftWheelVelocity = DRIVETRAIN_SUBSYSTEM.leftVelocity(); 
    double rightWheelVelocity = DRIVETRAIN_SUBSYSTEM.rightVelocity(); 
  

    double gyroSpeed = gryoDrivePIDControllers.calculate(DRIVETRAIN_SUBSYSTEM.gyroYaw(), gyroTargetPosition);
    averageDistance = (DRIVETRAIN_SUBSYSTEM.rightEncoder() + DRIVETRAIN_SUBSYSTEM.leftEncoder())/2; 

    // sign should otherwise be flipped to >
    if (averageDistance > driveForwardSetPoint) {
      if (driveForwardSetPoint < 0) {
        autonomousSpeed = -1 * driveForwardSpeed;
      }

      else {
        // autonomousSpeed = 1000;
        autonomousSpeed = driveForwardSpeed;
      }
    }

    else {
      autonomousSpeed = 0;
      gyroSpeed = 0;
    }

    rightWheelOutput = velocityRightPIDControllers.calculate(rightWheelVelocity, autonomousSpeed);
    leftWheelOutput = velocityLeftPIDControllers.calculate(leftWheelVelocity, autonomousSpeed);

    double maxOutput = 0.8;

    if (leftWheelOutput > maxOutput) {
      leftWheelOutput = maxOutput;
    }

    else if (leftWheelOutput < -maxOutput) {
      leftWheelOutput = -maxOutput;
    }

    if (rightWheelOutput > maxOutput) {
      rightWheelOutput = maxOutput;
    }

    else if (rightWheelOutput < -maxOutput) {
      leftWheelOutput = -maxOutput;
    }


    // adding some gyro stuff
    DRIVETRAIN_SUBSYSTEM.setRight(-1*(rightWheelOutput - gyroSpeed));
    DRIVETRAIN_SUBSYSTEM.setLeft(-1*(leftWheelOutput + gyroSpeed));
  
    // DRIVETRAIN_SUBSYSTEM.set(0.2, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    DRIVETRAIN_SUBSYSTEM.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(averageDistance) >= Math.abs(driveForwardSetPoint)){
        return true; 
    }

    else{
        return false; 
    }

    // return false; 
  }
}

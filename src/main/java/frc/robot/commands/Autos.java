// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.drivetrainSubsytem;
import frc.robot.subsystems.gearboxSubsystem;
import frc.robot.subsystems.intakeSubsystem;
import frc.robot.subsystems.lowerIntakeSubsystem;
import frc.robot.subsystems.lowerJawSubsystem;
import frc.robot.subsystems.pistonShooterSubsystem;
import frc.robot.subsystems.upperJawSubsystem;
import frc.robot.subsystems.winchSubsystem;

public final class Autos {


  /** Example static factory for an autonomous command. */
  // public static CommandBase exampleAuto(ExampleSubsystem subsystem) {
  //   return Commands.sequence(subsystem.exampleMethodCommand(), new ExampleCommand(subsystem));
  // } 

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}

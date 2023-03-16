// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.LowerJawConstants;
import frc.robot.Constants.pistonShooterConstants;
import frc.robot.Constants.upperJawConstants;
import frc.robot.subsystems.drivetrainSubsytem;
import frc.robot.subsystems.lowerJawSubsystem;
import frc.robot.subsystems.pistonShooterSubsystem;
import frc.robot.subsystems.upperJawSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html

public class shootAutoCommand extends SequentialCommandGroup {
  /** Creates a new simpleAutoCommand. */
  public shootAutoCommand(drivetrainSubsytem drive, lowerJawSubsystem lowerJaw, upperJawSubsystem upperJaw, pistonShooterSubsystem piston) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    drive.resetEncoders();
    addCommands(

      new ParallelRaceGroup(
        new lowerJawCommand(lowerJaw, LowerJawConstants.lowerJawShootHighPosition), 
        new upperJawCommand(upperJaw, 0, upperJawConstants.upperJawClampSpeed)
      ), 

      new ParallelRaceGroup(
        new delayCommand(1000), 
        new upperJawCommand(upperJaw, 0, upperJawConstants.upperJawReleaseSpeed)
      ),
      
      new pistonShooterCommand(piston, pistonShooterConstants.pistonMode, pistonShooterConstants.longDelay),

      new ParallelCommandGroup( 
        new autoDriveForwardCommand(drive, -28), 
        // new simpleDriveForwardCommand(drive, -28), 
        new upperJawCommand(upperJaw, 0, upperJawConstants.upperJawClampSpeed)
      )
    );
  }
}

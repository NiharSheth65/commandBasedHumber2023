// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.GearBoxConstants;
import frc.robot.Constants.UpperIntakeConstants;
import frc.robot.Constants.autoConstants;
import frc.robot.Constants.upperJawConstants;
import frc.robot.Constants.winchMechanismConstants;
import frc.robot.subsystems.drivetrainSubsytem;
import frc.robot.subsystems.gearboxSubsystem;
import frc.robot.subsystems.intakeSubsystem;
import frc.robot.subsystems.lowerJawSubsystem;
import frc.robot.subsystems.winchSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class autoDropCommand extends SequentialCommandGroup {
  /** Creates a new dropAutoCommand. */

  public autoDropCommand(drivetrainSubsytem drive, winchSubsystem winch, intakeSubsystem intake, gearboxSubsystem gear) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    drive.resetEncoders();
    addCommands(
      new ParallelRaceGroup(
        new gearshiftingCommand(gear, GearBoxConstants.speedMode), 
        new outtakeWinchCommand(winch, winchMechanismConstants.winchSpeed), 
        new defaultUpperIntakeCommand(intake, UpperIntakeConstants.upperIntakeDefaultSpeed)
      ), 
      
      new outtakeCommand(intake), 
      new intakeWinchCommand(winch, winchMechanismConstants.winchSpeed),
      new autoDriveForwardCommand(drive, autoConstants.autoConeCommunityClearDistance, autoConstants.clearingSpeed) 
    );

    SmartDashboard.putBoolean("intake switch", intake.intakeLimitSwitch()); 
  }
}

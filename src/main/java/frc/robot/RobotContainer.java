// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.GearBoxConstants;
import frc.robot.Constants.LowerJawConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.UpperIntakeConstants;
import frc.robot.Constants.lowerIntakeConstants;
import frc.robot.Constants.pistonShooterConstants;
import frc.robot.Constants.upperJawConstants;
import frc.robot.Constants.winchMechanismConstants;
// import frc.robot.commands.Autos;
import frc.robot.commands.autoDriveForwardCommand;
import frc.robot.commands.defaultDriveCommand;
import frc.robot.commands.defaultUpperIntakeCommand;
import frc.robot.commands.delayCommand;
import frc.robot.commands.gearshiftingCommand;
import frc.robot.commands.intakeWinchCommand;
import frc.robot.commands.lowerIntakeCommand;
import frc.robot.commands.lowerJawCommand;
import frc.robot.commands.outtakeWinchCommand;
import frc.robot.commands.pistonShooterCommand;
import frc.robot.commands.upperJawCommand;
import frc.robot.subsystems.drivetrainSubsytem;
import frc.robot.subsystems.gearboxSubsystem;
import frc.robot.subsystems.intakeSubsystem;
import frc.robot.subsystems.lowerIntakeSubsystem;
import frc.robot.subsystems.lowerJawSubsystem;
import frc.robot.subsystems.pistonShooterSubsystem;
import frc.robot.subsystems.upperJawSubsystem;
import frc.robot.subsystems.winchSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Constants.OperatorConstants;;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final drivetrainSubsytem DRIVE_SUBSYSTEM = new drivetrainSubsytem(); 
  private final intakeSubsystem INTAKE_SUBSYSTEM = new intakeSubsystem();
  private final winchSubsystem WINCH_SUBSYSTEM = new winchSubsystem(); 
  private final gearboxSubsystem GEARBOX_SUBSYSTEM = new gearboxSubsystem(); 
  private final pistonShooterSubsystem PISTON_SUBSYSTEM = new pistonShooterSubsystem(); 
  private final lowerJawSubsystem LOWERJAW_SUBSYSTEM = new lowerJawSubsystem(); 
  private final upperJawSubsystem UPPERJAW_SUBSYSTEM = new upperJawSubsystem(); 
  private final lowerIntakeSubsystem LOWERINTAKE_SUBSYSTEM = new lowerIntakeSubsystem(); 

  private final Joystick joystick = new Joystick(OperatorConstants.primaryControllerPort); 
  private final Joystick joystickSecondary = new Joystick(OperatorConstants.secondaryControllerPort); 

  private final CommandGenericHID controller = new CommandGenericHID(OperatorConstants.secondaryControllerPort); 

  private final JoystickButton BUTTON_A = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_A_PORT); 
  private final JoystickButton BUTTON_B = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_B_PORT); 
  private final JoystickButton BUTTON_X = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_X_PORT); 
  private final JoystickButton BUTTON_Y = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_Y_PORT); 
  private final JoystickButton BUTTON_RB = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_RB_PORT); 
  private final JoystickButton BUTTON_LB = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_LB_PORT); 

  private final JoystickButton BUTTON_RIGHT_JOYSTICK = new JoystickButton(joystick, OperatorConstants.BUTTON_RIGHT_JOYSTICK_PORT); 
  private final JoystickButton BUTTON_LEFT_JOYSTICK = new JoystickButton(joystick, OperatorConstants.BUTTON_LEFT_JOYSTICK_PORT); 

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    // configureBindings();
    configureButtonBindings();
    defaultCommands();
  }

  private void configureButtonBindings(){
    // BUTTON_RB.onTrue(new outtakeCommand(INTAKE_SUBSYSTEM)); 

    BUTTON_RB.onTrue(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, UpperIntakeConstants.upperOuttakeSpeed)); 
    BUTTON_RB.onFalse(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, UpperIntakeConstants.upperIntakeDefaultSpeed)); 

    BUTTON_LB.onTrue(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, UpperIntakeConstants.upperIntakeSpeed)); 
    BUTTON_LB.onFalse(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, UpperIntakeConstants.upperIntakeDefaultSpeed)); 
    
    BUTTON_RIGHT_JOYSTICK.onTrue(new gearshiftingCommand(GEARBOX_SUBSYSTEM, GearBoxConstants.torqueMode)); 
    BUTTON_LEFT_JOYSTICK.onTrue(new gearshiftingCommand(GEARBOX_SUBSYSTEM, GearBoxConstants.speedMode)); 
    

    BUTTON_A.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, LowerJawConstants.lowerJawHomePosition)); 
    
    BUTTON_B.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, LowerJawConstants.lowerJawGroundPickUpPosition)
    .andThen(new lowerIntakeCommand(LOWERINTAKE_SUBSYSTEM, lowerIntakeConstants.lowerIntakeSpeed))); 

    BUTTON_Y.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, LowerJawConstants.lowerJawShootHighPosition)
    .alongWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawClampSpeed))
    .andThen(new delayCommand(1000))
    .andThen(new pistonShooterCommand(PISTON_SUBSYSTEM, pistonShooterConstants.pistonMode, pistonShooterConstants.longDelay))
    .raceWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawReleaseSpeed))
    .andThen(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawClampSpeed))); 

    // BUTTON_Y.toggleOnFalse(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawClampSpeed)); 

    BUTTON_X.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, LowerJawConstants.lowerJawShootHighPosition)); 

    
    controller.axisGreaterThan(3, winchMechanismConstants.activeThreshold).toggleOnTrue(new outtakeWinchCommand(WINCH_SUBSYSTEM, winchMechanismConstants.winchSpeed)); 
    controller.axisGreaterThan(2, winchMechanismConstants.activeThreshold).toggleOnTrue(new intakeWinchCommand(WINCH_SUBSYSTEM, winchMechanismConstants.winchSpeed));
  }

  private void defaultCommands(){
    DRIVE_SUBSYSTEM.setDefaultCommand(new defaultDriveCommand(DRIVE_SUBSYSTEM, joystick));
    INTAKE_SUBSYSTEM.setDefaultCommand(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, UpperIntakeConstants.upperIntakeDefaultSpeed));
    UPPERJAW_SUBSYSTEM.setDefaultCommand(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawClampSpeed));
    GEARBOX_SUBSYSTEM.setDefaultCommand(new gearshiftingCommand(GEARBOX_SUBSYSTEM, GearBoxConstants.speedMode));  

    SmartDashboard.putBoolean("back limit switch", WINCH_SUBSYSTEM.backLimitSwitch()); 
    SmartDashboard.putBoolean("front limit switch", WINCH_SUBSYSTEM.frontLimitSwitch()); 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new SequentialCommandGroup(
      new lowerJawCommand(LOWERJAW_SUBSYSTEM, LowerJawConstants.lowerJawShootHighPosition),
      new delayCommand(1000), 
      
      new ParallelCommandGroup(
        new pistonShooterCommand(PISTON_SUBSYSTEM, pistonShooterConstants.pistonMode, pistonShooterConstants.longDelay), new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawReleaseSpeed)   
      ), 

      new autoDriveForwardCommand(DRIVE_SUBSYSTEM, -28)

    ); 
 

  }
}


 
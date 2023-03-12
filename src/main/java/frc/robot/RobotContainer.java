// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
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
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj.Timer;
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

  private final Joystick joystick = new Joystick(0); 
  private final CommandGenericHID controller = new CommandGenericHID(0); 

  private final JoystickButton BUTTON_A = new JoystickButton(joystick, 1); 
  private final JoystickButton BUTTON_B = new JoystickButton(joystick, 2); 
  private final JoystickButton BUTTON_X = new JoystickButton(joystick, 3); 
  private final JoystickButton BUTTON_Y = new JoystickButton(joystick, 4); 
  private final JoystickButton BUTTON_RB = new JoystickButton(joystick, 6); 
  private final JoystickButton BUTTON_LB = new JoystickButton(joystick, 5); 

  private final JoystickButton BUTTON_RIGHT_JOYSTICK = new JoystickButton(joystick, 10); 
  private final JoystickButton BUTTON_LEFT_JOYSTICK = new JoystickButton(joystick, 9); 

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    // configureBindings();
    configureButtonBindings();
    defaultCommands();
  }

  private void configureButtonBindings(){
    // BUTTON_RB.onTrue(new outtakeCommand(INTAKE_SUBSYSTEM)); 

    BUTTON_RB.onTrue(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, -0.5)); 

    BUTTON_RB.onFalse(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, 0)); 

    BUTTON_LB.onTrue(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, 0.5)); 
    BUTTON_LB.onFalse(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, 0)); 
    
    BUTTON_RIGHT_JOYSTICK.onTrue(new gearshiftingCommand(GEARBOX_SUBSYSTEM, 1)); 
    BUTTON_LEFT_JOYSTICK.onTrue(new gearshiftingCommand(GEARBOX_SUBSYSTEM, 2)); 
    
    // BUTTON_A.onTrue(new pistonShooterCommand(PISTON_SUBSYSTEM, 1)); 
    // BUTTON_B.onTrue(new pistonShooterCommand(PISTON_SUBSYSTEM, 2)); 

    BUTTON_A.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, 0)); 
    BUTTON_B.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, -28)
    .andThen(new lowerIntakeCommand(LOWERINTAKE_SUBSYSTEM, -0.5))); 

    BUTTON_Y.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, -18)
    // .alongWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, 0.1))
    .andThen(new delayCommand(1000))
    .andThen(new pistonShooterCommand(PISTON_SUBSYSTEM, 1, 1000))
    .alongWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, -0.1))); 


    BUTTON_X.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, -18)
    // .alongWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, 0.1))
    .andThen(new delayCommand(1000))
    .andThen(new pistonShooterCommand(PISTON_SUBSYSTEM, 1, 100))
    .alongWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, -0.1))); 

    // BUTTON_X.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, -19))
    // .alongWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, 0.1))
    // .andThen(new delayCommand(1000)
    // .andThen(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, -0.1))
    // .alongWith(new pistonShooterCommand(PISTON_SUBSYSTEM, 0, 250))); 

    // controller.axisGreaterThan(3, 0.25).onTrue(new outtakeWinchCommand(WINCH_SUBSYSTEM, 0.5)
    // .andThen(new intakeCommand(INTAKE_SUBSYSTEM, 0.75))
    // .andThen(new intakeWinchCommand(WINCH_SUBSYSTEM, 0.5)));

    // controller.axisGreaterThan(2, 0.25).toggleOnTrue(new toggleWinchCommand(WINCH_SUBSYSTEM, 0.5, 1)); 
    
    controller.axisGreaterThan(3, 0.25).toggleOnTrue(new outtakeWinchCommand(WINCH_SUBSYSTEM, 0.5)); 
    controller.axisGreaterThan(2, 0.25).toggleOnTrue(new intakeWinchCommand(WINCH_SUBSYSTEM, 0.5)); 
    
  }

  private void defaultCommands(){
    DRIVE_SUBSYSTEM.setDefaultCommand(new defaultDriveCommand(DRIVE_SUBSYSTEM, joystick));
    INTAKE_SUBSYSTEM.setDefaultCommand(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, 0.1));
    UPPERJAW_SUBSYSTEM.setDefaultCommand(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, 0.1));
    GEARBOX_SUBSYSTEM.setDefaultCommand(new gearshiftingCommand(GEARBOX_SUBSYSTEM, 2));  
    
    // INTAKE_SUBSYSTEM.setDefaultCommand(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, 0.1));
    if(joystick.getRawAxis(2) > 0.5){
      new outtakeWinchCommand(WINCH_SUBSYSTEM, 0.5); 
    }

    else if(joystick.getRawAxis(3) > 0.5){
      new intakeWinchCommand(WINCH_SUBSYSTEM, 0.5); 
    }

    SmartDashboard.putBoolean("back limit switch", WINCH_SUBSYSTEM.backLimitSwitch()); 
    SmartDashboard.putBoolean("front limit switch", WINCH_SUBSYSTEM.frontLimitSwitch()); 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null; 
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
  }
}

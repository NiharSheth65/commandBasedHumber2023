// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.defaultDriveCommand;
import frc.robot.commands.gearshiftingCommand;
import frc.robot.commands.intakeCommand;
import frc.robot.commands.winchCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.drivetrainSubsytem;
import frc.robot.subsystems.gearboxSubsystem;
import frc.robot.subsystems.intakeSubsystem;
import frc.robot.subsystems.winchSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final drivetrainSubsytem DRIVE_SUBSYSTEM = new drivetrainSubsytem(); 
  private final intakeSubsystem INTAKE_SUBSYSTEM = new intakeSubsystem();
  private final winchSubsystem WINCH_SUBSYSTEM = new winchSubsystem(); 
  private final gearboxSubsystem GEARBOX_SUBSYSTEM = new gearboxSubsystem(); 

  private final Joystick joystick = new Joystick(0); 

  private final JoystickButton BUTTON_A = new JoystickButton(joystick, 1); 
  private final JoystickButton BUTTON_B = new JoystickButton(joystick, 2); 
  private final JoystickButton BUTTON_x = new JoystickButton(joystick, 3); 
  private final JoystickButton BUTTON_Y = new JoystickButton(joystick, 4); 
  private final JoystickButton BUTTON_RB = new JoystickButton(joystick, 5); 
  private final JoystickButton BUTTON_LB = new JoystickButton(joystick, 6); 

  private final JoystickButton BUTTON_RIGHT_JOYSTICK = new JoystickButton(joystick, 10); 
  private final JoystickButton BUTTON_LEFT_JOYSTICK = new JoystickButton(joystick, 9); 


  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    // configureBindings();
    configureButtonBindings();
    defaultCommands();
  }

  private void configureButtonBindings(){
    // BUTTON_RB.onTrue(new outtakeCommand(INTAKE_SUBSYSTEM)); 
    BUTTON_RB.onTrue(new intakeCommand(INTAKE_SUBSYSTEM, 0.5));
    BUTTON_RB.onFalse(new intakeCommand(INTAKE_SUBSYSTEM, 0));  
    
    BUTTON_LB.onTrue(new intakeCommand(INTAKE_SUBSYSTEM, -0.5)); 
    BUTTON_LB.onFalse(new intakeCommand(INTAKE_SUBSYSTEM, 0));  
  
    // BUTTON_A.onTrue(new winchCommand(WINCH_SUBSYSTEM, 0.5, 1)); 
    // BUTTON_B.onTrue(new winchCommand(WINCH_SUBSYSTEM, -0.5, 2)); 

    BUTTON_RIGHT_JOYSTICK.onTrue(new gearshiftingCommand(GEARBOX_SUBSYSTEM, 1)); 
    BUTTON_LEFT_JOYSTICK.onTrue(new gearshiftingCommand(GEARBOX_SUBSYSTEM, 2)); 

    if(joystick.getRawAxis(0) > 0.5){
      new winchCommand(WINCH_SUBSYSTEM, 0.5, 1); 
    }

    else if(joystick.getRawAxis(0) > 0.5){
      new winchCommand(WINCH_SUBSYSTEM, -0.5, 2); 
    }

    else{
      new winchCommand(WINCH_SUBSYSTEM, 0, 0);
    }

  }

  private void defaultCommands(){
    DRIVE_SUBSYSTEM.setDefaultCommand(new defaultDriveCommand(DRIVE_SUBSYSTEM, joystick));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}

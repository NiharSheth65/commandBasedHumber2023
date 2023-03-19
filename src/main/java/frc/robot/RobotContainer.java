// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.DriveTrainConstants;
import frc.robot.Constants.GearBoxConstants;
import frc.robot.Constants.LowerJawConstants;
import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.UpperIntakeConstants;
import frc.robot.Constants.lowerIntakeConstants;
import frc.robot.Constants.pistonShooterConstants;
import frc.robot.Constants.upperJawConstants;
import frc.robot.Constants.winchMechanismConstants;
// import frc.robot.commands.Autos;
import frc.robot.commands.defaultDriveCommand;
import frc.robot.commands.defaultUpperIntakeCommand;
import frc.robot.commands.delayCommand;
import frc.robot.commands.autoDropAndDockCommand;
import frc.robot.commands.autoDropCommand;
import frc.robot.commands.autoShootAndDockCommand;
import frc.robot.commands.gearshiftingCommand;
import frc.robot.commands.intakeWinchCommand;
import frc.robot.commands.ledCommand;
import frc.robot.commands.lowerIntakeCommand;
import frc.robot.commands.lowerJawCommand;
import frc.robot.commands.outtakeWinchCommand;
import frc.robot.commands.pistonShooterCommand;
import frc.robot.commands.autoShootCommand;
import frc.robot.commands.upperJawCommand;
import frc.robot.subsystems.drivetrainSubsytem;
import frc.robot.subsystems.gearboxSubsystem;
import frc.robot.subsystems.intakeSubsystem;
import frc.robot.subsystems.ledSubsystem;
import frc.robot.subsystems.lowerIntakeSubsystem;
import frc.robot.subsystems.lowerJawSubsystem;
import frc.robot.subsystems.pistonShooterSubsystem;
import frc.robot.subsystems.upperJawSubsystem;
import frc.robot.subsystems.winchSubsystem;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandGenericHID;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.OperatorConstants;

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
  private final ledSubsystem LED_SUBSYSTEM = new ledSubsystem(); 

  private final Joystick joystick = new Joystick(OperatorConstants.primaryControllerPort); 
  private final Joystick joystickSecondary = new Joystick(OperatorConstants.secondaryControllerPort); 

  private final CommandGenericHID controller = new CommandGenericHID(OperatorConstants.secondaryControllerPort); 

  private final JoystickButton BUTTON_A = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_A_PORT); 
  private final JoystickButton BUTTON_B = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_B_PORT); 
  private final JoystickButton BUTTON_X = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_X_PORT); 
  private final JoystickButton BUTTON_Y = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_Y_PORT); 
  private final JoystickButton BUTTON_START = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_START); 
  
  private final JoystickButton BUTTON_RB_PRIMARY = new JoystickButton(joystick, OperatorConstants.BUTTON_RB_PORT); 
  private final JoystickButton BUTTON_LB_PRIMARY = new JoystickButton(joystick, OperatorConstants.BUTTON_LB_PORT); 

  private final JoystickButton BUTTON_RB = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_RB_PORT); 
  private final JoystickButton BUTTON_LB = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_LB_PORT); 

  private final JoystickButton BUTTON_RIGHT_JOYSTICK = new JoystickButton(joystick, OperatorConstants.BUTTON_RIGHT_JOYSTICK_PORT); 
  private final JoystickButton BUTTON_LEFT_JOYSTICK = new JoystickButton(joystick, OperatorConstants.BUTTON_LEFT_JOYSTICK_PORT); 

  private final JoystickButton BUTTON_LEFT_JOYSTICKSECONDARY = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_LEFT_JOYSTICK_PORT); 
  private final JoystickButton BUTTON_RIGHT_JOYSTICKSECONDARY = new JoystickButton(joystickSecondary, OperatorConstants.BUTTON_RIGHT_JOYSTICK_PORT); 
 
  
  private final Command m_shootCommand = new autoShootCommand(DRIVE_SUBSYSTEM, LOWERJAW_SUBSYSTEM, UPPERJAW_SUBSYSTEM, PISTON_SUBSYSTEM, GEARBOX_SUBSYSTEM, LOWERINTAKE_SUBSYSTEM  );
  private final Command m_shootAndDockCommand = new autoShootAndDockCommand(DRIVE_SUBSYSTEM, LOWERJAW_SUBSYSTEM, UPPERJAW_SUBSYSTEM, PISTON_SUBSYSTEM, GEARBOX_SUBSYSTEM); 
  private final Command m_dropCommand = new autoDropCommand(DRIVE_SUBSYSTEM, WINCH_SUBSYSTEM, INTAKE_SUBSYSTEM, GEARBOX_SUBSYSTEM); 
  private final Command m_dropAndDockCommand = new autoDropAndDockCommand(DRIVE_SUBSYSTEM, WINCH_SUBSYSTEM, INTAKE_SUBSYSTEM, GEARBOX_SUBSYSTEM); 

  double speedFactor; 
  double turnFactor; 
  int colourMode; 

  SendableChooser<Command> m_autoChooser = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    // configureBindings();
    CameraServer.startAutomaticCapture();         

    // A chooser for autonomous commands    
    m_autoChooser.setDefaultOption("Cube and Clear Auto", m_shootCommand);
    m_autoChooser.addOption("Cube and Dock Auto", m_shootAndDockCommand); 
    m_autoChooser.addOption("Cone and Clear Auto", m_dropCommand);   
    m_autoChooser.addOption("Cone and Dock Auto", m_dropAndDockCommand);

    // Put the chooser on the dashboard
    SmartDashboard.putData(m_autoChooser);
    
    configureButtonBindings();
    defaultCommands();
  }

  private void configureButtonBindings(){
    // BUTTON_RB.onTrue(new outtakeCommand(INTAKE_SUBSYSTEM)); 

    BUTTON_RB.onTrue(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, UpperIntakeConstants.upperOuttakeSpeed)); 
    BUTTON_RB.onFalse(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, UpperIntakeConstants.upperIntakeDefaultSpeed)); 

    BUTTON_LB.onTrue(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, UpperIntakeConstants.upperIntakeSpeed)); 
    BUTTON_LB.onFalse(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, UpperIntakeConstants.upperIntakeDefaultSpeed)); 
    
    BUTTON_RB_PRIMARY.onTrue(new gearshiftingCommand(GEARBOX_SUBSYSTEM, GearBoxConstants.torqueMode)); 
    BUTTON_LB_PRIMARY.onTrue(new gearshiftingCommand(GEARBOX_SUBSYSTEM, GearBoxConstants.speedMode)); 
    
    BUTTON_LEFT_JOYSTICKSECONDARY.onTrue(new pistonShooterCommand(PISTON_SUBSYSTEM, 4, 0)); 
    
    BUTTON_RIGHT_JOYSTICKSECONDARY.onTrue(new lowerIntakeCommand(LOWERINTAKE_SUBSYSTEM, -1*(lowerIntakeConstants.lowerIntakeSpeed))); 
    BUTTON_RIGHT_JOYSTICKSECONDARY.onFalse(new lowerIntakeCommand(LOWERINTAKE_SUBSYSTEM, 0)); 
    
    // home position 
    BUTTON_START.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, LowerJawConstants.lowerJawHomePosition)
    .alongWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, 0))); 
    
       // shoot mid position 
    // BUTTON_A.toggleOnTrue(
    //  new lowerJawCommand(LOWERJAW_SUBSYSTEM, LowerJawConstants.lowerJawShootingMidPosition)
    // .andThen(new delayCommand(1000))
    // .andThen(new pistonShooterCommand(PISTON_SUBSYSTEM, pistonShooterConstants.pistonMode, pistonShooterConstants.shortDelay))
    // .raceWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawReleaseSpeed))
    // .andThen(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawClampSpeed)));

     BUTTON_A.toggleOnTrue(
     new lowerJawCommand(LOWERJAW_SUBSYSTEM, LowerJawConstants.lowerJawShootHighPosition)
    .alongWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawReleaseSpeed))); 
    // .andThen(new pistonShooterCommand(PISTON_SUBSYSTEM, pistonShooterConstants.pistonMode, pistonShooterConstants.shortDelay))
    // .raceWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawReleaseSpeed))
    // .andThen(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawClampSpeed)));

    // ground pick up 
    BUTTON_B.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, LowerJawConstants.lowerJawGroundPickUpPosition)
    .raceWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawClampSpeed))
    .andThen(new lowerIntakeCommand(LOWERINTAKE_SUBSYSTEM, lowerIntakeConstants.lowerIntakeSpeed))); 

    // shoot position and shoot high
    BUTTON_Y.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, LowerJawConstants.lowerJawShootHighPosition)
    .andThen(new delayCommand(1000))
    .andThen(new pistonShooterCommand(PISTON_SUBSYSTEM, pistonShooterConstants.pistonMode, pistonShooterConstants.longDelay))
    .raceWith(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawReleaseSpeed))
    .andThen(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawClampSpeed)));

    // shoot but hold position 
    BUTTON_X.toggleOnTrue(new lowerJawCommand(LOWERJAW_SUBSYSTEM, LowerJawConstants.lowerJawCarryPosition)
    .raceWith(new upperJawCommand(UPPERJAW_SUBSYSTEM,  0, upperJawConstants.upperJawClampSpeed))); 

    controller.axisGreaterThan(3, winchMechanismConstants.activeThreshold).toggleOnTrue(new outtakeWinchCommand(WINCH_SUBSYSTEM, winchMechanismConstants.winchSpeed)); 
    controller.axisGreaterThan(2, winchMechanismConstants.activeThreshold).toggleOnTrue(new intakeWinchCommand(WINCH_SUBSYSTEM, winchMechanismConstants.winchSpeed));
 
}


  private void defaultCommands(){
    DRIVE_SUBSYSTEM.setDefaultCommand(new defaultDriveCommand(DRIVE_SUBSYSTEM, joystick, DriveTrainConstants.defaultDriveSpeed, DriveTrainConstants.defaultTurnSpeed));
    INTAKE_SUBSYSTEM.setDefaultCommand(new defaultUpperIntakeCommand(INTAKE_SUBSYSTEM, UpperIntakeConstants.upperIntakeDefaultSpeed));
    // UPPERJAW_SUBSYSTEM.setDefaultCommand(new upperJawCommand(UPPERJAW_SUBSYSTEM, 0, upperJawConstants.upperJawClampSpeed));
    GEARBOX_SUBSYSTEM.setDefaultCommand(new gearshiftingCommand(GEARBOX_SUBSYSTEM, GearBoxConstants.speedMode));  
 
    if(INTAKE_SUBSYSTEM.intakeLimitSwitch() == false){
      colourMode = 3; 
    }


    LED_SUBSYSTEM.setDefaultCommand(new ledCommand(LED_SUBSYSTEM, 1));

      // boost for driving straigts 
    SmartDashboard.putNumber("spped facotr", speedFactor);   

    SmartDashboard.putBoolean("intake limit switch", INTAKE_SUBSYSTEM.intakeLimitSwitch()); 
    SmartDashboard.putBoolean("back limit switch", WINCH_SUBSYSTEM.backLimitSwitch()); 
    SmartDashboard.putBoolean("front limit switch", WINCH_SUBSYSTEM.frontLimitSwitch()); 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() { 
    autonomousInit();
    return m_autoChooser.getSelected(); 
  }

  public void autonomousInit(){
    DRIVE_SUBSYSTEM.resetEncoders();
    DRIVE_SUBSYSTEM.resetGyro();
    PISTON_SUBSYSTEM.pistonRetract();
    // GEARBOX_SUBSYSTEM.speedMode();
  }
}


 
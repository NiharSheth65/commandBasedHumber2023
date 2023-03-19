// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int primaryControllerPort = 0;
    public static final int secondaryControllerPort = 1; 

    // button ports
    public static final int BUTTON_A_PORT = 1;
    public static final int BUTTON_B_PORT = 2;
    public static final int BUTTON_X_PORT = 3;
    public static final int BUTTON_Y_PORT = 4;
    public static final int BUTTON_RB_PORT = 6;
    public static final int BUTTON_LB_PORT = 5;

    public static final int BUTTON_START = 8;  
    public static final int BUTTON_RIGHT_JOYSTICK_PORT = 10; 
    public static final int BUTTON_LEFT_JOYSTICK_PORT = 9;    
  }

  public static final class autoConstants{
    public static final double autoConeCommunityClearDistance = -35; 
    public static final double autoConeDockingDistance = -39; 
    public static final double autoCubeCommunityClearDistance = -35; 
    public static final double autoCubeDockingDistance = -40.75; 

    public static final double clearingSpeed = 1000; 
    public static final double dockingSpeed = 500; 
  }

  public static final class DriveTrainConstants{
     public static final int leftMotorBackPort = 1;  
     public static final int leftMotorFrontPort = 2;  
     public static final int rightMotorBackPort = 3;  
     public static final int rightMotorFrontPort = 4; 
    
     public static final double defaultDriveSpeed = 0.5;
     public static final double boostedDriveSpeed = 0.75; 

     public static final double defaultTurnSpeed = 0.6;
     public static final double boostedTurnSpeed = 0.85; 
  
  }

  public static final class UpperIntakeConstants{
     public static final double upperIntakeSpeed = 0.5; 
     public static final double upperOuttakeSpeed = -0.3;
     public static final double upperIntakeDefaultSpeed = 0.125; 

     public static final int rightIntakeMotorPort = 1; 
     public static final int leftIntakeMotorPort = 2; 
     public static final int intakeLimitSwitchPort = 2; 
  }

  public static final class GearBoxConstants{
    public static final int torqueMode = 1; 
    public static final int speedMode = 2; 

    public static final int forwardChannel = 2; 
    public static final int reverseChannel = 3; 
  }

  public static final class LowerJawConstants{
    public static final double lowerJawHomePosition = 0; 
    public static final double lowerJawShootHighPosition = -17.375; 
    public static final double lowerJawShootingMidPosition = -22; 
    public static final double lowerJawGroundPickUpPosition = -28; 
    public static final double lowerJawCarryPosition = -12; 

    public static final int lowerJawMotorPort = 5; 

    public static final double lowerJawKP = 0.7; 
    public static final double lowerJawKI = 0.05; 
    public static final double lowerJawKD = 0; 

  }

  public static final class lowerIntakeConstants{
    public static final double lowerIntakeSpeed = -0.6; 
    public static final int lowerIntakePort = 0; 
  }

  public static final class pistonShooterConstants{
    public static final int shortDelay = 100; 
    public static final int longDelay = 1000; 
    public static final int pistonMode = 1; 

    public static final int pistonforwardChannel = 0; 
    public static final int pistonreverseChannel = 1; 
  }

  public static final class winchMechanismConstants{
    public static final double winchSpeed = 1.00;
    public static final double activeThreshold = 0.25; 
  
    public static final int winchMotorPort = 4; 
    public static final int frontLimitSwitchPort = 1; 
    public static final int backLimitSwitchPort = 0; 

  }

  public static final class upperJawConstants{
    public static final double upperJawClampSpeed = 0.2; 
    public static final double upperJawReleaseSpeed = -0.2; 

    public static final int upperJawMotorPort = 6; 
  }

}

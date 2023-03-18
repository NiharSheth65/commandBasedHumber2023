// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ledSubsystem extends SubsystemBase {
  /** Creates a new ledSubsystem. */
  AddressableLED m_led; 
  AddressableLEDBuffer m_LedBuffer; 

  public ledSubsystem() {
    m_led = new AddressableLED(5); 

    m_LedBuffer = new AddressableLEDBuffer(40); 
    m_led.setLength(m_LedBuffer.getLength());

    m_led.setData(m_LedBuffer);
    m_led.start();
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setLED(int R, int G, int B){
    for(var i = 0; i < m_LedBuffer.getLength(); i++){
      m_LedBuffer.setRGB(i, R, G, B);
    } 

    m_led.setData(m_LedBuffer);
  }
}

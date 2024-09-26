// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Backend.Indexer;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkLowLevel.PeriodicFrame;

import frc.Backend.Constants;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatchResult;

public class Indexer extends SubsystemBase {
  /** Creates a new Indexer. */
  static CANSparkMax IndexerMotor = new CANSparkMax(Constants.INDEXER_MOTOR, MotorType.kBrushless);
  static final AddressableLED LED = new AddressableLED(9);
  AddressableLEDBuffer ledBuffer = new AddressableLEDBuffer(Constants.INDEXER_LED_STRIP_LENGTH);
  Color kGreen1 = new Color(20, 150,  0);

  {
    IndexerMotor.setIdleMode(IdleMode.kCoast);
    { //CAN Status Frames
      IndexerMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 400);
      IndexerMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 400);
      IndexerMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 400);
      IndexerMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 400);
      IndexerMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 400);
      IndexerMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 400);
      IndexerMotor.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 400);
    }
    
    LED.setLength(ledBuffer.getLength());
    LED.setData(ledBuffer);
    LED.start();
    for(int i = 0; i < ledBuffer.getLength(); i++){
      ledBuffer.setLED(i, kGreen1);
    }
    LED.setData(ledBuffer);
  }

  public static void indexIn(){
    IndexerMotor.set(-Constants.INDEXER_SPEED);
  }
  public static void indexOut(){
    IndexerMotor.set(Constants.INDEXER_SPEED_OUT);
  }

  public static void off(){
    IndexerMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

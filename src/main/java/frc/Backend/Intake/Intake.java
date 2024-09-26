// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Backend.Intake;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkLowLevel.PeriodicFrame;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.Backend.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  static CANSparkFlex IntakeMotorBottom = new CANSparkFlex(Constants.INTAKE_MOTOR_BOTTOM, MotorType.kBrushless);
  static CANSparkMax IntakeMotorTop = new CANSparkMax(Constants.INTAKE_MOTOR_TOP, MotorType.kBrushless);
  {
    IntakeMotorBottom.setIdleMode(IdleMode.kCoast);
    IntakeMotorTop.setIdleMode(IdleMode.kCoast);  
     //CAN Status Frames
    IntakeMotorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 400);
    IntakeMotorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 400);
    IntakeMotorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 400);
    IntakeMotorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 400);
    IntakeMotorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 400);
    IntakeMotorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 400);
    IntakeMotorBottom.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 400);
    IntakeMotorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 400);
    IntakeMotorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 400);
    IntakeMotorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 400);
    IntakeMotorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 400);
    IntakeMotorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 400);
    IntakeMotorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 400);
    IntakeMotorTop.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 400);
  }

  public static void intakeWithTrigger(double speed) {//make dependent on trigger
    IntakeMotorBottom.set(speed);
    IntakeMotorTop.set(speed);
  }

  public static void intake(){
    IntakeMotorBottom.set(Constants.INTAKE_OUTTAKE_SPEED);
    IntakeMotorTop.set(Constants.INTAKE_OUTTAKE_SPEED);
  }

  public static void outtake() {
    IntakeMotorBottom.set(-Constants.INTAKE_OUTTAKE_SPEED);
    IntakeMotorTop.set(-Constants.INTAKE_OUTTAKE_SPEED);
  }

  public static void off(){
    IntakeMotorBottom.set(0);
    IntakeMotorTop.set(0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

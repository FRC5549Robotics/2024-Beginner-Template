// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Backend.Pivot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.Backend.Constants;

  
public class Pivot extends SubsystemBase {
  /** Creates a new Pivot. */
  public enum PivotTarget{
    Retracted,
    Intake,
    Amp
  }
  CANSparkMax PivotRightMotor = new CANSparkMax(Constants.PIVOT_MOTOR_RIGHT, MotorType.kBrushless);
  CANSparkMax PivotLeftMotor = new CANSparkMax(Constants.PIVOT_MOTOR_LEFT, MotorType.kBrushless);
  PIDController controllerRight = new PIDController(0.006, 0.0, 0.00);
  PIDController controllerLeft = new PIDController(0.006, 0.0, 0.00);
  DutyCycleEncoder RightThroughbore = new DutyCycleEncoder(Constants.PIVOT_ENCODER_RIGHT);
  DutyCycleEncoder LeftThroughbore = new DutyCycleEncoder(Constants.PIVOT_ENCODER_LEFT);
  {
    RightThroughbore.setPositionOffset(Constants.PIVOT_OFFSET_RIGHT);
    LeftThroughbore.setPositionOffset(Constants.PIVOT_OFFSET_LEFT);
    RightThroughbore.setDistancePerRotation(360);
    LeftThroughbore.setDistancePerRotation(360);
    PivotLeftMotor.setIdleMode(IdleMode.kBrake);
    PivotRightMotor.setIdleMode(IdleMode.kBrake);
  }

  public void pivot(double speed){
    PivotRightMotor.set(speed*Constants.PIVOT_SCALING_FACTOR);
    PivotLeftMotor.set(-speed*Constants.PIVOT_SCALING_FACTOR);
  }
  
  public void pivotToPosition( Pivot.PivotTarget target) {
    double leftSetpoint = 0; 
    double rightSetpoint = 0;
    if(target == PivotTarget.Intake){
      leftSetpoint = Constants.PIVOT_LEFT_INTAKE_SETPOINT;
      rightSetpoint = Constants.PIVOT_RIGHT_INTAKE_SETPOINT;
    }
    else if(target == PivotTarget.Retracted){
      leftSetpoint = Constants.PIVOT_LEFT_RETRACTED_SETPOINT;
      rightSetpoint = Constants.PIVOT_RIGHT_RETRACTED_SETPOINT;
    }
    else if(target == PivotTarget.Amp) {
      leftSetpoint = Constants.PIVOT_LEFT_AMP_SETPOINT;
      rightSetpoint = Constants.PIVOT_RIGHT_AMP_SETPOINT;
    }
    PivotRightMotor.set(-controllerRight.calculate(getRightPosition(), rightSetpoint));
    PivotLeftMotor.set(-controllerLeft.calculate(getLeftPosition(), leftSetpoint));
    SmartDashboard.putNumber("rs", rightSetpoint);
    SmartDashboard.putNumber("ls", leftSetpoint);
  }
  private double getRightPosition(){
    return RightThroughbore.getDistance();
  }
  private double getLeftPosition(){
    return LeftThroughbore.getDistance();
  }

  public void off(){
    PivotRightMotor.set(0);
    PivotLeftMotor.set(0);
  }

  @Override
  public void periodic() {
  }
}

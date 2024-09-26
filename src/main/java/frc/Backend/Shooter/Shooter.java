// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Backend.Shooter;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import com.revrobotics.CANSparkFlex;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
//import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkLowLevel.PeriodicFrame;

import frc.Backend.Constants;

public class Shooter extends SubsystemBase {
	private static Shooter instance = null;
	/** Creates a new Shooter. */
	static CANSparkFlex ShooterRight = new CANSparkFlex(Constants.SHOOTER_RIGHT_MOTOR, MotorType.kBrushless);
	static CANSparkFlex ShooterLeft = new CANSparkFlex(Constants.SHOOTER_LEFT_MOTOR, MotorType.kBrushless);
		
	{//CAN Status Frames
		ShooterRight.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 10);
		ShooterRight.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 20);
		ShooterRight.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 20);
		ShooterRight.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 50);
		ShooterRight.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 20);
		ShooterRight.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 200);
		ShooterRight.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 200);
		ShooterLeft.setPeriodicFramePeriod(PeriodicFrame.kStatus0, 10);
		ShooterLeft.setPeriodicFramePeriod(PeriodicFrame.kStatus1, 20);
		ShooterLeft.setPeriodicFramePeriod(PeriodicFrame.kStatus2, 20);
		ShooterLeft.setPeriodicFramePeriod(PeriodicFrame.kStatus3, 50);
		ShooterLeft.setPeriodicFramePeriod(PeriodicFrame.kStatus4, 20);
		ShooterLeft.setPeriodicFramePeriod(PeriodicFrame.kStatus5, 200);
		ShooterLeft.setPeriodicFramePeriod(PeriodicFrame.kStatus6, 200);
	}

	public static void on(double setPoint) {
		ShooterRight.set(-setPoint);
		ShooterLeft.set(setPoint);
	}

	public static void shootAmp(){
		ShooterRight.set(-Constants.SHOOTER_SET_SPEED/3.4);
		ShooterLeft.set(Constants.SHOOTER_SET_SPEED/3.4);
	}

	public static void shooterIn(){
		ShooterRight.set(Constants.SHOOTER_SET_SPEED);
		ShooterLeft.set(-Constants.SHOOTER_SET_SPEED);
	}

	public static void off(){
		ShooterRight.set(0);
    	ShooterLeft.set(0);
	}
	
	@Override
	public void periodic(){
	}
}
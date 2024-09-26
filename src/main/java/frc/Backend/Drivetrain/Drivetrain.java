// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.Backend.Drivetrain;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import frc.Backend.Constants;

/** Add your docs here. */
public class Drivetrain {

    private static final AHRS m_ahrs = new AHRS();
    private static DrivetrainSubsystem DrivetrainSubsytem = new DrivetrainSubsystem(m_ahrs);
    private final static double SCALING_FACTOR = 0.3;
    public static void drive(double leftinput, double rightinput) {
        
        double xDot = Math.min(leftinput, rightinput)*SCALING_FACTOR*Constants.kMaxTranslationalVelocity;
        double yDot = 0;
        double thetaDot = (leftinput-rightinput)*SCALING_FACTOR*Constants.kMaxRotationalVelocity;

        boolean fieldRelative = false;
        if(Math.abs(xDot)<=0.07*0.07*Constants.kMaxTranslationalVelocity){
            xDot = 0;
        }
        else{
            if(xDot > 0){
            xDot -= (0.07*0.07);
            }
            else{
            xDot += (0.07*0.07);
            }
            xDot *= 1/(1-(0.07*0.07));
        }
        if(Math.abs(yDot)<=0.07*0.07*Constants.kMaxTranslationalVelocity){
            yDot = 0;
        }
        else{
            if(yDot > 0){
            yDot -= (0.07*0.07);
            }
            else{
            yDot += (0.07*0.07);
            }
            yDot *= 1/(1-(0.07*0.07));
        }
        if(Math.abs(thetaDot)<=0.07*0.07*Constants.kMaxRotationalVelocity){
            thetaDot = 0;
        }
        else{
            if(thetaDot > 0){
            thetaDot -= 0.07*0.07;
            }
            else{
            thetaDot += 0.07*0.07;
            }
            thetaDot *= 1/(1-(0.07*0.07));
        }

        ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(xDot, yDot, thetaDot, new Rotation2d(0));

        DrivetrainSubsytem.drive(chassisSpeeds, true);
    }

}

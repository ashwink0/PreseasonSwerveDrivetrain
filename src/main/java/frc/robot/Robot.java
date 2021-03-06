/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2020 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.IntakeManualPID;
import frc.robot.commands.SwerveManual;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import harkerrobolib.util.Conversions.SpeedUnit;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    Drivetrain.getInstance().getTopLeft().getRotationMotor().setSelectedSensorPosition((Drivetrain.getInstance().getTopLeft().getRotationMotor().getSensorCollection().getPulseWidthRiseToFallUs() - Drivetrain.TL_OFFSET));
    Drivetrain.getInstance().getTopRight().getRotationMotor().setSelectedSensorPosition((Drivetrain.getInstance().getTopRight().getRotationMotor().getSensorCollection().getPulseWidthRiseToFallUs() - Drivetrain.TR_OFFSET));
    Drivetrain.getInstance().getBottomLeft().getRotationMotor().setSelectedSensorPosition((Drivetrain.getInstance().getBottomLeft().getRotationMotor().getSensorCollection().getPulseWidthRiseToFallUs() - Drivetrain.BL_OFFSET));
    Drivetrain.getInstance().getBottomRight().getRotationMotor().setSelectedSensorPosition((Drivetrain.getInstance().getBottomRight().getRotationMotor().getSensorCollection().getPulseWidthRiseToFallUs() - Drivetrain.BR_OFFSET));
    CommandScheduler.getInstance().setDefaultCommand(Drivetrain.getInstance(), new SwerveManual());
    CommandScheduler.getInstance().setDefaultCommand(Intake.getInstance(), new IntakeManualPID());

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("angle pos tl pulse width", Drivetrain.getInstance().getTopLeft().getRotationMotor().getSensorCollection().getPulseWidthRiseToFallUs());
    SmartDashboard.putNumber("angle pos tr pulse width", Drivetrain.getInstance().getTopRight().getRotationMotor().getSensorCollection().getPulseWidthRiseToFallUs());
    SmartDashboard.putNumber("angle pos bl pulse width", Drivetrain.getInstance().getBottomLeft().getRotationMotor().getSensorCollection().getPulseWidthRiseToFallUs());
    SmartDashboard.putNumber("angle pos br pulse width", Drivetrain.getInstance().getBottomRight().getRotationMotor().getSensorCollection().getPulseWidthRiseToFallUs());

    
    SmartDashboard.putNumber("angle pos tl", Drivetrain.getInstance().getTopLeft().getRotationMotor().getSelectedSensorPosition());
    SmartDashboard.putNumber("angle pos tr", Drivetrain.getInstance().getTopRight().getRotationMotor().getSelectedSensorPosition());
    SmartDashboard.putNumber("angle pos bl", Drivetrain.getInstance().getBottomLeft().getRotationMotor().getSelectedSensorPosition());
    SmartDashboard.putNumber("angle pos br", Drivetrain.getInstance().getBottomRight().getRotationMotor().getSelectedSensorPosition());

    SmartDashboard.putNumber("Pigeon Heading", Drivetrain.getInstance().getPigeon().getFusedHeading());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    // m_autoSelected = m_chooser.getSelected();
    // // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    // System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    // switch (m_autoSelected) {
    //   case kCustomAuto:
    //     // Put custom auto code here
    //     break;
    //   case kDefaultAuto:
    //   default:
    //     // Put default auto code here
    //     break;
    // }
  }

  /**
   * This function is called once when teleop is enabled.
   */
  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once when the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  /**
   * This function is called periodically when disabled.
   */
  @Override
  public void disabledPeriodic() {
  }

  /**
   * This function is called once when test mode is enabled.
   */
  @Override
  public void testInit() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    CommandScheduler.getInstance().run();
  }
}

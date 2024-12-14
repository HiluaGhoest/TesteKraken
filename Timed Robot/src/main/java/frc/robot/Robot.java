package frc.robot;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.*;
import com.ctre.phoenix6.signals.*;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimesliceRobot;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimesliceRobot {

  private final Joystick joystick = new Joystick(0);
  private final TalonFX motor = new TalonFX(1);
  MotionMagicVoltage m_motmag = new MotionMagicVoltage(0);
  public static final double kS = 0.28;
  public static final double kP = 1.0;
  public static final double kI = 0.2;
  public static final double kD = 0.0;
  public static final double MagicAcceleration = 600;
  public static final double MagicCruiseVelocity = 240;
  public static final double MagicJerk = 1600;
  
  public Robot() {

    super(0.005, 0.01);

    LiveWindow.disableAllTelemetry();
  }

  @Override
  public void robotInit() {
    // SmartDashboard Init
    SmartDashboard.putNumber("kS", kS);
    SmartDashboard.putNumber("kP", kP);
    SmartDashboard.putNumber("kI", kI);
    SmartDashboard.putNumber("kD", kD);
    SmartDashboard.putNumber("Acceleration", MagicAcceleration); // rotations per second squared
    SmartDashboard.putNumber("Cruise Velocity", MagicCruiseVelocity); // rotations per second
    SmartDashboard.putNumber("Jerk", MagicJerk); // rotations per second cubed
    SmartDashboard.putNumber("Target Position", 1); // default position

    motor.setPosition(0.0); // Reset encoder to zero
  }
  
  @Override
  public void teleopInit() {
    updateMotorConfigurationFromDashboard();
  }
  
  @Override
  public void teleopPeriodic() {
    updateMotorConfigurationFromDashboard();
    double targetPosition = SmartDashboard.getNumber("Target Position", 200);
    m_motmag.Slot = 0;
    if (joystick.getRawButton(1)) {
        motor.setControl(m_motmag.withPosition(targetPosition));
    } else {
        motor.setControl(m_motmag.withPosition(0));
    }

    // Send motor telemetry to the SmartDashboard
    SmartDashboard.putNumber("Encoder Position (ticks)", motor.getPosition().getValue());
    SmartDashboard.putNumber("Motor Velocity", motor.getVelocity().getValue());
  }
  
  private void updateMotorConfigurationFromDashboard() {
    // Create a new configuration object
    var talonFXConfigs = new TalonFXConfiguration();

    // Slot 0 configuration
    var slot0Configs = talonFXConfigs.Slot0;
    slot0Configs.kS = SmartDashboard.getNumber("kS", kS);
    slot0Configs.kP = SmartDashboard.getNumber("kP", kP);
    slot0Configs.kI = SmartDashboard.getNumber("kI", kI);
    slot0Configs.kD = SmartDashboard.getNumber("kD", kD);

    // Motion Magic configuration
    var motionMagicConfigs = talonFXConfigs.MotionMagic;
    motionMagicConfigs.MotionMagicAcceleration = SmartDashboard.getNumber("Acceleration", MagicAcceleration);
    motionMagicConfigs.MotionMagicCruiseVelocity = SmartDashboard.getNumber("Cruise Velocity", MagicCruiseVelocity);
    motionMagicConfigs.MotionMagicJerk = SmartDashboard.getNumber("Jerk", MagicJerk);

    // Apply the updated configuration to the motor
    motor.getConfigurator().apply(talonFXConfigs, 0.050);
  }
  
}

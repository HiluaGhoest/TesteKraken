package frc.robot.Kraken;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.TalonFXConfiguration;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SubsystemKraken extends SubsystemBase {
    private final TalonFX motor = new TalonFX(1);
    private final MotionMagicVoltage m_motmag = new MotionMagicVoltage(0);

    public SubsystemKraken() {
        motor.setPosition(0.0); //Reset Kraken
        SmartDashboard.putNumber("TargetPosition", 1);
    }

    public void setPosition(double position) {
        m_motmag.Slot = 0;
        motor.setControl(m_motmag.withPosition(position));
    }

    public double getEncoderPosition() {
        return motor.getPosition().getValue();
    }

    public double getMotorVelocity() {
        return motor.getVelocity().getValue();
    }

    public void periodic() {
        SmartDashboard.putNumber("Encoder Position (ticks)", getEncoderPosition());
        SmartDashboard.putNumber("Motor Velocity", getMotorVelocity());
    }

    public void updateConfigurationFromDashboard() {
        //Create configuration and update based on dashboard
        var talonFXConfigs = new TalonFXConfiguration();
        var slot0Configs = talonFXConfigs.Slot0;
        slot0Configs.kS = SmartDashboard.getNumber("kS", 0.28);
        slot0Configs.kS = SmartDashboard.getNumber("kP", 1.0);
        slot0Configs.kS = SmartDashboard.getNumber("kI", 0.2);
        slot0Configs.kS = SmartDashboard.getNumber("kD", 0.0);

        var motionMagicConfigs = talonFXConfigs.MotionMagic;
        motionMagicConfigs.MotionMagicAcceleration = SmartDashboard.getNumber("Acceleration", 600);
        motionMagicConfigs.MotionMagicCruiseVelocity = SmartDashboard.getNumber("Cruise Velocity", 240);
        motionMagicConfigs.MotionMagicJerk = SmartDashboard.getNumber("Jerk", 1600);

        motor.getConfigurator().apply(talonFXConfigs, 0.050);
    }
}

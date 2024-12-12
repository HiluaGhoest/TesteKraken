package frc.robot.Kraken;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SubsystemKraken extends SubsystemBase {
    private final TalonFX motor = new TalonFX(1);
    private final MotionMagicVoltage m_motmag = new MotionMagicVoltage(0);
    private static final double kS = 0.28;
    private static final double kP = 1.0;
    private static final double kI = 0.2;
    private static final double kD = 0.0;
    private static final double MagicAcceleration = 600;
    private static final double MagicCruiseVelocity = 240;
    private static final double MagicJerk = 1600;

    public SubsystemKraken() {
        motor.setPosition(0.0); // Reset encoder to zero
        SmartDashboard.putNumber("TargetPosition", 1);

        // Configure motor parameters
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.Slot0.kS = kS;
        config.Slot0.kP = kP;
        config.Slot0.kI = kI;
        config.Slot0.kD = kD;
        config.MotionMagic.MotionMagicAcceleration = MagicAcceleration;
        config.MotionMagic.MotionMagicCruiseVelocity = MagicCruiseVelocity;
        config.MotionMagic.MotionMagicJerk = MagicJerk;
        motor.getConfigurator().apply(config, 0.050);
    }

    public void executeKrakenMode1() {
        setPosition(10.0); // Example: Move to position 10
        System.out.println("Executing Kraken Mode 1");
    }

    public void executeKrakenMode2() {
        setPosition(20.0); // Example: Move to position 20
        System.out.println("Executing Kraken Mode 2");
    }    

    public void setPosition(double position) {
        System.out.println("Setting motor position to: " + position);
        m_motmag.Slot = 0;
        motor.setControl(m_motmag.withPosition(position));
    }

    public void stopMotor() {
        motor.setControl(m_motmag.withPosition(0.0));
    }

    // Log encoder position and velocity
    public void logEncoderData() {
        double positionTicks = motor.getPosition().getValue();
        double velocityTicksPer100ms = motor.getVelocity().getValue();
        
        SmartDashboard.putNumber("Encoder Position (ticks)", positionTicks);
        SmartDashboard.putNumber("Motor Velocity (ticks/100ms)", velocityTicksPer100ms);
    }

    @Override
    public void periodic() {
        logEncoderData(); // Log periodically
    }
}

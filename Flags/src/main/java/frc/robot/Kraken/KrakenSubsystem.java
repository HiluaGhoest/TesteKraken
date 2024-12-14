package frc.robot.Kraken;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.FlagManagers.FlagManagers;
import frc.robot.FlagManagers.FlagManagers.KrakenFlagManager;

public class KrakenSubsystem extends SubsystemBase {
    // Flag manager
    private final FlagManagers flagManagers;

    private final TalonFX motor = new TalonFX(1);
    private final MotionMagicVoltage m_motmag = new MotionMagicVoltage(0);
    private static final double kS = 0.28;
    private static final double kP = 1.0;
    private static final double kI = 0.2;
    private static final double kD = 0.0;
    private static final double MagicAcceleration = 600;
    private static final double MagicCruiseVelocity = 240;
    private static final double MagicJerk = 1600;

    private double lastKnownPosition = 0.0; // Store the last known position
    private double lastKnownVelocity = 0.0; // Store the last known velocity

    public KrakenSubsystem(FlagManagers flagManagers) {
        // Flag manager
        this.flagManagers = flagManagers;


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

    public void setPosition(double position) {
        System.out.println("Setting motor position to: " + position);
        m_motmag.Slot = 0;
        motor.setControl(m_motmag.withPosition(position));
    }

    public double getCurrentPosition(int delayTime) {
        // Update the last known position and velocity
        lastKnownPosition = motor.getPosition().getValue();
        lastKnownVelocity = motor.getVelocity().getValue(); // Get the current velocity

        // Calculate how much the motor would have moved during the delay
        return lastKnownPosition + (lastKnownVelocity * delayTime);
    }

    public void stopMotor() {
        motor.setControl(m_motmag.withPosition(getCurrentPosition(1)));
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

    public void handleActiveFlag() {
        KrakenFlagManager krakenFlagManager = flagManagers.getFlagManager(KrakenFlagManager.class);
        KrakenFlags activeFlag = krakenFlagManager.getActiveFlag();
        
        switch (activeFlag) {
            case MOVE_FORWARD:
                CommandScheduler.getInstance().schedule(new CommandsKraken.MoveForwardCommand(this));
                break;
            case MOVE_BACKWARD:
                CommandScheduler.getInstance().schedule(new CommandsKraken.MoveBackwardCommand(this));
                break;
            case STOP:
                CommandScheduler.getInstance().schedule(new CommandsKraken.StopCommand(this));

                // Only schedule StopCommand if no movement flags are active
                if (!krakenFlagManager.isFlagSet(KrakenFlags.MOVE_FORWARD) && 
                    !krakenFlagManager.isFlagSet(KrakenFlags.MOVE_BACKWARD)) {
                    CommandScheduler.getInstance().schedule(new CommandsKraken.StopCommand(this));
                }
                break;
            default:
                break; // No active flag
        }
    }
}

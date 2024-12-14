package frc.robot.Kraken;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CommandKraken extends Command {
    private final SubsystemKraken subsystemKraken;

    public CommandKraken(SubsystemKraken subsystemKraken) {
        this.subsystemKraken = subsystemKraken;
        addRequirements(subsystemKraken);
    }

    @Override
    public void execute() {
        // Dynamically fetch targetPosition from SmartDashboard
        double targetPosition = SmartDashboard.getNumber("TargetPosition", 1);
        subsystemKraken.setPosition(targetPosition);
    }

    @Override
    public void end(boolean interrupted) {
        subsystemKraken.setPosition(0.0); // Stop motor when command ends
    }

    @Override
    public boolean isFinished() {
        return false; // Run continuously until interrupted
    }
}
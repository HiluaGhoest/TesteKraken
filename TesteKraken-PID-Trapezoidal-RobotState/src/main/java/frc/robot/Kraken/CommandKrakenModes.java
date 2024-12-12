package frc.robot.Kraken;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.RobotState.State.RobotState;
import frc.robot.RobotState.State.StateManager;

public class CommandKrakenModes extends Command {
    private final SubsystemKraken kraken;
    private final StateManager stateManager;

    public CommandKrakenModes(SubsystemKraken kraken, StateManager stateManager) {
        this.kraken = kraken;
        this.stateManager = stateManager;
        addRequirements(kraken);
    }

    @Override
    public void execute() {
        RobotState state = stateManager.getCurrentState();

        switch (state) {
            case FORWARD:
                kraken.executeKrakenMode1();
                break;
            case BACKWARD:
                kraken.executeKrakenMode2();
                break;
            default:
                kraken.stopMotor(); // Stop motor if not in Kraken-specific states
        }
    }

    @Override
    public void end(boolean interrupted) {
        kraken.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return false; // Runs continuously
    }
}

package frc.robot.Kraken;

import frc.robot.RobotState.State.RobotState;
import frc.robot.RobotState.State.StateManager;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

public class CommandKraken extends Command {
    private final SubsystemKraken kraken;
    private final StateManager stateManager;

    public CommandKraken(SubsystemKraken kraken, StateManager stateManager) {
        this.kraken = kraken;
        this.stateManager = stateManager;
        addRequirements(kraken);
    }

    @Override
    public void execute() {
        double targetPosition = SmartDashboard.getNumber("TargetPosition", 1.0);
        System.out.println(stateManager.getCurrentState());

        // Check the current robot state and act accordingly
        switch (stateManager.getCurrentState()) {
            case FORWARD:
                kraken.setPosition(targetPosition * 1); // Example: Multiply position for Mode 1
                break;

            case BACKWARD:
                kraken.setPosition(targetPosition * -1); // Example: Half position for Mode 2
                break;

            default:
                kraken.stopMotor(); // Stop motor in other states
        }
    }
    

    @Override
    public void end(boolean interrupted) {
        kraken.stopMotor();
    }

    @Override
    public boolean isFinished() {
        return false; // Runs continuously until interrupted
    }
}

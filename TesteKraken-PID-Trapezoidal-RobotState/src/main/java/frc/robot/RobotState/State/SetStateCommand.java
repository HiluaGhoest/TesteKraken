package frc.robot.RobotState.State;

import edu.wpi.first.wpilibj2.command.InstantCommand;

public class SetStateCommand extends InstantCommand {
    public SetStateCommand(StateManager stateManager, RobotState targetState) {
        super(() -> stateManager.setState(targetState));
    }
}

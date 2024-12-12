package frc.robot.RobotState.State;

import frc.robot.RobotState.Flag.FlagManager;
import frc.robot.RobotState.Flag.FlagState;

public class StateManager {

    private RobotState currentState = RobotState.IDLE; // Default state
    private final FlagManager flagManager;

    public StateManager(FlagManager flagManager) {
        this.flagManager = flagManager;
    }

    public void processFlags() {
        switch (flagManager.getFlag()) {
            case FORWARD:
                setState(RobotState.FORWARD);
                break;
            case BACKWARD:
                setState(RobotState.BACKWARD);
                break;
            default:
                setState(RobotState.IDLE);
                break;
        }
    }    

    public void setState(RobotState newState) {
        if (currentState != newState) {
            currentState = newState;
            System.out.println("State changed to: " + currentState);
            // Add state-specific logic here if needed
        }
    }

    public RobotState getCurrentState() {
        return currentState;
    }
}

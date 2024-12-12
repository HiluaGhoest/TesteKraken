package frc.robot.RobotState.Flag;

import java.util.EnumMap;

public class FlagManager {
    private EnumMap<FlagState, Boolean> flagMap = new EnumMap<>(FlagState.class);

    public FlagManager() {
        // Initialize all flags to false
        for (FlagState flag : FlagState.values()) {
            flagMap.put(flag, false);
        }
    }

    // Method to set a flag and deactivate others in the same group
    public void setFlag(FlagState flag, boolean value) {
        if (value) {
            deactivateGroup(flag); // Deactivate other flags in the same group
        }
        flagMap.put(flag, value);
        System.out.println("Flag " + flag + " set to " + value);
    }

    // Method to deactivate other flags in the same group
    private void deactivateGroup(FlagState activeFlag) {
        switch (activeFlag) {
            case FORWARD:
                flagMap.put(FlagState.BACKWARD, false);
                break;
            case BACKWARD:
                flagMap.put(FlagState.FORWARD, false);
                break;
            // Add more groups as needed
            default:
                break;
        }
    }

    // Method to check if a flag is active
    public boolean isFlagSet(FlagState flag) {
        return flagMap.getOrDefault(flag, false);
    }

    // Method to get the current active flag (prioritize based on order in FlagState)
    public FlagState getFlag() {
        for (FlagState flag : FlagState.values()) {
            if (flagMap.getOrDefault(flag, false)) {
                return flag; // Return the first active flag found
            }
        }
        return FlagState.IDLE; // Default to IDLE if no flags are active
    }
}

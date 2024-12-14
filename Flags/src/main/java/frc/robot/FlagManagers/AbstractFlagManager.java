package frc.robot.FlagManagers;

import java.util.EnumMap;

public abstract class AbstractFlagManager<T extends Enum<T>> {
    private EnumMap<T, Boolean> flagMap = new EnumMap<>(getFlagType());

    public AbstractFlagManager() {
        for (T flag : getFlagType().getEnumConstants()) {
            flagMap.put(flag, false);
        }
    }

    protected abstract Class<T> getFlagType();

    public void setFlag(T flag, boolean value) {
        flagMap.put(flag, value);
        System.out.println("Flag " + flag + " set to " + value);
    }

    public boolean isFlagSet(T flag) {
        return flagMap.getOrDefault(flag, false);
    }

    public T getActiveFlag() {
        for (T flag : getFlagType().getEnumConstants()) {
            if (isFlagSet(flag)) {
                return flag; // Return the first flag that is set
            }
        }
        return getFlagType().getEnumConstants()[0]; // Or return a default flag if none are set
    }
}
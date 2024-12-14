package frc.robot.FlagManagers;

import java.util.HashMap;
import java.util.Map;

import frc.robot.Climber.ClimberFlags;
import frc.robot.Intake.IntakeFlags;
import frc.robot.Kraken.KrakenFlags;

public class FlagManagers {
    private final Map<Class<?>, AbstractFlagManager<?>> flagManagers = new HashMap<>();

    public FlagManagers() {
        try {
            flagManagers.put(ClimberFlagManager.class, new ClimberFlagManager());
            flagManagers.put(IntakeFlagManager.class, new IntakeFlagManager());
            flagManagers.put(KrakenFlagManager.class, new KrakenFlagManager());
        } catch (Exception e) {
            System.err.println("Error initializing FlagManagers: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Enum<T>, M extends AbstractFlagManager<T>> M getFlagManager(Class<M> flagManagerClass) {
        return (M) flagManagers.get(flagManagerClass);
    }

    // Static inner class for ClimberFlagManager
    public static class ClimberFlagManager extends AbstractFlagManager<ClimberFlags> {
        @Override
        protected Class<ClimberFlags> getFlagType() {
            return ClimberFlags.class;
        }
    }

    // Static inner class for IntakeFlagManager
    public static class IntakeFlagManager extends AbstractFlagManager<IntakeFlags> {
        @Override
        protected Class<IntakeFlags> getFlagType() {
            return IntakeFlags.class;
        }
    }

    // Static inner class for KrakenFlagManager
    public static class KrakenFlagManager extends AbstractFlagManager<KrakenFlags> {
        @Override
        protected Class<KrakenFlags> getFlagType() {
            return KrakenFlags.class;
        }
    }
}
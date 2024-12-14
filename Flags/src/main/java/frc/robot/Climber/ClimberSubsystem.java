package frc.robot.Climber;

import frc.robot.FlagManagers.FlagManagers;
import frc.robot.FlagManagers.FlagManagers.ClimberFlagManager;


public class ClimberSubsystem {
    private final FlagManagers flagManagers;

    public ClimberSubsystem(FlagManagers flagManagers) {
        this.flagManagers = flagManagers;
    }

    public void update() {
        // Use the generic getFlagManager method to retrieve the ClimberFlagManager
        ClimberFlagManager climberFlagManager = flagManagers.getFlagManager(ClimberFlagManager.class);

        if (climberFlagManager.isFlagSet(ClimberFlags.FORWARD)) {
            // Move the drivetrain forward
            System.out.println("Drivetrain is moving forward");
        } else if (climberFlagManager.isFlagSet(ClimberFlags.BACKWARD)) {
            // Move the drivetrain backward
            System.out.println("Drivetrain is moving backward");
        } else if (climberFlagManager.isFlagSet(ClimberFlags.TURN_LEFT)) {
            // Turn the drivetrain left
            System.out.println("Drivetrain is turning left");
        } else if (climberFlagManager.isFlagSet(ClimberFlags.TURN_RIGHT)) {
            // Turn the drivetrain right
            System.out.println("Drivetrain is turning right");
        } else if (climberFlagManager.isFlagSet(ClimberFlags.DRIVETRAIN_IDLE)) {
            // Set the drivetrain to idle
            System.out.println("Drivetrain is idle");
        }
    }
}
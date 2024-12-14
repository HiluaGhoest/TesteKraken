package frc.robot.Intake;

import frc.robot.FlagManagers.FlagManagers;
import frc.robot.FlagManagers.FlagManagers.IntakeFlagManager;

public class IntakeSubsystem {
    private final FlagManagers flagManagers;

    public IntakeSubsystem(FlagManagers flagManagers) {
        this.flagManagers = flagManagers;
    }

    public void update() {
        IntakeFlagManager intakeFlagManager = flagManagers.getFlagManager(IntakeFlagManager.class);

        if (intakeFlagManager.isFlagSet(IntakeFlags.INTAKE_ON)) {
            // Turn on the intake motor
            System.out.println("Intake motor is on");
        } else if (intakeFlagManager.isFlagSet(IntakeFlags.INTAKE_OFF)) {
            // Turn off the intake motor
            System.out.println("Intake motor is off");
        } else if (intakeFlagManager.isFlagSet(IntakeFlags.INTAKE_IDLE)) {
            // Set the intake motor to idle
            System.out.println("Intake motor is idle");
        }
    }
}
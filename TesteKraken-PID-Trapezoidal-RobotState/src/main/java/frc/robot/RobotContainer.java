package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Kraken.*;
import frc.robot.RobotState.Flag.FlagManager;
import frc.robot.RobotState.Flag.FlagState;
import frc.robot.RobotState.State.RobotState;
import frc.robot.RobotState.State.SetStateCommand;
import frc.robot.RobotState.State.StateManager;

public class RobotContainer {
    private final Joystick joystick = new Joystick(0);
    private final SubsystemKraken kraken = new SubsystemKraken();
    private final FlagManager flagManager;
    private final StateManager stateManager;

    public RobotContainer(StateManager stateManager, FlagManager flagManager) {
        this.stateManager = stateManager;
        this.flagManager = flagManager;
        configureBindings();
    }

    private void configureBindings() {
      // Button 1: Set Kraken to Mode 1
      new JoystickButton(joystick, 1)
        .onTrue(new InstantCommand(() -> {
          flagManager.setFlag(FlagState.FORWARD, true);
          System.out.println("FORWARD flag set to true");
        }))
        .whileTrue(new CommandKraken(kraken, stateManager))
        .onFalse(new InstantCommand(() -> {
            flagManager.setFlag(FlagState.FORWARD, false);
            System.out.println("FORWARD flag set to false");
        }));
      // Button 2: Set Kraken to Mode 2
      new JoystickButton(joystick, 2)
        .onTrue(new InstantCommand(() -> {
            flagManager.setFlag(FlagState.BACKWARD, true);
            System.out.println("BACKWARD flag set to true");
        }))
        .whileTrue(new CommandKraken(kraken, stateManager))
        .onFalse(new InstantCommand(() -> {
            flagManager.setFlag(FlagState.BACKWARD, false);
            System.out.println("BACKWARD flag set to false");
        }));
  }
  
}

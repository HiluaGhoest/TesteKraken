package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Kraken.KrakenSubsystem;
import frc.robot.FlagManagers.FlagManagers;
import frc.robot.FlagManagers.FlagManagers.KrakenFlagManager;
import frc.robot.Kraken.KrakenFlags;


public class RobotContainer {
    private final Joystick joystick = new Joystick(0);
    private final FlagManagers flagManagers = new FlagManagers();

    private final KrakenSubsystem krakenSubsystem = new KrakenSubsystem(flagManagers);
    private final KrakenFlagManager krakenFlagManager = flagManagers.getFlagManager(KrakenFlagManager.class);

    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {
      System.out.println("Configure bindings called");
      
      // Button 1: Set Kraken MOVE_FORWARD
      new JoystickButton(joystick, 1)
          .whileTrue(new InstantCommand(() -> {
              krakenFlagManager.setFlag(KrakenFlags.MOVE_FORWARD, true);
          }))
          .onFalse(new InstantCommand(() -> {
              krakenFlagManager.setFlag(KrakenFlags.MOVE_FORWARD, false);
          }));
  
      // Button 2: Set Kraken MOVE_BACKWARD
      new JoystickButton(joystick, 2)
          .whileTrue(new InstantCommand(() -> {
              krakenFlagManager.setFlag(KrakenFlags.MOVE_BACKWARD, true);
          }))
          .onFalse(new InstantCommand(() -> {
              krakenFlagManager.setFlag(KrakenFlags.MOVE_BACKWARD, false);
          }));
  
      // Button 3: Set Kraken STOP
      new JoystickButton(joystick, 3)
          .whileTrue(new InstantCommand(() -> {
              krakenFlagManager.setFlag(KrakenFlags.STOP, true);
          }))
          .onFalse(new InstantCommand(() -> {
              krakenFlagManager.setFlag(KrakenFlags.STOP, false);
          }));
    }

    public void update() {
      krakenSubsystem.handleActiveFlag();
  
      // Update SmartDashboard with the current flag states
      for (KrakenFlags flag : KrakenFlags.values()) {
          SmartDashboard.putBoolean(flag.toString(), krakenFlagManager.isFlagSet(flag));
      }
  }
}
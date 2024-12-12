// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Kraken.*; //Import Kraken subsystems and commands

public class RobotContainer {
  // Instantiate subsystems as class-level fields
  private final Joystick joystick = new Joystick(0);
  private final SubsystemKraken kraken = new SubsystemKraken();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    System.out.println("Button Configured");
     new JoystickButton(joystick, 1)
      .whileTrue(new CommandKraken(kraken)); //Change to actual command
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}

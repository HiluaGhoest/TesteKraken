package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private RobotContainer robotContainer;

  @Override
  public void robotInit() {
    robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    // This ensures the CommandScheduler runs regularly
    CommandScheduler.getInstance().run();
  }

  @Override
  public void teleopPeriodic() {
    // Optional: You can add custom debug or testing logic here
  }
}

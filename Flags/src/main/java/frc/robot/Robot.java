package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    private RobotContainer m_robotContainer;

    @Override
    public void robotInit() {
        m_robotContainer = new RobotContainer();
        System.out.println(m_robotContainer);
    }

    @Override
    public void disabledInit() {
        // Code to run when the robot is disabled
    }

    @Override
    public void teleopInit() {
        System.out.println("Teleop mode initialized");

        // Code to run when the robot enters teleop mode
    }

    @Override
    public void robotPeriodic() {
        // Call the Command Scheduler to run commands
        CommandScheduler.getInstance().run();
        
        // Call the update method of RobotContainer
        m_robotContainer.update();
    }

    @Override
    public void teleopPeriodic() {
        // This method is called periodically during teleop
        // You can add any teleop-specific logic here if needed
    }
}
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.RobotState.Flag.FlagManager;
import frc.robot.RobotState.State.RobotState;
import frc.robot.RobotState.State.StateManager;

public class Robot extends TimedRobot {
    private Command m_autonomousCommand;

    private RobotContainer m_robotContainer;
    private final FlagManager flagManager = new FlagManager(); // Create FlagManager instance
    private final StateManager stateManager = new StateManager(flagManager);

    @Override
    public void robotInit() {
        m_robotContainer = new RobotContainer(stateManager, flagManager); // Pass StateManager to RobotContainer
    }

    @Override
    public void disabledInit() {
        stateManager.setState(RobotState.DISABLED);
    }

    @Override
    public void teleopInit() {
        stateManager.setState(RobotState.TELEOP);
    }

    @Override
    public void autonomousInit() {
        stateManager.setState(RobotState.AUTONOMOUS);
    }

    @Override
    public void testInit() {
        stateManager.setState(RobotState.TEST);
    }
    
    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
        stateManager.processFlags();
        SmartDashboard.putString("Robot State", stateManager.getCurrentState().toString());
        SmartDashboard.putNumber("Target Position", SmartDashboard.getNumber("TargetPosition", 1));
    }
    

    @Override
    public void teleopPeriodic() {
        // Add teleop-specific updates if necessary
        super.teleopPeriodic();
    }

    @Override
    public void disabledPeriodic() {
        // Add disabled-specific updates if necessary
        super.disabledPeriodic();
    }
}

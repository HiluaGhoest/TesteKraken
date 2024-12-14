package frc.robot.Kraken;

import edu.wpi.first.wpilibj2.command.Command;

public class CommandsKraken {

    public static class MoveForwardCommand extends Command {
        private final KrakenSubsystem krakenSubsystem;

        public MoveForwardCommand(KrakenSubsystem krakenSubsystem) {

            this.krakenSubsystem = krakenSubsystem;
            addRequirements(krakenSubsystem);
        }

        @Override
        public void initialize() {
            // Initialization logic if needed
        }

        @Override
        public void execute() {
            krakenSubsystem.setPosition(400.0); // Example position for moving forward
        }

        @Override
        public void end(boolean interrupted) {
            // Stop the motor at the current position
            if (interrupted) {
                // Optionally, you can log the current position if needed
                // System.out.println("MoveBackwardCommand interrupted. Current position: " + krakenSubsystem.getCurrentPosition());
            }
            // Do not reset position, just stop the motor
            // krakenSubsystem.stopMotor(); 
       }

        @Override
        public boolean isFinished() {
            return false; // This command runs until interrupted
        }
    }

    public static class MoveBackwardCommand extends Command {
        private final KrakenSubsystem krakenSubsystem;

        public MoveBackwardCommand(KrakenSubsystem krakenSubsystem) {
            this.krakenSubsystem = krakenSubsystem;
            addRequirements(krakenSubsystem);
        }

        @Override
        public void initialize() {
            // Initialization logic if needed
        }

        @Override
        public void execute() {
            krakenSubsystem.setPosition(-1.0); // Example position for moving backward
        }

        @Override
        public void end(boolean interrupted) {
            // Stop the motor at the current position
            if (interrupted) {
                // Optionally, you can log the current position if needed
                // System.out.println("MoveBackwardCommand interrupted. Current position: " + krakenSubsystem.getCurrentPosition());
            }
            // Do not reset position, just stop the motor
            // krakenSubsystem.stopMotor();        
        }

        @Override
        public boolean isFinished() {
            return false; // This command runs until interrupted
        }
    }

    public static class StopCommand extends Command {
        private final KrakenSubsystem krakenSubsystem;

        public StopCommand(KrakenSubsystem krakenSubsystem) {
            this.krakenSubsystem = krakenSubsystem;
            addRequirements(krakenSubsystem);
        }

        @Override
        public void initialize() {
            krakenSubsystem.stopMotor(); // Stop the motor
            // Initialization logic if needed
        }

        @Override
        public void execute() {
        }

        @Override
        public boolean isFinished() {
            return true; // This command finishes immediately
        }
    }
}
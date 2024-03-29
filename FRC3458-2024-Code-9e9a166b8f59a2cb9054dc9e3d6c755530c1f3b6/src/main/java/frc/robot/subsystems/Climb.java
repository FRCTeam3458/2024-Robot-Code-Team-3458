// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  public Climb() {}

  private final VictorSPX climb = new VictorSPX(8);

public Command Extend() {
    return runOnce(() -> climb.set(VictorSPXControlMode.PercentOutput, 1))
    .withName("Extend");
  }

  public Command Retract() {
    return runOnce(() -> climb.set(VictorSPXControlMode.PercentOutput, -1))
    .withName("Retract");
  }

  public Command StopClimb() {
    return runOnce(() -> climb.set(VictorSPXControlMode.PercentOutput, 0))
    .withName("Stop Climb");
  }

/*   @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }*/
}

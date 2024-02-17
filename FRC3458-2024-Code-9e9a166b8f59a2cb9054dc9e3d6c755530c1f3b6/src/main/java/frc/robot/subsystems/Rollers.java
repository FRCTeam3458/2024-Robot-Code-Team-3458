// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration;

import edu.wpi.first.wpilibj.motorcontrol.Victor;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Rollers extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  //public OuterIntake() {}
   private final VictorSPX doubleRoller = new VictorSPX(16);

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command IntakeCommand() {
    return runOnce(() -> doubleRoller.set(VictorSPXControlMode.PercentOutput, 0))
          .withName("Intake Rollers"); 
  }
  public Command Shoot() {
    return runOnce(() -> doubleRoller.set(VictorSPXControlMode.PercentOutput, -1))
            .withName("Shoot");
  }
  public Command alignNote() {
    return runOnce(() -> doubleRoller.set(VictorSPXControlMode.PercentOutput, 0.5))
            .withName("Align Note");
  }
  public Command StopDouble() {
    return runOnce(() -> doubleRoller.set(VictorSPXControlMode.PercentOutput, 0))
            .withName("Stop Double Rollers");
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

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.*;
import frc.robot.subsystems.swerve.rev.RevSwerve;
import frc.robot.subsystems.Rollers;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Flywheels;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final Joystick operator = new Joystick(1);

    //private final DigitalInput noteSensor = new DigitalInput(1);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;

    /* Driver Buttons */
    //private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton intake = new JoystickButton(operator, 1);
    private final JoystickButton runFlywheel = new JoystickButton(operator, 2);
    private final JoystickButton shootRoller = new JoystickButton(operator, 4);
    private final JoystickButton ampScore = new JoystickButton(operator, 3);

    private final JoystickButton temp1 = new JoystickButton(driver, 1);
    private final JoystickButton temp2 = new JoystickButton(driver, 2);
    private final JoystickButton temp3 = new JoystickButton(driver, 3);
    private final JoystickButton temp4 = new JoystickButton(driver, 4);

    /* Subsystems */
    private final RevSwerve s_Swerve = new RevSwerve();
    private final Flywheels s_Flywheels = new Flywheels();
    private final Rollers s_Rollers = new Rollers();
    private final Arm s_Arm = new Arm();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> driver.getRawAxis(translationAxis), 
                () -> driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(2), 
                () -> false
            )
        );

         s_Flywheels.setDefaultCommand(s_Flywheels.StopFlywheels());
      // s_Rollers.setDefaultCommand(s_Rollers.StopDouble()); 
        s_Rollers.setDefaultCommand(s_Rollers.setRollerSpeed(0));

        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        //zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));

        /* Operator Buttons */
       /*   intake.onTrue(s_Flywheels.IntakeCommand());
        intake.onFalse(s_Flywheels.StopFlywheels()); 
        intake.onTrue(s_Flywheels.IntakeCommand());
        intake.onTrue(s_Rollers.IntakeCommand());
        intake.onFalse(s_Flywheels.StopFlywheels());
        intake.onFalse(s_Rollers.StopDouble()); */

        runFlywheel.onTrue(new ParallelCommandGroup((s_Flywheels.RunFlywheels().alongWith(new WaitCommand(1).andThen(s_Rollers.Shoot())))));
        runFlywheel.onFalse(s_Flywheels.StopFlywheels());
        runFlywheel.onFalse(s_Rollers.StopDouble());

        ampScore.onTrue(s_Rollers.IntakeCommand());
        ampScore.onFalse(s_Rollers.StopDouble());

       // intake.onTrue(s_Rollers.IntakeCommand());
       // intake.onFalse(s_Rollers.StopDouble()); 


        temp4.whileTrue(s_Arm.armToSpeakerCommand());
        temp4.onFalse(s_Arm.stopArm()); 

        temp2.whileTrue(s_Arm.armToIntakeCommand());
        temp2.onFalse(s_Arm.stopArm()); 

        temp1.onTrue(s_Arm.runArm());
        temp1.onFalse(s_Arm.stopArm()); 
        
        temp3.onTrue(s_Arm.armToAmpCommand());
        temp3.onTrue(s_Arm.armToAmpCommand());

        
    
       intake.onTrue(s_Flywheels.IntakeCommand());
       intake.onFalse(s_Flywheels.StopFlywheels());
        
        s_Flywheels.hasNote.onFalse(s_Flywheels.IntakeCommand());
        s_Flywheels.hasNote.onFalse(s_Rollers.IntakeCommand());
        s_Flywheels.hasNote.onTrue(s_Flywheels.StopFlywheels());
        s_Flywheels.hasNote.onTrue(s_Rollers.StopDouble());


        
        
        
       
        
        
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }
    
}

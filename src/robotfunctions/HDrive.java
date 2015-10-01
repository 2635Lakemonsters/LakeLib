package robotfunctions;

import actuator.IActuator;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * A simple HDrive class. Basically a simple drive base with an added middle wheel.
 * @author Tristan Thompson
 *
 */
public class HDrive 
{
	RobotDrive drive;
	IActuator<Double> middleWheel;
	public HDrive(RobotDrive drive, IActuator<Double> middleWheel) {
		super();
		this.drive = drive;
		this.middleWheel = middleWheel;
	}
	public void drive(double X, double Y, double rotation)
	{
		drive.arcadeDrive(Y, rotation);
		middleWheel.actuate(X);
	}
}

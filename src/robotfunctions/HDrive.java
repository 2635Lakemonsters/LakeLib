package robotfunctions;

import actuator.IActuator;
import actuator.IDrive;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * A simple HDrive class. Basically a simple drive base with an added middle wheel.
 * @author Tristan Thompson
 *
 */
public class HDrive 
{
	IDrive drive;
	IActuator<Double> middleWheel;
	public HDrive(IDrive drive, IActuator<Double> middleWheel) {
		super();
		this.drive = drive;
		this.middleWheel = middleWheel;
	}
	/**
	 * Drive the drive and actuates the middle wheel.
	 */
	public void drive(double X, double Y, double rotation)
	{
		drive.drive(rotation, Y);
		middleWheel.actuate(X);
	}
}

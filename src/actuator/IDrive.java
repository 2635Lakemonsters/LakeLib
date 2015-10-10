package actuator;

/**
 * A drive interface. Be careful what you use in your implementing class.
 * For example, an HDrive may be made up of a RobotDrive and a SpeedController, 
 * but the middle wheel's implementation may change, so a class that is composed of an IActuator and IDrive may be preferable.
 * On the other hand, a swerve drive is made up of a number of SpeedControllers, and changing implementation of the drive may change the entire layout of the class.
 * Use discretion. 
 * 
 * Implementation examples include: <br>
 * A drive system driven by a PIDController. <br>
 * A swerve drive <br>
 * A car <br>
 * 
 * @author Tristan Thompson
 *
 */
public interface IDrive 
{
	public boolean drive(double X, double Y);
}

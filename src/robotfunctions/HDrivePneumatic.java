package robotfunctions;

import edu.wpi.first.wpilibj.RobotDrive;
import actuator.IActuator;
import actuator.IDrive;

/**
 * Like HDrive, but uses a pneumatic piston to depress the middle wheel.
 * @author localuser
 *
 */
public class HDrivePneumatic extends HDrive
{
	IActuator<Boolean> piston;
	double depressionTolerance;
	public HDrivePneumatic(IDrive drive, IActuator<Double> middleWheel,
			IActuator<Boolean> piston, double depressionTolerance ) {
		super(drive, middleWheel);
		this.piston = piston;
		this.depressionTolerance = depressionTolerance;
	}
	@Override
	public void drive(double X, double Y, double rotation)
	{
		if(Math.abs(X) > depressionTolerance)
		{
			piston.actuate(true);
			middleWheel.actuate(X);
			drive.drive(0, 0, 0);
		}
		else
		{
			piston.actuate(false);
			middleWheel.actuate(0.0);
			drive.drive(Y, X, 0);
		}
	}
}

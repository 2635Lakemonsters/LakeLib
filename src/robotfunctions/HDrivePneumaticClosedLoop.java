package robotfunctions;

import sensor.ISensor;
import edu.wpi.first.wpilibj.PIDController;
import actuator.IActuator;
import actuator.IDrive;

/**
 * Uses PID to drive the main drive assembly when the middle wheel is down.
 * @author localuser
 *
 */
public class HDrivePneumaticClosedLoop extends HDrivePneumatic
{

	public PIDController pid;
	public ISensor<Double> setPointGet;
	public HDrivePneumaticClosedLoop(IDrive drive,
			IActuator<Double> middleWheel, IActuator<Boolean> piston, ISensor<Double> setPointGetter,
			double depressionTolerance)
	{
		super(drive, middleWheel, piston, depressionTolerance);
		this.setPointGet = setPointGetter;
	}
	@Override
	public void drive(double X, double Y, double rotation)
	{
		if(Math.abs(X) > depressionTolerance)
		{
			piston.actuate(true);
			middleWheel.actuate(X);
			//Drive from pid rather than from drive
			pid.setSetpoint(setPointGet.sense());
			pid.enable();
			
		}
		else
		{
			piston.actuate(false);
			middleWheel.actuate(0.0);
			drive.drive(Y, X, 0);
		}

	}

}

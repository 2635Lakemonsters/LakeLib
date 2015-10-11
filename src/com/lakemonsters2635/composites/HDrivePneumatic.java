package com.lakemonsters2635.composites;

import com.lakemonsters2635.actuator.interfaces.IActuator;
import com.lakemonsters2635.actuator.interfaces.IDrive;

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
			IActuator<Boolean> piston, double depressionTolerance ) 
	{
		super(drive, middleWheel);
		this.piston = piston;
		this.depressionTolerance = depressionTolerance;
	}
	/**
	 * Depresses the middle wheel if X goes above depressionTolerance. 
	 */
	@Override
	public void drive(double X, double Y, double rotation)
	{
		if(Math.abs(X) > depressionTolerance)
		{
			piston.actuate(true);
			middleWheel.actuate(X);
		}
		else
		{
			piston.actuate(false);
			middleWheel.actuate(0.0);
		}
		drive.drive(rotation, Y);

	}
}

package com.lakemonsters2635.composites;

import com.lakemonsters2635.actuator.interfaces.IActuator;
import com.lakemonsters2635.actuator.interfaces.IDrive;
import com.lakemonsters2635.sensor.ISensor;

import edu.wpi.first.wpilibj.PIDController;

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
			IActuator<Double> middleWheel, IActuator<Boolean> piston, ISensor<Double> setPointGetter, PIDController pid,
			double depressionTolerance)
	{
		super(drive, middleWheel, piston, depressionTolerance);
		this.setPointGet = setPointGetter;
		this.pid = pid;
	}
	/**
	 * Depresses the middle wheel when X goes above a certain tolerance. When the middle wheel is depressed the drive is driven by pid rather than the IDrive implementation.
	 */
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
			pid.disable();
			piston.actuate(false);
			middleWheel.actuate(0.0);
			drive.drive(rotation, Y);
		}

	}

}

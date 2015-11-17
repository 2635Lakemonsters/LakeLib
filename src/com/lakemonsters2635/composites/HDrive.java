package com.lakemonsters2635.composites;

import com.lakemonsters2635.actuator.interfaces.IActuator;
import com.lakemonsters2635.actuator.interfaces.IDrive;

/**
 * A simple HDrive class. Basically a simple drive base with an added middle wheel.
 * @author Tristan Thompson
 *
 */
public class HDrive 
{
	IDrive drive;
	IActuator<Double> middleWheel;
	/**
	 * 
	 * @param drive Drive module.
	 * @param middleWheel Middle wheel module.
	 */
	public HDrive(IDrive drive, IActuator<Double> middleWheel) 
	{
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

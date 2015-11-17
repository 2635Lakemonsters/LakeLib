package com.lakemonsters2635.composites;

import com.lakemonsters2635.actuator.interfaces.IActuator;

/**
 * A launcher with a means of adjusting angle and accelerating objects.
 * @author Tristan
 *
 */
public class SimpleLauncher extends BaseLauncher
{
	/**
	 * 
	 * @param accelerator The thing that makes the thing go fast.
	 * @param elevator Moves the launcher up and down.
	 */
	public SimpleLauncher(IActuator<Double> accelerator,
			IActuator<Double> elevator)
	{
		super();
		this.accelerator = accelerator;
		this.elevator = elevator;
	}
	IActuator<Double> accelerator;
	IActuator<Double> elevator;
	/**
	 * Actuate the accelerator supplying magnitude.
	 */
	@Override
	public void fire(double magnitude)
	{
		accelerator.actuate(magnitude);		
	}
	/**
	 * Set the elevation based on magnitude.
	 * @param elevation Magnitude of elevation
	 */
	public void setElevation(double elevation)
	{
		elevator.actuate(elevation);
	}

}

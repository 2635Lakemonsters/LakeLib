package com.lakemonsters2635.composites;

import com.lakemonsters2635.actuator.interfaces.IActuator;

/**
 * A launcher with a means of adjusting angle and accelerating objects.
 * @author Tristan
 *
 */
public class SimpleLauncher extends BaseLauncher
{
	IActuator<Double> accelerator;
	IActuator<Double> elevator;
	@Override
	public void fire(double magnitude)
	{
		accelerator.actuate(magnitude);		
	}
	public void setElevation(double elevation)
	{
		elevator.actuate(elevation);
	}

}

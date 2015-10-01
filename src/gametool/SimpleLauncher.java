package gametool;

import actuator.IActuator;

public class SimpleLauncher extends Launcher
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

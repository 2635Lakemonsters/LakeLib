package com.lakemonsters2635.actuator.interfaces;


public class ActuateTask<T> extends BaseRobotTask
{
	IActuator<T> actuator;
	T magnitude;
	public ActuateTask(IActuator<T> actuator, T magnitude) {
		super();
		this.actuator = actuator;
		this.magnitude = magnitude;
	}
	@Override
	public void run() 
	{
		actuator.actuate(magnitude);
		super.run();
	}
	
}

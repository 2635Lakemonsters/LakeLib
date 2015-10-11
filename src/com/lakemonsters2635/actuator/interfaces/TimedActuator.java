package com.lakemonsters2635.actuator.interfaces;

import java.util.Timer;

/**
 * An actuator that will actuate in a specific amount of time. Examples include: <br>
 * A winch that pulls something at a set rate <br>
 * A car that drives at a set rate in two directions 
 *  
 * @author Tristan Thompson
 *
 * @param <T>
 */
public class TimedActuator<T>
{
	Timer timer;
	boolean timerSet;
	IActuator<T> actuator;
	ActuateTask<T> task;
	public TimedActuator(Timer timer, IActuator<T> actuator,
			ActuateTask<T> task) {
		super();
		this.timer = timer;
		this.timerSet = false;
		this.actuator = actuator;
		this.task = task;
	}
	/**
	 * 
	 * @param timeMilliseconds
	 * @param magnitude
	 * @return true if task has executed, false if not.
	 */
	public boolean actuateIn(int timeMilliseconds, double magnitude)
	{
		if(!timerSet)
		{
			timerSet = true;
			timer.schedule(task, timeMilliseconds);
		}
		if(task.hasRun)
		{
			timerSet = false;
			return true;
		}
		return false;
		
	}
	
}
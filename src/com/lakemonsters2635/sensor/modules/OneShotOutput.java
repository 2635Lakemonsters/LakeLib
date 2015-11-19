package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.IOutput;

/**
 * One shot output. Same as OneShot in utils.
 * @author Tristan Thompson
 *
 */
public class OneShotOutput implements IOutput<Object, Object>
{

	Object previousValue;
	Object lowValue;
	public OneShotOutput(Object lowValue)
	{
		this.lowValue = lowValue;
		this.previousValue = lowValue;
	}
	public Object getOutput(Object currentValue)
	{
		//If T is Integer, Double, etc., .equals will compare by value, not by object
		if(!currentValue.equals(previousValue))
		{
			previousValue = currentValue;
			return currentValue;
		}
		return lowValue;
	}
}

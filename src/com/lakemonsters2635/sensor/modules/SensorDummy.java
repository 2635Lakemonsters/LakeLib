package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.ISensor;

public class SensorDummy<OutputType> implements ISensor<OutputType>
{
	OutputType constant;
	public SensorDummy(OutputType constant)
	{
		this.constant = constant;
	}
	@Override
	public OutputType sense()
	{

		return constant;
	}

}

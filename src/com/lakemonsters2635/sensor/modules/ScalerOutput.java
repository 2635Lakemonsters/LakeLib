package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.IOutput;

public class ScalerOutput implements IOutput<Double, Double>
{
	double scaler;
	public ScalerOutput(double scaler)
	{
		this.scaler = scaler;
	}
	@Override
	public Double getOutput(Double input)
	{
		return input * scaler;
	}

}

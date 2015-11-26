package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.IOutput;

public class OutputScaler implements IOutput<Double, Double>
{
	double scaler;
	public OutputScaler(double scaler)
	{
		this.scaler = scaler;
	}
	@Override
	public Double getOutput(Double input)
	{
		return input * scaler;
	}

}

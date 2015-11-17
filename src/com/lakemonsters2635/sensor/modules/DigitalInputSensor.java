package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.ISensor;

import edu.wpi.first.wpilibj.DigitalInput;

public class DigitalInputSensor implements ISensor<Boolean>
{
	DigitalInput digitalInput;
	DigitalInputSensor(DigitalInput digitalInput)
	{
		this.digitalInput = digitalInput;
	}

	@Override
	public Boolean sense()
	{
		return digitalInput.get();
	}
	
	
}

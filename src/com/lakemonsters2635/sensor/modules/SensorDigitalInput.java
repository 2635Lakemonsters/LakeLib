package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.ISensor;

import edu.wpi.first.wpilibj.DigitalInput;

public class SensorDigitalInput implements ISensor<Boolean>
{
	DigitalInput digitalInput;
	SensorDigitalInput(DigitalInput digitalInput)
	{
		this.digitalInput = digitalInput;
	}

	@Override
	public Boolean sense()
	{
		return digitalInput.get();
	}
	
	
}

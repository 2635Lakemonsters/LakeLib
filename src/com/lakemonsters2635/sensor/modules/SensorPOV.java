package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.ISensor;

import edu.wpi.first.wpilibj.Joystick;

public class SensorPOV implements ISensor<Integer>
{
	int POV;
	Joystick joystick;
	public SensorPOV(int POV, Joystick joystick)
	{
		super();
		this.POV = POV;
		this.joystick = joystick;
	}
	@Override
	public Integer sense()
	{
		return joystick.getPOV(POV);
	}
}

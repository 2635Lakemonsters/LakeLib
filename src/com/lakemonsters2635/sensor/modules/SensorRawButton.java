package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.ISensor;

import edu.wpi.first.wpilibj.Joystick;

public class SensorRawButton implements ISensor<Boolean>
{
	int button;
	Joystick joystick;
	public SensorRawButton(int button, Joystick joystick)
	{
		super();
		this.button = button;
		this.joystick = joystick;
	}
	@Override
	public Boolean sense()
	{
		return joystick.getRawButton(button);
	}
}

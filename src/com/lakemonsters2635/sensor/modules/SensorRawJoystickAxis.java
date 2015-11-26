package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.ISensor;

import edu.wpi.first.wpilibj.Joystick;

public class SensorRawJoystickAxis implements ISensor<Double>
{
	int axis;
	Joystick joystick;
	public SensorRawJoystickAxis(int axis, Joystick joystick)
	{
		super();
		this.axis = axis;
		this.joystick = joystick;
	}
	@Override
	public Double sense()
	{
		return joystick.getRawAxis(axis);
	}
	
}

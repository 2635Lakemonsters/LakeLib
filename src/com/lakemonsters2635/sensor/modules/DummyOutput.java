package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.IOutput;

/**
 * A class that simulates an output. Good for testing.
 * @author Tristan Thompson
 *
 */
public class DummyOutput<OutputType, InputType> implements IOutput<OutputType, InputType>
{
	public OutputType constant;
	public DummyOutput(OutputType constant)
	{
		this.constant = constant;
	}
	@Override
	public OutputType getOutput(InputType magnitude)
	{
		return constant;
	}

}

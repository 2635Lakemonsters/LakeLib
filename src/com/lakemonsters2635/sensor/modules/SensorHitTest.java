package com.lakemonsters2635.sensor.modules;

import com.lakemonsters2635.sensor.interfaces.ISensor;
/**
 * Converts a Double returning ISensor into a Boolean returning ISensor
 * <br><br>
 * Sense returns true if sensed value is less than or equal to the upper limit 
 * and greater than or equal to the lower limit. Use cases include:<br>
 * Detecting if the robot is within a distance to a wall<br>
 * Detecting if a line follower is on a line or not<br>
 * Deciding whether or not to display the "low fuel" light in a car
 * @author Tristan Thompson
 */
public class SensorHitTest implements ISensor<Boolean>
{

	ISensor<Double> sensor;
	Double upperLimit;
	Double lowerLimit;
	
	
	
	public SensorHitTest(ISensor<Double> sensor, Double upperLimit,
			Double lowerLimit)
	{
		super();
		this.sensor = sensor;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
	}


	@Override
	public Boolean sense()
	{
		Double sensedValue = sensor.sense();		
		return sensedValue <= upperLimit && sensedValue >= lowerLimit; 
	}
 
}

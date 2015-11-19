package com.lakemonsters2635.sensor.interfaces;

/**
 * A simple interface for sensors. Implementation examples include: <br><br>
 * A gyroscope <br>
 * A sonar sensor
 * A vision sensor that detects things and returns a class describing that thing<br>
 * A sensor that detects cars
 * @author Tristan
 *
 * @param <OutputType> Type of sensed value.
 */
public interface ISensor<OutputType> 
{
	/**
	 * 
	 * @return Sensed value
	 */
	public OutputType sense();
}

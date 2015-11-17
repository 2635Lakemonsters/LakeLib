package com.lakemonsters2635.sensor.interfaces;

/**
 * A simple interface for sensors. Implementation examples include: <br><br>
 * A gyroscope <br>
 * A sonar sensor
 * A vision sensor that detects things and returns a class describing that thing<br>
 * A sensor that detects cars
 * @author Tristan
 *
 * @param <T> Type of sensed value.
 */
public interface ISensor<T>
{
	/**
	 * 
	 * @return Sensed value
	 */
	public T sense();
}

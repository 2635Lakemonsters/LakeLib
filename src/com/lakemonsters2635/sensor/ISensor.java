package com.lakemonsters2635.sensor;

/**
 * A simple interface for sensors.
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

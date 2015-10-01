package sensor;


public interface ISensor<T>
{
	/**
	 * 
	 * @return Sensed value
	 */
	public T sense();
}

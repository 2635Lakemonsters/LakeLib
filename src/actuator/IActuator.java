package actuator;

public interface IActuator<T>
{
	public boolean actuate(T magnitude);
}

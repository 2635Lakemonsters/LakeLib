package actuator;

public interface ITwoStateActuator<T>
{
	public boolean actuateForward(T magnitude);
	public boolean actuateBackwards(T magnitude);
}

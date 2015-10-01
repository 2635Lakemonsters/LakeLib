package actuator;

/**
 * An actuator that is programmatically pushed forward and backwards. Implementation examples include: <br><br>
 * A piston that locks itself one way, and must be unlocked to go the other way <br>
 * A motor that must switch into a forward or reverse gear when going forwards or backwards <br>
 * A simple car. 
 * @author Tristan
 *
 * @param <T>
 */
public interface ITwoStateActuator<T>
{
	public boolean actuateForward(T magnitude);
	public boolean actuateBackwards(T magnitude);
}

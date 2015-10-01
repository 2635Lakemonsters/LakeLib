package actuator;

import java.util.Timer;


//TODO: finish this up
public abstract class TimedActuator
{
	Timer timer;
	public abstract boolean actuateIn(int timeMilliseconds, double magnitude);
}

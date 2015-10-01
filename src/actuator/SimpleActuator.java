package actuator;

import edu.wpi.first.wpilibj.SpeedController;

public class SimpleActuator implements IActuator<Double>
{
	SpeedController actuator;
	
	public SimpleActuator(SpeedController actuator)
	{
		super();
		this.actuator = actuator;
	}

	@Override
	public boolean actuate(Double magnitude)
	{
		actuator.set(magnitude);
		return true;
	}

}

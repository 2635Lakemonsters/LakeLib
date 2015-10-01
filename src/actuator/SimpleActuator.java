package actuator;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * A simple actuator. Initializing the speed controller should be done outside this class. Actuating simply sets the actuator to a value. 
 * @author Tristan
 *
 */
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

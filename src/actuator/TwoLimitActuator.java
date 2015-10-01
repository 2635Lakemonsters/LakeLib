package actuator;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;

public class TwoLimitActuator implements IActuator<Double>
{
	SpeedController actuator;
	DigitalInput upperLimit;
	DigitalInput lowerLimit;
	public TwoLimitActuator(SpeedController actuator, DigitalInput upperLimit,
			DigitalInput lowerLimit)
	{
		super();
		this.actuator = actuator;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
	}

	@Override
	public boolean actuate(Double magnitude)
	{
		if(upperLimit.get())
		{
			if(magnitude <= 0)
			{
				actuator.set(magnitude);
			}
			else
			{
				actuator.set(0);
			}
		}
		else if(lowerLimit.get())
		{
			if(magnitude >= 0)
			{
				actuator.set(magnitude);
			}
			else
			{
				actuator.set(0);
			}
			
		}
		else
		{
			actuator.set(magnitude);
		}
		return true;
	}
	}

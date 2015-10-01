package actuator;

import edu.wpi.first.wpilibj.PIDController;

public class ClosedLoopActuator implements IActuator<Double>
{
	PIDController actuator;

	@Override
	public boolean actuate(Double magnitude)
	{
		actuator.setSetpoint(magnitude);
		return false;
	}
	
}

package actuator;

import edu.wpi.first.wpilibj.PIDController;

/**
 * Class for actuators that make use of the PIDController class. A quick module for motors that use encoders, etc. Actuating sets the setpoint of the PIDController.
 * @author Tristan
 *
 */
public class ClosedLoopActuator implements IActuator<Double>
{
	PIDController actuator;
	public ClosedLoopActuator(PIDController actuator)
	{
		super();
		this.actuator = actuator;
	}
	@Override
	public boolean actuate(Double magnitude)
	{
		actuator.setSetpoint(magnitude);
		return false;
	}

	
	
}

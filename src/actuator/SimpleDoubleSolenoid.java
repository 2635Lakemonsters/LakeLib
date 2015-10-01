package actuator;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * A boolean actuator. Good for pistons. Actuating true will set forward, actuating false will set reverse.
 * @author Tristan
 *
 */
public class SimpleDoubleSolenoid implements IActuator<Boolean>
{	
	DoubleSolenoid solenoid;
	
	public SimpleDoubleSolenoid(DoubleSolenoid solenoid)
	{
		super();
		this.solenoid = solenoid;
	}

	@Override
	public boolean actuate(Boolean magnitude)
	{
		//Set solenoid forward if true, reverse if false.
		solenoid.set(magnitude?Value.kForward:Value.kReverse);
		return true;
	}

}

package actuator;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

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
		solenoid.set(magnitude?Value.kForward:Value.kReverse);
		return true;
	}

}

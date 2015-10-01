package robotfunctions;

import actuator.IActuator;

/**
 * A rotary cannon. Rotates at a specified magnitude after firing
 * @author Tristan
 *
 */
public class RotaryCannon extends SimpleLauncher
{
	IActuator<Double> rotator;
	public void fire(double fireMagnitude, double rotatorMagnitude)
	{
		accelerator.actuate(fireMagnitude);
		rotator.actuate(rotatorMagnitude);
	}

}

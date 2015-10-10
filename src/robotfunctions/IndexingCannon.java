package robotfunctions;

import actuator.IActuator;

/**
 * A rotary cannon. Rotates an indexer at a specified magnitude after firing.
 * @author Tristan
 *
 */
public class IndexingCannon extends SimpleLauncher
{
	IActuator<Double> rotator;
	public void fire(double fireMagnitude, double rotatorMagnitude)
	{
		accelerator.actuate(fireMagnitude);
		rotator.actuate(rotatorMagnitude);
	}

}

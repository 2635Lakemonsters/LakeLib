package com.lakemonsters2635.composites;

import com.lakemonsters2635.actuator.interfaces.IActuator;

/**
 * A rotary cannon. Rotates an indexer at a specified magnitude after firing.
 * @author Tristan
 *
 */
public class IndexingCannon extends SimpleLauncher
{
	IActuator<Double> rotator;
	/**
	 * 
	 * @param accelerator Thing that makes the thing go fast.
	 * @param elevator Thing that moves the launcher.
	 * @param rotator Thing that rotates the launcher.
	 */
	public IndexingCannon(IActuator<Double> accelerator,
			IActuator<Double> elevator, IActuator<Double> rotator)
	{
		super(accelerator, elevator);
		this.rotator = rotator;
	}
	/**
	 * Fire and rotate the cannon
	 * @param fireMagnitude Modify the firing module
	 * @param rotatorMagnitude Modify the rotational module
	 */
	public void fire(double fireMagnitude, double rotatorMagnitude)
	{
		accelerator.actuate(fireMagnitude);
		rotator.actuate(rotatorMagnitude);
	}

}

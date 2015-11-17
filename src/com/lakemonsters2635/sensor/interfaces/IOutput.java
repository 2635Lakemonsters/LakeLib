package com.lakemonsters2635.sensor.interfaces;

/**
 * An interface that allows for more control over an output device. Allows for an output to be modified by an input. Implementation examples include: <br><br>
 * A sensor whose outputs are scaled by a double value <br>
 * A sensor whose functionality can be changed by an input value <br>
 * A linear node based system, where each node is influenced by the node ahead of it and influences the node behind it<br>
 * A car that returns feedback data
 * @author Tristan
 *
 * @param <OutputType> Type of output
 * @param <InputType> Type of magnitude
 */
public interface IOutput<OutputType, InputType>
{
	/**
	 * 
	 * @param magnitude Value to modify output
	 * @return Modified output
	 */
	OutputType getOutput(InputType magnitude);
}

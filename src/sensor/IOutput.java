package sensor;

/**
 * 
 * @author Lake Monsters 2635
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

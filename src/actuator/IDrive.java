package actuator;

/**
 * A drive interface. Implementation examples include: <br>
 * A drive system driven by a PIDController. <br>
 * A car <br>
 * 
 * @author Tristan Thompson
 *
 */
@Deprecated 
public interface IDrive 
{
	public boolean drive(double X, double Y, double rotation);
}

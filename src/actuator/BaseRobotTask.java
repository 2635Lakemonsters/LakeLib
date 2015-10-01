package actuator;

import java.util.TimerTask;
/**
 * An extension off of TimerTask that allows the programmer to find out if the task has run. 
 * Classes extending off this class should run this class's run after implementing thier logic.
 * @author Tristan Thompson
 *
 */
public class BaseRobotTask extends TimerTask
{
	public boolean hasRun = false;

	@Override
	public void run() 
	{
		hasRun = true;
	}
	
}

package robotfunctions;

import edu.wpi.first.wpilibj.RobotDrive;
import actuator.IActuator;

public class HDrivePneumatic extends HDrive
{
	IActuator<Boolean> piston;
	double depressionTolerance;
	public HDrivePneumatic(RobotDrive drive, IActuator<Double> middleWheel,
			IActuator<Boolean> piston, double depressionTolerance ) {
		super(drive, middleWheel);
		this.piston = piston;
		this.depressionTolerance = depressionTolerance;
	}
	@Override
	public void drive(double X, double Y, double rotation)
	{
		if(Math.abs(X) > depressionTolerance)
		{
			
		}
	}
}

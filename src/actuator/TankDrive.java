package actuator;

import edu.wpi.first.wpilibj.RobotDrive;

public class TankDrive implements IDrive
{

	RobotDrive drive;
	public TankDrive(RobotDrive drive)
	{
		super();
		this.drive = drive;
	}
	@Override
	public boolean drive(double X, double Y)
	{
		drive.tankDrive(X,Y);
		return true;
	}

}

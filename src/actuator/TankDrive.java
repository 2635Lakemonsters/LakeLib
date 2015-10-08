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
	public boolean drive(double X, double Y, double rotation)
	{
		drive.tankDrive(X,Y);
		// TODO Auto-generated method stub
		return true;
	}

}

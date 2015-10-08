package actuator;

import edu.wpi.first.wpilibj.RobotDrive;

public class ArcadeDrive implements IDrive
{

	RobotDrive drive;
	public ArcadeDrive(RobotDrive drive)
	{
		super();
		this.drive = drive;
	}
	@Override
	public boolean drive(double X, double Y, double rotation)
	{
		drive.arcadeDrive(Y, rotation);
		return true;
	}

}

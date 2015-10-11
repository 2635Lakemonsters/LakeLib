package com.lakemonsters2635.actuator.modules;

import com.lakemonsters2635.actuator.interfaces.IDrive;

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
	public boolean drive(double X, double Y)
	{
		drive.arcadeDrive(Y, X);
		return true;
	}

}

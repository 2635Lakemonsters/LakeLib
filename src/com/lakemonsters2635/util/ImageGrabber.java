package com.lakemonsters2635.util;

import java.util.Timer;
import java.util.TimerTask;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;

public class ImageGrabber
{
	NIVision.Image image;
	int session;
	boolean outputToDashboard;
	CameraServer server;
	Timer cameraTimer;
	public ImageGrabber(int session, long grabRate, boolean outputToDashboard)
	{
		image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		this.session = session;
		
		this.outputToDashboard = outputToDashboard;
		server = CameraServer.getInstance();
		NIVision.IMAQdxConfigureGrab(session);
        NIVision.IMAQdxStartAcquisition(session);
		cameraTimer = new Timer();
		cameraTimer.schedule(new CameraTask(this), 0L, grabRate);
	}
	public ImageGrabber(int session, boolean outputToDashboard)
	{
		this(session, 50L, outputToDashboard);
	}
	public class CameraTask extends TimerTask
	{
		ImageGrabber imageGrabber;
		public CameraTask(ImageGrabber imageGrabber)
		{
			this.imageGrabber = imageGrabber;
		}
		@Override
		public void run()
		{
			imageGrabber.runCamera();
		}
		
	}
	public synchronized NIVision.Image getImage()
	{
		return image;
	}
	public void runCamera()
	{
		synchronized(this)
		{
			NIVision.IMAQdxGrab(session, image, 1);
		}
    	if(outputToDashboard)
    	{
    		server.setImage(image);
    	}

	}
}

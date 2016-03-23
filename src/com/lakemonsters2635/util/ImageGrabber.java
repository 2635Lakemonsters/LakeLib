package com.lakemonsters2635.util;

import java.util.Timer;
import java.util.TimerTask;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.FlipAxis;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;

public class ImageGrabber
{
	public enum ImageMode
	{
		COLOR_DETECT,
		NORMAL
	}
	ImageMode mode;
	NIVision.Range HUE_RANGE; //= new NIVision.Range(0, 5);	//Default hue range for yellow tote
	NIVision.Range SAT_RANGE; //= new NIVision.Range(0, 10);	//Default saturation range for yellow tote
	NIVision.Range VAL_RANGE; //= new NIVision.Range(250, 255);	//Default value range for yellow tote

	NIVision.Image image;
	NIVision.Image bwImage;
	int session;
	boolean outputToDashboard;
	boolean flip;
	CameraServer server;
	Timer cameraTimer;
	public ImageGrabber(int session, long grabRate, boolean outputToDashboard, boolean flip, ImageMode startingMode, NIVision.Range HUE_RANGE, NIVision.Range SAT_RANGE, NIVision.Range VAL_RANGE)
	{
		image = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		bwImage = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		mode = startingMode;
		this.session = session;
		this.outputToDashboard = outputToDashboard;
		this.flip = flip;
		server = CameraServer.getInstance();
		NIVision.IMAQdxConfigureGrab(session);
        NIVision.IMAQdxStartAcquisition(session);
		cameraTimer = new Timer();
		cameraTimer.schedule(new CameraTask(this), 0L, grabRate);
		this.HUE_RANGE = HUE_RANGE;
		this.SAT_RANGE = SAT_RANGE;
		this.VAL_RANGE = VAL_RANGE;
	}
	public ImageGrabber(int session, boolean outputToDashboard, boolean flip, ImageMode startingMode, NIVision.Range HUE_RANGE, NIVision.Range SAT_RANGE, NIVision.Range VAL_RANGE)
	{
		this(session, 50L, outputToDashboard, flip, startingMode, HUE_RANGE, SAT_RANGE, VAL_RANGE);
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
	public synchronized void setMode(ImageMode mode)
	{
		this.mode = mode;
	}
	public void runCamera()
	{
		synchronized(this)
		{
			NIVision.IMAQdxGrab(session, image, 1);
			if(mode == ImageMode.COLOR_DETECT)
			{
				NIVision.imaqColorThreshold(bwImage, image, 255, ColorMode.HSV, HUE_RANGE, SAT_RANGE, VAL_RANGE);
			}
			if(flip)
			{
				if(mode == ImageMode.COLOR_DETECT)
				{
					NIVision.imaqFlip(bwImage, bwImage, FlipAxis.HORIZONTAL_AXIS);
				}
				else
				{
					NIVision.imaqFlip(image, image, FlipAxis.HORIZONTAL_AXIS);
				}
			}
		}
    	if(outputToDashboard)
    	{
    		if(mode == ImageMode.COLOR_DETECT)
    		{
    			server.setImage(bwImage);
    		}
    		else
    		{
    			server.setImage(image);
    		}
    	}

	}
}

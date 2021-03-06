package com.lakemonsters2635.util;

import java.util.Comparator;
import java.util.Timer;
import java.util.TimerTask;

import com.lakemonsters2635.sensor.modules.SensorTargetAngleFromImage.ParticleReport;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.FlipAxis;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ImageGrabber
{
	public enum ImageMode
	{
		COLOR_DETECT, NORMAL
	}

	public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport>
	{
		double Area;
		double AreaByImageArea;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectWidth;
		double BoundingRectHeight;

		public int compareTo(ParticleReport r)
		{
			return (int) (r.AreaByImageArea - this.AreaByImageArea);
		}

		public int compare(ParticleReport r1, ParticleReport r2)
		{
			return (int) (r1.AreaByImageArea - r2.AreaByImageArea);
		}
	};

	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0, 0, 1, 1);

	ImageMode mode;
	NIVision.Range HUE_RANGE; // = new NIVision.Range(0, 5); //Default hue range
								// for yellow tote
	NIVision.Range SAT_RANGE; // = new NIVision.Range(0, 10); //Default
								// saturation range for yellow tote
	NIVision.Range VAL_RANGE; // = new NIVision.Range(250, 255); //Default value
								// range for yellow tote

	NIVision.Image image;
	NIVision.Image bwImage;
	int session;
	boolean outputToDashboard;
	boolean flip;
	CameraServer server;
	Timer cameraTimer;

	public ImageGrabber(int session, long grabRate, boolean outputToDashboard, boolean flip, ImageMode startingMode,
			NIVision.Range HUE_RANGE, NIVision.Range SAT_RANGE, NIVision.Range VAL_RANGE)
	{
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, 0.5, 100.0,
				0, 0);

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

	public ImageGrabber(int session, boolean outputToDashboard, boolean flip, ImageMode startingMode,
			NIVision.Range HUE_RANGE, NIVision.Range SAT_RANGE, NIVision.Range VAL_RANGE)
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

	public synchronized void setHueRange(NIVision.Range hueRange)
	{
		this.HUE_RANGE = hueRange;
	}

	public synchronized void setSatRange(NIVision.Range satRange)
	{
		this.SAT_RANGE = satRange;
	}

	public synchronized void setValRange(NIVision.Range valRange)
	{
		this.VAL_RANGE = valRange;
	}

	public void runCamera()
	{
		synchronized (this)
		{
			try
			{
				NIVision.IMAQdxGrab(session, image, 1);
				if(flip)
				{
					NIVision.imaqFlip(image, image, FlipAxis.HORIZONTAL_AXIS);
					NIVision.imaqFlip(image, image, FlipAxis.VERTICAL_AXIS);
				}
				NIVision.imaqColorThreshold(bwImage, image, 255, ColorMode.HSV, HUE_RANGE, SAT_RANGE, VAL_RANGE);


				if (mode == ImageMode.COLOR_DETECT)
				{
					server.setImage(bwImage);
					return;
				}
				// Look at the color frame for colors that fit the range. Colors
				// that fit the range will be transposed as a 1 to the binary
				// frame.
				// NIVision.imaqColorThreshold(bwImage, image, 255,
				// ColorMode.HSV, HUE_RANGE, SAT_RANGE, VAL_RANGE);
				// Filter out particles that are too small
				NIVision.imaqParticleFilter4(bwImage, bwImage, criteria, filterOptions, null);

				int numParticles = NIVision.imaqCountParticles(bwImage, 1);
				if (numParticles > 0)
				{
					ParticleReport bestParticle = null;
					// Vector<ParticleReport> particles = new
					// Vector<ParticleReport>();
					for (int particleIndex = 0; particleIndex < numParticles; particleIndex++)
					{
						ParticleReport par = new ParticleReport();
						par.Area = NIVision.imaqMeasureParticle(bwImage, particleIndex, 0,
								NIVision.MeasurementType.MT_AREA);
						par.AreaByImageArea = NIVision.imaqMeasureParticle(bwImage, particleIndex, 0,
								NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
						par.BoundingRectTop = NIVision.imaqMeasureParticle(bwImage, particleIndex, 0,
								NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
						par.BoundingRectLeft = NIVision.imaqMeasureParticle(bwImage, particleIndex, 0,
								NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
						par.BoundingRectHeight = NIVision.imaqMeasureParticle(bwImage, particleIndex, 0,
								NIVision.MeasurementType.MT_BOUNDING_RECT_HEIGHT);
						par.BoundingRectWidth = NIVision.imaqMeasureParticle(bwImage, particleIndex, 0,
								NIVision.MeasurementType.MT_BOUNDING_RECT_WIDTH);
						if (bestParticle == null
								|| Math.abs(par.BoundingRectHeight / par.BoundingRectWidth - 14.0 / 20.0) < Math.abs(
										bestParticle.BoundingRectHeight / bestParticle.BoundingRectWidth) - 14.0 / 20.0)
						{
							bestParticle = par;
						}
						// particles.add(par);

					}
					// particles.sort(null);
					NIVision.imaqDrawShapeOnImage(image, image,
							new NIVision.Rect((int) bestParticle.BoundingRectTop, (int) bestParticle.BoundingRectLeft,
									(int) bestParticle.BoundingRectHeight, (int) bestParticle.BoundingRectWidth),
							DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 0);

				} else
				{

				}

				server.setImage(image);

			} catch (Exception ex)
			{
				System.out.println("Some stupid vision error happened");
			}

		}
	}
}

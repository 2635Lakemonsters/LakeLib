package com.lakemonsters2635.sensor.modules;

import java.util.Comparator;
import java.util.Vector;

import com.lakemonsters2635.sensor.interfaces.IOutput;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OutputAngleFromImage implements IOutput <Double, NIVision.Image> {
	double CAMERA_RESOLUTION_X;
	double VIEW_ANGLE;
	NIVision.Range HUE_RANGE; //= new NIVision.Range(0, 5);	//Default hue range for yellow tote
	NIVision.Range SAT_RANGE; //= new NIVision.Range(0, 10);	//Default saturation range for yellow tote
	NIVision.Range VAL_RANGE; //= new NIVision.Range(250, 255);	//Default value range for yellow tote
	Double AREA_MINIMUM;
	NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
	Image binaryImage;
	
	public OutputAngleFromImage(double CAMERA_RESOLUTION_X, double VIEW_ANGLE,
			NIVision.Range hueRange, NIVision.Range saturationRange, NIVision.Range valueRange, double particleAreaMinimum)
	{
		super();
		HUE_RANGE = hueRange;
		SAT_RANGE = saturationRange;
		VAL_RANGE = valueRange;
		AREA_MINIMUM = particleAreaMinimum;
		this.CAMERA_RESOLUTION_X = CAMERA_RESOLUTION_X;
		this.VIEW_ANGLE = VIEW_ANGLE;
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, AREA_MINIMUM, 100.0, 0, 0);
		binaryImage = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);

	}
	public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport>{
		double Area;
		double AreaByImageArea;
		double BoundingRectLeft;
		double BoundingRectTop;
		double BoundingRectWidth;
		double BoundingRectHeight;
		
		public int compareTo(ParticleReport r)
		{
			return (int)(r.AreaByImageArea - this.AreaByImageArea);
		}
		
		public int compare(ParticleReport r1, ParticleReport r2)
		{
			return (int)(r1.AreaByImageArea - r2.AreaByImageArea);
		}
	};

		@Override
	public Double getOutput(Image image) 
	{
		//Look at the color frame for colors that fit the range. Colors that fit the range will be transposed as a 1 to the binary frame.
		NIVision.imaqColorThreshold(binaryImage, image, 255, ColorMode.HSV, HUE_RANGE, SAT_RANGE, VAL_RANGE);
		//Filter out particles that are too small
        NIVision.imaqParticleFilter4(binaryImage, binaryImage, criteria, filterOptions, null);
		
        int numParticles = NIVision.imaqCountParticles(binaryImage, 1);        
		if(numParticles > 0)
		{
			Vector<ParticleReport> particles = new Vector<ParticleReport>();
			for(int particleIndex = 0; particleIndex < numParticles; particleIndex++)
			{
				ParticleReport par = new ParticleReport();
				par.Area = NIVision.imaqMeasureParticle(binaryImage, particleIndex, 0, NIVision.MeasurementType.MT_AREA);
				par.AreaByImageArea = NIVision.imaqMeasureParticle(binaryImage, particleIndex, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
				par.BoundingRectTop = NIVision.imaqMeasureParticle(binaryImage, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_TOP);
				par.BoundingRectLeft = NIVision.imaqMeasureParticle(binaryImage, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_LEFT);
				par.BoundingRectHeight = NIVision.imaqMeasureParticle(binaryImage, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_HEIGHT);
				par.BoundingRectWidth = NIVision.imaqMeasureParticle(binaryImage, particleIndex, 0, NIVision.MeasurementType.MT_BOUNDING_RECT_WIDTH);
				particles.add(par);
	
			}
			particles.sort(null);


			ParticleReport bestParticle = particles.elementAt(0);
	    
			double bestParticleMidpoint = bestParticle.BoundingRectLeft + bestParticle.BoundingRectWidth/2.0;
			double bestParticleMidpointAimingCoordnates = pixelCoordnateToAimingCoordnate(bestParticleMidpoint, CAMERA_RESOLUTION_X);
			return aimingCoordnateToAngle(bestParticleMidpointAimingCoordnates, VIEW_ANGLE);
		
		}
		else
		{
			return 0.0;
		}
	}
    public double pixelCoordnateToAimingCoordnate(double pixelCoordnate, double resolution)
    {

    	return (pixelCoordnate - resolution / 2.0) / (resolution / 2.0);
    }
    public double aimingCoordnateToAngle(double aimingCoordnate, double viewingAngle)
    {
    	return aimingCoordnate * viewingAngle/2.0;
    }

		
}

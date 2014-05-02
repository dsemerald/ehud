package algorithms;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import ehud.Radio;
import ehud.World;

/**
 * Purpose of this algorithm is to maximize the coverage area without regard to battery usage
 * @author Emerald Dsouza
 *
 */
public class GreedyCoverage implements Algorithm {
	
	int boundingBoxDistance = 100;

	@Override
	public int preStep(int frameNumber, World world, Radio radio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int step(int frameNumber, World world, Radio radio) {
		ArrayList<Radio> neighbors = radio.getNeighbours(world);
		if(neighbors.size()>2){
			radio.setActiveFrame(frameNumber + 4);
			radio.setRadioState(false);
			log.debug("Too many overlapping radios. Turning off radio "+radio.getCellID() + " to conserve power");
		}
		return 0;
	}

	@Override
	public int postStep(int frameNumber, World world, Radio radio) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * What distance should we consider for neighbours
	 * @return
	 */
	public int getBoundingBoxDistance() {
		return boundingBoxDistance;
	}

	public void setBoundingBoxDistance(int boundingBoxDistance) {
		this.boundingBoxDistance = boundingBoxDistance;
	}

}

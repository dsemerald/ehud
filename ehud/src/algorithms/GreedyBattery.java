package algorithms;

import java.util.ArrayList;

import ehud.Radio;
import ehud.World;

/**
 * Purpose of this class if to maximize the battery usage
 * @author Emerald Dsouza
 *
 */
public class GreedyBattery implements Algorithm {

	@Override
	public int preStep(int frameNumber, World world, Radio radio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int step(int frameNumber, World world, Radio radio) {
		ArrayList<Radio> neighbors = radio.getNeighbours(world);
		if(neighbors.size()>3){
			radio.setActiveFrame(frameNumber + 4);
			radio.setRadioState(false);
			log.debug("Too many overlapping radios. Turning off radio "+radio.getCellID() + " to conserve power");
		}
		else{
			radio.setMaxTransmissionPower(150);
			for(Radio r:neighbors){
				double pythogorasXDist = Math.pow(radio.getxCoord() - r.getxCoord(), 2);
				double pythogorasYDist = Math.pow(radio.getyCoord() - r.getyCoord(), 2);
				double pythogorasDistance = pythogorasXDist+pythogorasYDist;
				double maxDistance = Math.sqrt(pythogorasDistance)/2;
				if(maxDistance<r.getMaxTransmissionPower()){
					// we are within the other circle
					radio.setActiveFrame(frameNumber + 4);
					radio.setRadioState(false);
					log.debug("Too many overlapping radios. Turning off radio "+radio.getCellID() + " to conserve power");
				}
				else{
					//we set out tranmission power accordingly
					if(radio.getMaxTransmissionPower()>maxDistance){
						radio.setMaxTransmissionPower(maxDistance);
					}
				}
			}
		}
		return 0;
	}

	@Override
	public int postStep(int frameNumber, World world, Radio radio) {
		// TODO Auto-generated method stub
		return 0;
	}

}

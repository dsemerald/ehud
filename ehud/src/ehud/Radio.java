package ehud;

import java.awt.Color;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import algorithms.Algorithm;

/**
 * Defines a radio element examples of which are a mobile phone or a
 * transmission tower
 * 
 * @author Emerald Dsouza
 * 
 */
@XmlRootElement(name = "radio")
@XmlAccessorType(XmlAccessType.FIELD)
public class Radio extends GridElement {
	
	static Logger log = Logger.getLogger(
            Radio.class.getName());

	int[] allowedMainFrequencies = { 200, 201, 202, 203 };
	int[] allowedFracFrequencies = { 24, 48, 72 };
	
	private Algorithm algorithm;

	/** The physical cell id **/
	@XmlElement
	int cellID = -1;
	/**
	 * maintains if radio is active
	 */
	@XmlElement(defaultValue="false")
	boolean radioState = false;
	/**
	 * From which step of the simulation does this radio become active
	 */
	@XmlElement
	int activeFrame = 9999;

	boolean initialized = false; // keep track if we have run setup

	@XmlPath(value = "/radio/frequency/main/text()")
	int freqMain = 0;
	@XmlPath(value = "/radio/frequency/fractional/text()")
	int freqFrac = 0;
	
	@XmlPath(value = "/radio/antenna/maxTransmissionPower/text()")
	double maxTransmissionPower = 150;

	/**
	 * What all should be done when the radio is switched on
	 * 
	 * @param w
	 * @return
	 */
	public int setup(World w) {
		if(freqMain==0){
			freqMain=200;
		}
		if(freqFrac==0){
			freqFrac=24;
		}
		try{
			Class klass = Class.forName(w.getAlgorithm());
			algorithm = (Algorithm) klass.newInstance();
		}
		catch(ClassNotFoundException ex){
			log.error("Could not load algorithm class");
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialized = true;
		return 0; // TODO: proper return value
	}

	/**
	 * What should the radio do in this step
	 * 
	 * @return
	 */
	public int step(int frameNumber, World w) {
		
		if (!initialized) {
			setup(w);
		}
		if (!radioState) {
			radioState = activeFrame == frameNumber ? true : false;
			radioState = activeFrame == 0? true:radioState;
			if(radioState){
				log.debug("Radio "+cellID+" has become active ");
			}
		} else { // radio is active
			algorithm.preStep(frameNumber, w, this);
			algorithm.step(frameNumber, w, this);
			checkFrequencyClash(w);
			algorithm.postStep(frameNumber, w, this);
		}
		return 0;
	}

	/**
	 * Check if neighbour and me are using same frequency
	 * 
	 * @param w
	 */
	private void checkFrequencyClash(World w) {
		ArrayList<Radio> neighbors = getNeighbours(w);
		for (Radio r : neighbors) {
			if (r.getFreqMain() == this.getFreqMain()
					&& r.getFreqFrac() == this.getFreqFrac()) {
				freqMain+=1;
				freqFrac+=24;
				if(freqMain>203){freqMain=200;}
				if(freqFrac>72){freqFrac=24;}
				log.debug("Updating frequency of "+cellID+" to "+getFreqMain()+"."+r.getFreqFrac()+"MHz");
			}
		}
	}

	/**
	 * Returns list of neighbours
	 * 
	 * @param w
	 * 
	 * @return
	 */
	public ArrayList<Radio> getNeighbours(World w) {
		ArrayList<Radio> neighbors = new ArrayList<Radio>();
		int[] boundingBox = getBoundingBox(this);
		for (Radio r : w.getRadioElements()) {
			if(r.getCellID()==cellID){
				// do nothing its just us
				continue;
			}
			if(!r.isRadioState()){
				continue;
			}
			if (r.getxCoord() >= boundingBox[0]
					&& r.getxCoord() <= boundingBox[1]) {
				if (r.getyCoord() >= boundingBox[2]
						&& r.getyCoord() <= boundingBox[3]) {
					if (r.isRadioState()) {
						neighbors.add(r);
					}
				}
			}
			// loop optimization as we have a sorted list
			if (r.getxCoord() > boundingBox[1]
					&& r.getyCoord() > boundingBox[3]) {
				break;
			}
		}
		return neighbors;
	}
	

	/**
	 * Returns the min(x,y) and max(x,y) that forms a box. If another radio has
	 * its center in the box it is considered a neighbour
	 * 
	 * @param radio
	 * @return
	 */
	private int[] getBoundingBox(Radio r) {
		int d = algorithm.getBoundingBoxDistance(); // TODO: change this later
		int minX = (int) Math.floor(r.getxCoord() - d);
		int minY = (int) Math.floor(r.getyCoord() - d);
		int maxX = (int) Math.floor(r.getxCoord() + d);
		int maxY = (int) Math.floor(r.getyCoord() + d);
		int[] box = { minX, maxX, minY, maxY };
		return box;
	}

	/**
	 * Returns a color based on frequency
	 * 
	 * @return
	 */
	public Color getColor() {
		Color c = Color.blue;
		switch (this.freqMain) {
		case 200: {
			switch (this.freqFrac) {
			case 24: c = Color.decode("0x33CC33"); break;
			case 48: c = Color.decode("0x009933"); break;
			case 72: c = Color.decode("0x669900"); break;
			default: c = Color.yellow;
			}
			break;
		}
		case 201: {
			switch (this.freqFrac) {
			case 24: c = Color.decode("0x3399FF"); break;
			case 48: c = Color.decode("0x0000FF"); break;
			case 72: c = Color.decode("0x3366CC"); break;
			default: c = Color.yellow;
			}
			break;
		}
		case 202: {
			switch (this.freqFrac) {
			case 24: c = Color.decode("0xFF00FF"); break;
			case 48: c = Color.decode("0xFF0066"); break;
			case 72: c = Color.decode("0x6600CC"); break;
			default: c = Color.yellow;
			}
			break;
		}
		case 203: {
			switch (this.freqFrac) {
			case 24: c = Color.decode("0x663300"); break;
			case 48: c = Color.decode("0xCC6600"); break;
			case 72: c = Color.decode("0xCCCC00"); break;
			default: c = Color.yellow;
			}
			break;
		}
		default: c = Color.orange; 
		}
		return c;
	}

	/*
	 * Getter and setters
	 */
	public int getCellID() {
		return cellID;
	}

	public void setCellID(int cellID) {
		this.cellID = cellID;
	}

	public boolean isRadioState() {
		return radioState;
	}

	public void setRadioState(boolean radioState) {
		this.radioState = radioState;
	}

	public int getActiveFrame() {
		return activeFrame;
	}

	public void setActiveFrame(int activeFrame) {
		this.activeFrame = activeFrame;
	}

	public int getFreqMain() {
		return freqMain;
	}

	public void setFreqMain(int freqMain) {
		this.freqMain = freqMain;
	}

	public int getFreqFrac() {
		return freqFrac;
	}

	public void setFreqFrac(int freqFrac) {
		this.freqFrac = freqFrac;
	}

	public double getMaxTransmissionPower() {
		return maxTransmissionPower;
	}

	public void setMaxTransmissionPower(double maxTransmissionPower) {
		this.maxTransmissionPower = maxTransmissionPower;
	}

}

package ehud;

import java.awt.Color;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

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

	int[] allowedMainFrequencies = { 200, 201, 202, 203 };
	int[] allowedFracFrequencies = { 24, 48, 67 };

	/** The physical cell id **/
	@XmlElement
	int cellID = -1;
	/**
	 * maintains if radio is active
	 */
	@XmlElement
	boolean radioState = false;
	/**
	 * From which step of the simulation does this radio become active
	 */
	@XmlElement
	int activeFrame = 9999;

	boolean initialized = false; // keep track if we have run setup

	@XmlPath(value = "/radio/frequency/main")
	int freqMain = 0;
	@XmlPath(value = "/radio/frequency/fractional")
	int freqFrac = 0;

	/**
	 * What all should be done when the radio is switched on
	 * 
	 * @param w
	 * @return
	 */
	public int setup(World w) {
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
		} else { // radio is active
			checkFrequencyClash(w);
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
				// clash choose another main freq

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
	private ArrayList<Radio> getNeighbours(World w) {
		ArrayList<Radio> neighbors = new ArrayList<Radio>();
		int[] boundingBox = getBoundingBox(this);
		for (Radio r : w.getRadioElements()) {
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
		int d = 100; // TODO: change this later
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
			case 24: c = Color.decode("33CC33"); break;
			case 48: c = Color.decode("009933"); break;
			case 76: c = Color.decode("669900"); break;
			}
			break;
		}
		case 201: {
			switch (this.freqFrac) {
			case 24: c = Color.decode("3399FF"); break;
			case 48: c = Color.decode("0000FF"); break;
			case 76: c = Color.decode("3366CC"); break;
			}
			break;
		}
		case 202: {
			switch (this.freqFrac) {
			case 24: c = Color.decode("FF00FF"); break;
			case 48: c = Color.decode("FF0066"); break;
			case 76: c = Color.decode("6600CC"); break;
			}
			break;
		}
		case 203: {
			switch (this.freqFrac) {
			case 24: c = Color.decode("663300"); break;
			case 48: c = Color.decode("CC6600"); break;
			case 76: c = Color.decode("CCCC00"); break;
			}
			break;
		}
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

}

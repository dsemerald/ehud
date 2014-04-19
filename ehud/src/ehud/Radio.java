package ehud;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Defines a radio element examples of which are a mobile phone or a transmission tower
 * @author Emerald Dsouza
 *
 */
@XmlRootElement(name = "radio")
@XmlAccessorType(XmlAccessType.FIELD)
public class Radio extends GridElement {
	
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
	
	/**
	 * What should the radio do in this step
	 * @return
	 */
	public int step(int frameNumber){
		if(!radioState){
			radioState = activeFrame==frameNumber?true:false;
		}
		return 0;
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
	
	
}

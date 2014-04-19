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
	boolean radioState = false;
	/**
	 * From which step of the simulation does this radio become active
	 */
	int activeFrame = 9999;
	
	
	
	/*
	 * Getter and setters
	 */
	public int getCellID() {
		return cellID;
	}
	public void setCellID(int cellID) {
		this.cellID = cellID;
	}
	
	
}

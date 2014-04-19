/**
 * 
 */
package ehud;

import javax.xml.bind.annotation.XmlElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

/**
 * Represents an element on a grid with x and y cordinates
 * @author Emerald Dsouza
 */
public abstract class GridElement {
	/**
	 * x-Coordinate of the center
	 */
	@XmlPath(value="coordinates/x/text()")
	float xCoord;
	/**
	 * y-Coordinate of the center
	 */
	@XmlPath(value="coordinates/y/text()")
	float yCoord;
	
	public float getxCoord() {
		return xCoord;
	}
	public void setxCoord(float xCoord) {
		this.xCoord = xCoord;
	}
	public float getyCoord() {
		return yCoord;
	}
	public void setyCoord(float yCoord) {
		this.yCoord = yCoord;
	}
}

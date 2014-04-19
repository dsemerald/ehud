/**
 * 
 */
package ehud;

/**
 * Represents an element on a grid with x and y cordinates
 * @author Emerald Dsouza
 */
public abstract class GridElement {
	/**
	 * x-Coordinate of the center
	 */
	float xCoord;
	/**
	 * y-Coordinate of the center
	 */
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

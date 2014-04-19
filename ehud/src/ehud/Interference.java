package ehud;

/**
 * Interference does not allow radio propagation. These are are blind spots on
 * the grid of the world. For simplicity we treat interference as a circle
 * 
 * @author Emerald Dsouza
 * 
 */
public class Interference {
	/**
	 * x-Coordinate of the center
	 */
	float xCoord;
	/**
	 * y-Coordinate of the center
	 */
	float yCoord;
	/**
	 * How big is the blind spot
	 */
	float radius;
	
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
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	
	
}

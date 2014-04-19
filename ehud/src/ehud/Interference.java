package ehud;

/**
 * Interference does not allow radio propagation. These are are blind spots on
 * the grid of the world. For simplicity we treat interference as a circle
 * 
 * @author Emerald Dsouza
 * 
 */
public class Interference extends GridElement {
	
	/**
	 * How big is the blind spot
	 */
	float radius;
	
	
	public float getRadius() {
		return radius;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	
	
	
}

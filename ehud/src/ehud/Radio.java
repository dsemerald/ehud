package ehud;

/**
 * Defines a radio element examples of which are a mobile phone or a transmission tower
 * @author Emerald Dsouza
 *
 */
public class Radio {
	/**
	 * x-Coordinate of the center
	 */
	float xCoord;
	/**
	 * y-Coordinate of the center
	 */
	float yCoord;
	/** The physical cell id **/
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
	public float getxCoord() {
		return xCoord;
	}
	public void setxCoord(float xCord) {
		this.xCoord = xCord;
	}
	public float getyCoord() {
		return yCoord;
	}
	public void setyCoord(float yCoord) {
		this.yCoord = yCoord;
	}
	public int getCellID() {
		return cellID;
	}
	public void setCellID(int cellID) {
		this.cellID = cellID;
	}
	
	
}

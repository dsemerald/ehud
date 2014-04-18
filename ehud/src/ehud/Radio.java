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
	int cellID;
	
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

package ehud;

/**
 * Defines a radio element examples of which are a mobile phone or a transmission tower
 * @author Emerald Dsouza
 *
 */
public class Radio extends GridElement {
	
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
	public int getCellID() {
		return cellID;
	}
	public void setCellID(int cellID) {
		this.cellID = cellID;
	}
	
	
}

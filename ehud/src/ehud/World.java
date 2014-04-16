package ehud;

import java.util.ArrayList;
import java.util.List;

/**
 * The world consists of radio elements and interference elements all defined in
 * an xml file. The world can be considered a rectangular grid to which we map
 * the elements mentioned above.
 * 
 * @author Emerald Dsouza
 * 
 */
public class World {
	/**
	 * The radio elements are sorted by x-coordinates first then by the
	 * y-coordinates. This makes search easy
	 */
	List<Radio> radioElements = new ArrayList<Radio>();
	/**
	 * The interference elements are sorted by x-coordinates first then by the
	 * y-coordinates. This makes search easy
	 */
	List<Interference> interferenceElements = new ArrayList<Interference>();
	
	/**
	 * Loads the world with members
	 * @return 0 is load unsuccessful
	 * @return 1 if load successful
	 */
	int initialize(){
		
		return 0;
	}
}

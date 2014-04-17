package ehud.comparators;

import java.util.Comparator;
import ehud.Radio;

/**
 * Compares two radio elements to see where they are on the grid
 * @author Emerald Dsouza
 *
 */
public class AxisSorter implements Comparator<Radio> {

	@Override
	public int compare(Radio r1, Radio r2) {
		if(r1.getxCoord()==r2.getxCoord()){
			return (r1.getyCoord() < r2.getyCoord())? 0 : 1;
		}
		else if(r1.getxCoord() < r2.getxCoord()){
			return 0;
		}
		return 1;
	}

}

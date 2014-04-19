package ehud.comparators;

import java.util.Comparator;

import ehud.GridElement;
import ehud.Radio;

/**
 * Compares two radio elements to see where they are on the grid
 * @author Emerald Dsouza
 *
 */
public class AxisSorter implements Comparator<GridElement> {

	@Override
	public int compare(GridElement r1, GridElement r2) {
		if(r1.getxCoord()==r2.getxCoord()){
			return (r1.getyCoord() < r2.getyCoord())? 0 : 1;
		}
		else if(r1.getxCoord() < r2.getxCoord()){
			return 0;
		}
		return 1;
	}

}

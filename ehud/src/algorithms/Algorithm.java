/**
 * 
 */
package algorithms;

import org.apache.log4j.Logger;

import ehud.Radio;
import ehud.World;

/**
 * @author Administrator
 *
 */
public interface Algorithm {
	
	static Logger log = Logger.getLogger(
            Algorithm.class.getName());
	
	/**
	 * Algorithmic actions to perform before stepping through the simulation
	 * @param frameNumber TODO
	 * @param world TODO
	 * @param radio TODO
	 * @return int
	 */
	public int preStep(int frameNumber, World world, Radio radio);
	/**
	 * Algorithmic actions to perform 
	 * @param frameNumber what step of the simulation are we on
	 * @param world a reference to the wrold object
	 * @param radio TODO
	 * @return int
	 */
	public int step(int frameNumber, World world, Radio radio);
	/**
	 * Algorithmic actions to perform after stepping through the simulation
	 * @param frameNumber TODO
	 * @param world TODO
	 * @param radio TODO
	 * @return int
	 */
	public int postStep(int frameNumber, World world, Radio radio);
	
	public int getBoundingBoxDistance();

}

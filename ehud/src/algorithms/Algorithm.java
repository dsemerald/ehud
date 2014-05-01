/**
 * 
 */
package algorithms;

import ehud.World;

/**
 * @author Administrator
 *
 */
public interface Algorithm {
	
	/**
	 * Algorithmic actions to perform before stepping through the simulation
	 * @param frameNumber TODO
	 * @param world TODO
	 * @return int
	 */
	public int preStep(int frameNumber, World world);
	/**
	 * Algorithmic actions to perform 
	 * @param frameNumber what step of the simulation are we on
	 * @param world a reference to the wrold object
	 * @return int
	 */
	public int step(int frameNumber, World world);
	/**
	 * Algorithmic actions to perform after stepping through the simulation
	 * @param frameNumber TODO
	 * @param world TODO
	 * @return int
	 */
	public int postStep(int frameNumber, World world);

}

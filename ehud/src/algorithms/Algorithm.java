/**
 * 
 */
package algorithms;

/**
 * @author Administrator
 *
 */
public interface Algorithm {
	
	/**
	 * Algorithmic actions to perform before stepping through the simulation
	 * @return int
	 */
	public int preStep();
	/**
	 * Algorithmic actions to perform 
	 * @return int
	 */
	public int step();
	/**
	 * Algorithmic actions to perform after stepping through the simulation
	 * @return int
	 */
	public int postStep();

}

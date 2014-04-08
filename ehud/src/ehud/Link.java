/**
 * 
 */
package ehud;

/**
 * If a link exists between two radio elements they are connected. Some links
 * ignore radio interference examples of these being optic cables and co-axial
 * cables. The purpose of each step in the algorithm is to establish links.
 * 
 * @author Emerald Dsouza
 * 
 */
public class Link {
	/**
	 * Specifies if the link is immune to radio interference
	 */
	boolean ignoreInterference;
	/**
	 * The start and end points of the link are always radios
	 */
	Radio start;
	Radio end;
}

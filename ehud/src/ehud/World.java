package ehud;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

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
		Collection<File> files = 
			    FileUtils.listFiles(new File("xml"), null, false);
		for (File xml: files){
			if(xml.getName().startsWith("radio_")){
				System.out.println(xml.getName());
			}
		}
		return 0;
	}
}

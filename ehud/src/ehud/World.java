package ehud;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;

import ehud.comparators.AxisSorter;

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
	 * 
	 * @return 0 is load unsuccessful
	 * @return 1 if load successful
	 */
	int initialize() {
		Collection<File> files = FileUtils.listFiles(new File("xml"), null,
				false);
		try {
			// build the common objects we need outside the for loop
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression xcord = xpath.compile("/radio/coordinates/x/text()");
			XPathExpression ycord = xpath.compile("/radio/coordinates/y/text()");
			XPathExpression cellid = xpath.compile("/radio/cellID/text()");
			for (File xml : files) {
				if (xml.getName().startsWith("radio_")) {
					System.out.println(xml.getName());
					try {
						Document doc = dBuilder.parse(xml);
						Radio r = new Radio();
						r.setxCoord(Float.valueOf(xcord.evaluate(doc)));
						r.setyCoord(Float.valueOf(ycord.evaluate(doc)));
						r.setCellID(Integer.valueOf(cellid.evaluate(doc)));
						radioElements.add(r);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			Collections.sort(radioElements,new AxisSorter());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public List<Radio> getRadioElements() {
		return radioElements;
	}

	public void setRadioElements(List<Radio> radioElements) {
		this.radioElements = radioElements;
	}

	public List<Interference> getInterferenceElements() {
		return interferenceElements;
	}

	public void setInterferenceElements(List<Interference> interferenceElements) {
		this.interferenceElements = interferenceElements;
	}
	
	
}



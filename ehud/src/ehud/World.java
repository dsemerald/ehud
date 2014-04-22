package ehud;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
	
	static Logger log = Logger.getLogger(
            World.class.getName());
	
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
	 * Stop after how many steps
	 */
	int maxSteps = 0;
	/**
	 * track where we are in the simulation
	 */
	int currentStep = 0;

	/**
	 * Loads the world with members
	 * 
	 * @return 0 is load unsuccessful
	 * @return 1 if load successful
	 */
	public int initialize() {
		Collection<File> files = FileUtils.listFiles(new File("xml"), null,
				false);
		try {
			// build the common objects we need outside the for loop
			loadRadios(files);
			loadInterference(files);
			loadApplication(files);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Load application specific settings
	 * 
	 * @param files
	 */
	private void loadApplication(Collection<File> files)
			throws ParserConfigurationException, XPathExpressionException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression maxFrames = xpath
				.compile("/application/maxframes/text()");
		// XPathExpression ycord = xpath.compile("/radio/coordinates/y/text()");
		// XPathExpression cellid = xpath.compile("/radio/cellID/text()");
		for (File xml : files) {
			if (xml.getName().startsWith("application")) {
				try {
					Document doc = dBuilder.parse(xml);
					maxSteps = Integer.valueOf(maxFrames.evaluate(doc));
				} catch (Exception e) {
					maxSteps = 0;
					e.printStackTrace(); // TODO: implement logger
				}
			}
		}
	}

	/**
	 * Load interference cells from the xml files
	 * 
	 * @param files
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 */
	private void loadInterference(Collection<File> files)
			throws ParserConfigurationException, XPathExpressionException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression cell = xpath.compile("/interferenceMap/cell");
		XPathExpression xcord = xpath.compile("x/text()");
		XPathExpression ycord = xpath.compile("y/text()");
		XPathExpression radius = xpath.compile("radius/text()");
		// iterate over all the files in xml folder
		for (File xml : files) {
			// if the file begins with interference_
			if (xml.getName().startsWith("interference_")) {
				try {
					Document doc = dBuilder.parse(xml);
					// there are multiple inteference cells defined in one
					// xml file
					NodeList cells = (NodeList) cell.evaluate(doc,
							XPathConstants.NODESET);
					int loopLength = cells.getLength() - 1;
					for (int i = 0; i < loopLength; i++) {
						Node cll = cells.item(i);
						if (cll != null
								&& cll.getNodeType() == Node.ELEMENT_NODE) {
							Interference intr = new Interference(); // create
																	// the
																	// interference
																	// object
							Element product = (Element) cll;
							intr.setRadius(Float.valueOf(radius.evaluate(cll)));
							intr.setxCoord(Float.valueOf(xcord.evaluate(cll)));
							intr.setyCoord(Float.valueOf(ycord.evaluate(cll)));
							interferenceElements.add(intr);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();// TODO: implement logger
				}
			}
		}
		Collections.sort(interferenceElements, new AxisSorter());
	}

	/**
	 * Loads the radios from XML files
	 * 
	 * @param files
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 * @throws JAXBException
	 */
	private void loadRadios(Collection<File> files)
			throws ParserConfigurationException, XPathExpressionException,
			JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Radio.class);
		for (File xml : files) {
			if (xml.getName().startsWith("radio_")) {
				Unmarshaller u = jc.createUnmarshaller();
				Radio radio = (Radio) u.unmarshal(xml);
				boolean state = radio.getActiveFrame()==0? true:radio.isRadioState();
				radio.setRadioState(state);
				radioElements.add(radio);
			}
		}
		Collections.sort(radioElements, new AxisSorter());
	}

	/**
	 * Advances the simulation by one step
	 * 
	 * @return Number of steps remaining in simulation
	 */
	public int step() {
		currentStep++;
		log.debug("Step "+currentStep);
		if (currentStep <= maxSteps) {
			for(Radio r: radioElements){
				r.step(currentStep, this);
			}
		}
		return maxSteps - currentStep;
	}

	/**
	 * Checks if there are steps remaining to be executed in the simulation
	 * 
	 * @return True if simulation can be continued
	 */
	public boolean hasNext() {
		return (maxSteps - currentStep) > 0;
	}

	/* getters and setters */

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

	public int getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(int currentStep) {
		this.currentStep = currentStep;
	}

}

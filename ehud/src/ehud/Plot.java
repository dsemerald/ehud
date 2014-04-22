package ehud;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Plots the world to a jpeg image
 * 
 * @author Emerald Dsouza
 * 
 */
public class Plot {
	
	/**
	 * Used to generate the file number
	 */
	private int stepNumber = 0;

	public int saveImageToFile(World w) {
		int width = 600;
		int height = 400;
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) bi.getGraphics();
		setCommonGraphicsOptions(g);
		drawGrid(bi,g);
		plotRadios(bi,g, w);
		plotInterference(bi,g,w);
		File outputfile = new File("step_"+stepNumber+".png");//TODO: filename should coincide with step
		try {
			ImageIO.write(bi, "png", outputfile);
		} catch (Exception e) {
			e.printStackTrace(); // TODO: implement logging
		}
		return 0;
	}
	
	/**
	 * Plot zones with interference as black circles on the grid
	 * @param bi Buffered Image
	 * @param g	 graphics object preferably with options set
	 * @param w	 World object
	 */
	private void plotInterference(BufferedImage bi, Graphics2D g, World w) {
		float alpha = 0.50f;
		AlphaComposite myAlpha = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, alpha);
		g.setComposite(myAlpha);
		g.setColor(Color.black);
		for(Interference in: w.getInterferenceElements()){
			float d = in.getRadius();
			Ellipse2D.Double circle = new Ellipse2D.Double(in.getxCoord() - d
					/ 2, in.getyCoord() - d / 2, d, d);
			g.fill(circle);
		}	
		//restore the alpha
		myAlpha = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 1.0f);
		g.setComposite(myAlpha);
	}

	private Graphics2D setCommonGraphicsOptions(Graphics2D g){
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
				RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		return g;
	}

	/**
	 * Plot radios as small rectangles
	 * 
	 * @param bi
	 *            A buffered Image
	 * @param g Graphics object preferably with parameters set
	 * @param w
	 *            A world object
	 * @return a buffered image
	 */
	private BufferedImage plotRadios(BufferedImage bi, Graphics2D g, World w) {
		int rectWidth = 5, rectHeight = 5;
		float alpha = 0.50f;
		int d = 150; //TODO: radius must be from transmission power not harcoded
		for (Radio r : w.getRadioElements()) {
			if(!r.isRadioState()){
				continue;
			}
			g.setPaint(Color.red);
			AlphaComposite myAlpha = AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 1f);
			g.setComposite(myAlpha);
			g.draw(new Rectangle2D.Double(r.getxCoord(), r.getyCoord(),
					rectWidth, rectHeight));
			g.drawString(String.valueOf(r.getCellID()), r.getxCoord(),
					r.getyCoord());
			// now draw how far the transmission goes
			//g.setColor(Color.blue);
			g.setColor(r.getColor());
			Ellipse2D.Double circle = new Ellipse2D.Double(r.getxCoord() - d
					/ 2, r.getyCoord() - d / 2, d, d);
			myAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
			g.setComposite(myAlpha);
			g.fill(circle);
		}
		return bi;
	}

	/**
	 * Prints a grid on the image
	 * 
	 * @param bi
	 *            A bufferedImage object
	 * @param g2 
	 * @return A buffered image object
	 */
	private BufferedImage drawGrid(BufferedImage bi, Graphics2D g) {
		int xstart = 0;
		int ystart = 0;
		int height = bi.getHeight();
		int width = bi.getWidth();
		int gridSpacing = 5;
		g.setPaint(Color.black);
		for (int i = 1; i <= gridSpacing; i++) {
			xstart = i * (width / gridSpacing);
			ystart = i * (height / gridSpacing);
			g.draw(new Line2D.Double(xstart, 0, xstart, height)); // vertical
																	// lines
			g.draw(new Line2D.Double(0, ystart, width, ystart)); // horizontal
																	// lines
		}
		return bi;
	}

	public int getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(int stepNumber) {
		this.stepNumber = stepNumber;
	}

	
}

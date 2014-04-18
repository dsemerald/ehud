package ehud;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Plots the world to a jpeg image
 * @author Emerald Dsouza
 *
 */
public class Plot {
	
	public int saveImageToFile(World w){
		int width = 400;
		int height = 400;
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = bi.createGraphics();
		drawGrid(bi);
		plotRadios(bi,w);
		File outputfile = new File("saved.png");
		try{
			ImageIO.write(bi, "png", outputfile);
		}
		catch(Exception e){
			e.printStackTrace(); //TODO: implement logging
		}
		return 0;
	}
	
	/**
	 * Plot radios as small rectangles
	 * @param bi A buffered Image
	 * @param w A world object
	 * @return a buffered image
	 */
	private BufferedImage plotRadios(BufferedImage bi, World w) {
		int rectWidth=5, rectHeight = 5;
		Graphics2D g = (Graphics2D) bi.getGraphics();
		for(Radio r:w.getRadioElements()){
			g.setPaint(Color.red);
			AlphaComposite myAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f);
			g.setComposite(myAlpha);
			g.draw(new Rectangle2D.Double(r.getxCoord(), r.getyCoord(),
                    rectWidth,
                    rectHeight));
			g.drawString(String.valueOf(r.getCellID()), r.getxCoord(), r.getyCoord());
			// now draw how far the transmission goes
			g.setColor(Color.blue);
			float alpha = 0.50f;
			int d = 150;
			Ellipse2D.Double circle = new Ellipse2D.Double(r.getxCoord()-d/2,r.getyCoord()-d/2,d,d);
			myAlpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f);
			g.setComposite(myAlpha);
			g.fill(circle);
		}
		return bi;
	}

	/**
	 * Prints a grid on the image
	 * @param bi A bufferedImage object
	 * @return A buffered image object
	 */
	private BufferedImage drawGrid(BufferedImage bi){
		int xstart = 0;
		int ystart = 0;
		int height = bi.getHeight();
		int width = bi.getWidth();
		int gridSpacing = 5;
		Graphics2D g = (Graphics2D) bi.getGraphics();
		g.setPaint(Color.black);
	    for(int i = 1; i <= gridSpacing; i++)
	    {
	    	xstart = i*(width/gridSpacing);
	    	ystart = i*(height/gridSpacing);
	        g.draw(new Line2D.Double(xstart, 0, xstart, height)); // vertical lines
	        g.draw(new Line2D.Double(0, ystart, width, ystart));	// horizontal lines
	    }
		return bi;
	}

}

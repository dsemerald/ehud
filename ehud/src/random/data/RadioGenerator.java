package random.data;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.commons.io.FileUtils;

public class RadioGenerator {

	public static void main(String[] args) {
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		for(int i =0;i<15;i++){
			int cellid = 30000+i;
			double x = Double.valueOf(twoDForm.format(Math.random()*600));
			double y = Double.valueOf(twoDForm.format(Math.random()*400));
			int startFrame = Integer.valueOf((int) (Math.random()*6));
			System.out.println(cellid+"  "+x+"  "+y+"   "+startFrame);
			StringBuffer buf = new StringBuffer();
			buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<radio xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"radio.xsd\">");
			buf.append("\n\t<coordinates>\n");
			buf.append("\t\t<x>"+x+"</x>\n");
			buf.append("\t\t<y>"+y+"</y>\n");
			buf.append("\t</coordinates>\n");
			buf.append("\t<activeFrame>"+startFrame+"</activeFrame>\n");
			buf.append("\t<cellID>"+cellid+"</cellID>\n");
			buf.append("\t<coordinates>\n");
			buf.append("\t\t<main>0</main>\n");
			buf.append("\t\t<fractional>0</fractional>\n");
			buf.append("\t</coordinates>\n");
			buf.append("</radio>\n");
			String fileName = "xml/radio_"+cellid+".xml";
			File f = new File(fileName);
			try {
				FileUtils.writeStringToFile(f, buf.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

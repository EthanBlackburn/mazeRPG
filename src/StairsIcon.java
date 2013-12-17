import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;


public class StairsIcon implements Icon{
/*
 * this class holds and paints the picture for the stairs.
 * levels 1-3 have stairs to proceed to next level
 */
	private BufferedImage image;
	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void paintIcon(Component arg0, Graphics arg1, int arg2, int arg3) {
		Graphics2D g2 = (Graphics2D) arg1;
		java.net.URL url = getClass().getResource("/resources/environment/stairs.png");
		try {                
	         image = ImageIO.read(url);
	    } catch (IOException ex) {
	           System.out.println("couldnt load stairs image");
	    }
		
		Image temp= image.getScaledInstance(17,17,Image.SCALE_DEFAULT);
		g2.drawImage(temp, 629, 629, null);
		
	}

}

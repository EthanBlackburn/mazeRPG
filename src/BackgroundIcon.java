import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;




public class BackgroundIcon implements Icon{

	private BufferedImage background;
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
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		
		java.net.URL url = getClass().getResource("/resources/environment/ground.png");
		try {                
	         background = ImageIO.read(url);
	    } catch (IOException ex) {
	           System.out.println("couldnt ground image");
	    }
		for(int i = 0; i < 61; i++){
			for(int j = 0; j < 41; j++){
				g2.drawImage(background.getScaledInstance(17, 17, Image.SCALE_DEFAULT), i*17, j*17, null);
			}
		}
		
		
	}

}

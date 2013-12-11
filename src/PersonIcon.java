import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;


public class PersonIcon implements Icon{

	private BufferedImage player;
	private Person pers;
	
	
	public PersonIcon(Person p){
		pers = p;
		player = p.getImage();
	}
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
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(player, x, y, null);
		
	}

}

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;


public class PlayerIcon implements Icon{

	private BufferedImage player;
	private Player pers;
	
	
	public PlayerIcon(Player p){
		pers = p;
		java.net.URL url = getClass().getResource("/resources/player/52432.png");
		try {                
	         player = ImageIO.read(url);
	    } catch (IOException ex) {
	           System.out.println("couldnt load player image");
	    }
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

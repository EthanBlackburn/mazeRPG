import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player extends Person{
	//Player has a location
	private int level;
	private BufferedImage player;
	public Player(Path p) {
		super(p,p.getVertex(0, 0));
		maxHealth = 200;
		setHealth(maxHealth);
		level = 1;
		java.net.URL url = getClass().getResource("/resources/player/52432.png");
		try {                
	         player = ImageIO.read(url);
	    } catch (IOException ex) {
	           System.out.println("couldnt load player image");
	    }
		setImage(player);
	}
	public BufferedImage getImage(){
		return super.getImage();
	}
}

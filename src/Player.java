import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Player extends Person{
	/*
	 * player class represents the user
	 * 
	 */
	//Player has a location
	private int level;
	private BufferedImage player;
	private Vertex vert;
	public Player(Path p) {
		super(p,p.getVertex(0, 0));
		vert = super.vert;
		maxHealth = 200;
		attack = 20;
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
	public BufferedImage getImage(){ //return the image representing this player object
		return super.getImage();
	}
	
	public int getX() { //return x coordinate
		return super.getX();
	}
	
	public int getY() { //return y coordinate
		return super.getY();
	}
	
	
}

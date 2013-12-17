import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;


public class AttackIcon implements Icon{
	private String direction;
	private BufferedImage image;
	private Attack atk;
	
	public AttackIcon(Attack A){ //image to load for an attack
		direction = A.getDirection(); //get direction of attack
		atk = A;
	}
	
	@Override
	public void paintIcon(Component arg0, Graphics g, int arg2, int arg3) {
		Graphics2D g2 = (Graphics2D)g;
		java.net.URL url;
		if(direction == "up"){
			url = getClass().getResource("/resources/attacks/blast_up.png"); //load the correct blast image based on attack direction
			
			
		}
		else if(direction == "down"){
			url = getClass().getResource("/resources/attacks/blast_down.png");
			
		}
		else if(direction == "left"){
			url = getClass().getResource("/resources/attacks/blast_left.png");
			
		}
		else{
			url = getClass().getResource("/resources/attacks/blast_right.png");
			
		}
		try {                
	         image = ImageIO.read(url);
	    } catch (IOException ex) {
	           System.out.println("couldnt load blast image");
	    }
		
		Image temp= image.getScaledInstance(17,17,Image.SCALE_DEFAULT); //scale the image to the size of each square on the board
		g2.drawImage(temp, 17*(atk.getLocation().getX()), 17*(atk.getLocation().getY()), null);
		
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
}

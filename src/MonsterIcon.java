import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.String;

import javax.imageio.ImageIO;
import javax.swing.Icon;


public class MonsterIcon implements Icon{
	/*
	 * this class holds the image for the monster
	 * level one contains the first evolution,
	 * level two contains the second evolution
	 * level three contains the third evolution
	 * level four contains one monster which is the final boss.
	 */

	private BufferedImage monster;
	private Image monstimage;
	private Monster monst;
	
	
	public MonsterIcon(Monster m){
		monst = m;
		int t = monst.getType();
		int l = monst.getLevel();
		java.net.URL url;
		String file = "/resources/";
		switch(l) {
		case 1: 
			file += "level1/";
			switch(t) {
			case 1: file += "bulbasaur.png";
			break;
			case 2: file += "charmander.png";
			break;
			case 3: file += "squirtle.png";
			break;
			}
		break;
		case 2:
			file += "level2/";
			switch(t) {
			case 1: file += "ivysaur.png";
			break;
			case 2: file += "charmeleon.png";
			break;
			case 3: file += "wartortle.png";
			break;
			}
		break;
		case 3:
			file += "level3/";
			switch(t) {
			case 1: file += "venusaur.png";
			break;
			case 2: file += "charizard.png";
			break;
			case 3: file += "blastoise.png";
			break;
			}
		break;
		case 4:
			file += "level4/mewtwo.png";
			break;
		}
		
		url = getClass().getResource(file);
		try {
	         monster = ImageIO.read(url);
	         
	    } catch (IOException ex) {
	           System.out.println("couldnt load monster image");
	    }
		monstimage = monster.getScaledInstance(50,50,monster.SCALE_DEFAULT);
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
		//Image temp = monster.getScaledInstance(50,50,monst.SCALE_DEFAULT);
		g2.drawImage(monstimage, x, y, null);
		
	}

}

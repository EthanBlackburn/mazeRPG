import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;



public class GameIcon implements Icon{

	private Grid grid;
	private int height;
	private int width;
	private BufferedImage background;
	private Player play;
	private ArrayList<Monster> monsters;
	private int i;
	
	
	public GameIcon(Grid g, Player p, ArrayList<Monster> m){
		grid = g;
		height = 42*grid.getHeight();
		width = 42*grid.getWidth();
		if(height > 42*17){
			height = 42*17;
		}
		if(width > 62*17){
			width = 62*17;
		}
		i = 0;
		play = p;
		monsters = m;
	}
	
	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	

	@Override
	public void paintIcon(Component arg0, Graphics arg1, int arg2, int arg3) {
		Graphics2D g2 = (Graphics2D) arg1;
		

		
		int dx = 5;
		int dy = 5;
			
			
			
		BasicStroke stroke2 = new BasicStroke(10);
		//create a GraphIcon for the grid.walls and paint it black
		g2.setStroke(stroke2);
		GraphIcon w = new GraphIcon(grid.walls);
		w.paintIcon(arg0, g2, dx, dy);	
		
	
		BackgroundIcon background = new BackgroundIcon(grid.path);
		background.paintIcon(arg0, g2, 0, 0);
		GraphIcon wall = new GraphIcon(grid.walls);
		
		wall.paintIcon(arg0, g2, dx, dy);
		
		//draw monsters
		for(int i = 0; i<monsters.size(); i++) {
			MonsterIcon monster = new MonsterIcon(monsters.get(i));
			monster.paintIcon(arg0, g2, 17*(int)((monsters.get(i)).getX()-1), 17*(int)((monsters.get(i)).getY()-1));
		}
		
		//draw player
		PlayerIcon person = new PlayerIcon(play);
		person.paintIcon(arg0, g2, 17*(int)play.getX(), 17*(int)play.getY()-3);
		
	}

}

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;



public class GameIcon implements Icon{

	private Grid grid;
	private int height;
	private int width;
	private Player p;
	
	
	public GameIcon(Grid g, Player p){
		grid = g;
		height = 42*grid.getHeight();
		width = 42*grid.getWidth();
		if(height > 42*17){
			height = 42*17;
		}
		if(width > 62*17){
			width = 62*17;
		}
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
		
		BasicStroke stroke = new BasicStroke(2);
		g2.setStroke(stroke);
		
		int dx = 5;
		int dy = 5;
<<<<<<< HEAD
		
		java.net.URL url = getClass().getResource("/resources/environment/ground.png");
		try {                
	         background = ImageIO.read(url);
	    } catch (IOException ex) {
	           System.out.println("couldnt ground image");
	    }
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				g2.drawImage(background.getScaledInstance(390, 260, Image.SCALE_DEFAULT), i*390, j*260, null);
			}
		}
		
		
		BasicStroke stroke2 = new BasicStroke(10);
		//create a GraphIcon for the grid.walls and paint it black
		g2.setStroke(stroke2);
		GraphIcon w = new GraphIcon(grid.walls);
		g2.setColor(Color.BLACK);
		w.paintIcon(arg0, g2, dx, dy);	
		g2.setStroke(stroke);
		//preliminary test of longest path drawing by drawing the two endpoints with blue circles
		grid.findFurthestVertex(grid.path.getVertex(0,0),0);
		start = grid.farthestVert;
=======
	
		BackgroundIcon background = new BackgroundIcon();
		background.paintIcon(arg0, g2, 0, 0);
>>>>>>> ea360e75358e9891da53bbb28938dec63afe1e4c
		GraphIcon wall = new GraphIcon(grid.walls);
		PersonIcon person = new PersonIcon(p);
		person.paintIcon(arg0, g2, 5, 5);
		wall.paintIcon(arg0, g2, dx, dy);

		
	}

}

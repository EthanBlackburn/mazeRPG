import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class GameIcon implements Icon{

	private Grid grid;
	private Player player;
	private int x;
	private int y;
	private int height;
	private int width;
	private Vertex start;
	private Vertex end;
	private BufferedImage background;
	
	public GameIcon(Grid g){
		grid = g;
		height = 42*grid.getHeight();
		width = 42*grid.getWidth();
		x = grid.getWidth()*2;
		y = grid.getHeight()*2;
		if(height > 41*17){
			height = 41*17;
		}
		if(width > 61*17){
			width = 61*17;
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
		
		
		
		grid.findFurthestVertex(grid.path.getVertex(0,0),0);
		start = grid.farthestVert;
		GraphIcon wall = new GraphIcon(grid.walls);
		wall.paintIcon(arg0, g2, dx, dy);
		grid.resetMarkers();
		grid.findFurthestVertex(start,0);

		end = new Vertex(grid.farthestVert);

		Vertex end = new Vertex(grid.farthestVert);

		grid.resetMarkers();
		grid.makeLongestPath(start, new Vertex(start), end);
		grid.resetMarkers();
		
	}

}

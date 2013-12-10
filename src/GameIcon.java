import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.Icon;


public class GameIcon implements Icon{

	private Grid grid;
	private Player player;
	private int x;
	private int y;
	private int height;
	private int width;
	
	public GameIcon(Grid g){
		grid = g;
		height = 20*grid.getHeight();
		width = 20*grid.getWidth();
		x = grid.getWidth()*2;
		y = grid.getHeight()*2;
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
		int quant = 20;
		
		BasicStroke stroke = new BasicStroke(2);
		g2.setStroke(stroke);
		
		//create a GraphIcon for the grid.path and paint it RED
		GraphIcon path = new GraphIcon(grid.path);
		g2.setColor(Color.RED);
		path.paintIcon(arg0, g2, arg2, arg3);
				
		
		//create a GraphIcon for the grid.walls and paint it black
		GraphIcon w = new GraphIcon(grid.walls);
		g2.setColor(Color.BLACK);
		w.paintIcon(arg0, g2, arg2, arg3);	
		
		//preliminary test of longest path drawing by drawing the two endpoints with blue circles
		grid.findFurthestVertex(grid.path.getVertex(0,0),0);
		Vertex start = grid.farthestVert;
		grid.resetMarkers();
		grid.findFurthestVertex(start,0);
		Vertex end = new Vertex(grid.farthestVert);
		
		Ellipse2D.Double startCircle = new Ellipse2D.Double(10*start.getX() - 5,10*start.getY()-5, 10,10);
		Ellipse2D.Double endCircle = new Ellipse2D.Double(10*end.getX()-5, 10*end.getY()-5, 10, 10);
		g2.setColor(Color.BLUE);
		g2.fill(startCircle);
		g2.setColor(Color.RED);
		g2.fill(endCircle);
		
		grid.resetMarkers();
		grid.makeLongestPath(start, new Vertex(start), end);
		grid.resetMarkers();
		
		//draw the longest path in color BLUE
		g2.setColor(Color.BLUE);
		for(int i = 0 ; i< grid.longestPath.size()-1; i++) {
			Line2D.Double l1 = grid.longestPath.get(i).toLine(grid.longestPath.get(i+1), 10);
			g2.draw(l1);
		}
	}

}

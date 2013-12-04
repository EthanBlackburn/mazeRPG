import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.Icon;


public class GameIcon implements Icon{

	private ArrayList<Wall> walls;
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
	
	public void AddWall(Wall w) {
		Wall wall = w;
		walls.add(wall);
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
		for(int i = 0; i< grid.getWidth()-1; i++){
			for(int j = 0; j< grid.getHeight()-1; j++){
				for(int k = 0; k< grid.getVertex(i, j).connections.size(); k++) {
					Line2D.Double l1 = grid.getVertex(i, j).toLine((grid.getVertex(i,j)).getConnection(k), getIconWidth()/x);
					g2.setColor(Color.RED);
					g2.draw(l1);
				}
			}
		}
		for(int i = 0; i< grid.getWidth(); i++){
			for(int j = 0; j< grid.getHeight(); j++){
				for(int k = 0; k< grid.getVertex2(i, j).connections.size(); k++) {
					Line2D.Double l1 = grid.getVertex2(i, j).toLine((grid.getVertex2(i,j)).getConnection(k), getIconWidth()/x);
					g2.setColor(Color.BLACK);
					g2.draw(l1);
				}
			}
		}
	}

}

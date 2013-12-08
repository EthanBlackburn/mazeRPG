import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JComponent;


public class Wall extends Graph{
	
	//create a wall from existing vertices
	//by convention we will say w and h are the number rowsXcolumns of cells
	public Wall(int w, int h){
		//create the wall and connect all the neighboring vertices.
		super(w+1,h+1);
		for(int i = 0; i<= w ; i++) {
			for(int j = 0; j<= h; j++){
				graph[i][j] = new Vertex(2*i,2*j);
			}
		}
		for(int i = 0; i< w +1; i++) {
			for(int j = 0; j<h +1; j++){
					if(j<h)
						graph[i][j].addConnection(graph[i][j+1]);
					else if(j>0)
						graph[i][j].addConnection(graph[i][j-1]);
					if(i<w)
						graph[i][j].addConnection(graph[i+1][j]);
					else if(w>0)
						graph[i][j].addConnection(graph[i-1][j]);
			}
		}
	}
}

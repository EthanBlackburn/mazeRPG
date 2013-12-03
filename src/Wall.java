import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;


public class Wall {
	
	//wallType is either horizontal or vertical (0 or 1 respectively. 2 records the it is neither, in which case an error occured)
	private int wallType;
	
	//two vertices describe the wall location. the wall is basically a line with two endPoints
	protected Vertex vert1;
	protected Vertex vert2;
	
	//create a wall from existing vertices
	public Wall(Vertex vertA, Vertex vertB){
		vert1 = vertA;
		vert2 = vertB;
		if(vertA.getX() == vertB.getX()){
			wallType = 1;
		}
		else if(vertA.getY() == vertB.getY()){
			wallType = 0;
		}
		else {
			wallType = 2;
		}
	}
	
	
	//create a wall from locations
	public Wall(Location loc1, Location loc2){
		this (new Vertex(loc1), new Vertex(loc2));
	}
	
	//get type of wall
	public int getType(){
		return wallType;
	}
	
	public int getHeight(){
		int temp = vert1.getY() - vert2.getY();
		if(temp<0){
			return 1 - temp;
		}
		else {
			return 1 + temp;
		}
	}
	
	public int getWidth() {
		int temp = vert1.getX() - vert2.getX();
		if(temp<0){
			return 1 - temp;
		}
		else {
			return 1 + temp;
		}
	}

}

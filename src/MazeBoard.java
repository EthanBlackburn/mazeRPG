import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MazeBoard extends JPanel{
	
	//board should have a person
	private Player person1;
	
	//board should have a grid, path, and walls
	private Grid grid;
	private Path path;
	private ArrayList<Wall> walls;
	
	public MazeBoard(int w, int h) {
		super();
		
		setLayout(new GridLayout());
		
		grid = new Grid(w,h);
		
		this.walls = new ArrayList();
		walls.add(new Wall(new Vertex(10,10),new Vertex(10,20)));
		repaint();
	}
	
	public void addPath(Path p) {
		path = p;
	}
	
	public void addWall(Wall w) {
		Wall wall = w;
		walls.add(wall);
	}
}

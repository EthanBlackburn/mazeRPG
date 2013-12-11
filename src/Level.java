import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Level extends JPanel{

	private Player person1;
	
	private Grid grid;
	private IconAdapter player;
	private IconAdapter walls;
	protected ArrayList<Monster> monsters;
	
	public Level(int difficulty, int w, int h){
		super();
		grid = new Grid(w,h);
		person1 = new Player(grid.path);
		monsters = new ArrayList();
		monsters.add(new Monster(grid.path, grid.path.getVertex(w-1, 0), 1, difficulty));
		//level 4 has a boss, so only add the other two monsters if on the first three levels
		if(difficulty != 4) {
			monsters.add(new Monster(grid.path, grid.path.getVertex(w-1, h-1), 2, difficulty));
			monsters.add(new Monster(grid.path, grid.path.getVertex(0, h-1), 3, difficulty));
		}
		//player = new IconAdapter(new PersonIcon(person1.getImage()));
		walls = new IconAdapter(new GameIcon(grid,person1,monsters));
		setLayout(new FlowLayout());
		add(walls);
	}
	
	public void removePlayer(){
		remove(player);
	}
	
	public void addPlayer(){
		add(player);
	}
	
	
	
	public Grid getGrid(){
		return grid;
	}
	
	public Player getPlayer(){
		return person1;
	}
 
}

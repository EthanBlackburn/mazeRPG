import java.awt.FlowLayout;

import javax.swing.JPanel;


public class Level extends JPanel{

	private Player person1;
	
	private Grid grid;
	private IconAdapter player;
	private IconAdapter walls;
	
	public Level(int difficulty, int w, int h){
		super();
		grid = new Grid(w,h);
		person1 = new Player(grid.path);
		//player = new IconAdapter(new PersonIcon(person1.getImage()));
		walls = new IconAdapter(new GameIcon(grid,person1));
		setLayout(new FlowLayout());
		add(walls);
		//add(player);
		//repaint();
	}
	
	
	
	public Grid getGrid(){
		return grid;
	}
	
	public Player getPlayer(){
		return person1;
	}
 
}

import java.awt.FlowLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;


public class Level extends JPanel{

	private Player person1;
	
	private Grid grid;
	private IconAdapter player;
	private IconAdapter walls;
	protected ArrayList<Monster> monsters;
	private GameIcon GI;
	private int difficulty;
	
	public Level(int d, int w, int h){
		
		super();
		difficulty = d;
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
		GI = new GameIcon(grid,person1,monsters);
		walls = new IconAdapter(GI);
		setLayout(new FlowLayout());
		add(walls);
	}
	
	public void removePlayer(){
		remove(player);
	}
	
	public void addPlayer(){
		add(player);
	}
	
	public void addAttacks(ArrayList<Attack> a){
		System.out.println("Adding attacks to GameIcon");
		GI.addAttack(a);
	}
	
	public int getDifficulty(){
		return difficulty;
	}
	
	public Grid getGrid(){
		return grid;
	}
	
	public Player getPlayer(){
		return person1;
	}
 
	public ArrayList<Attack> DetectCollision(ArrayList<Attack> attacks){
		ArrayList<Attack> collisions = new ArrayList<Attack>();
		for(int i = 0; i < attacks.size(); i++){
			if(this.contains(attacks.get(i).getLocation().getX(),attacks.get(i).getLocation().getY())){
				collisions.add(attacks.get(i));
			}
		}
		if(collisions.isEmpty() == false){
			System.out.println("Removing attacks from GameIcon");
			GI.removeAttacks(collisions);
		}
		return collisions;
		
	}
}

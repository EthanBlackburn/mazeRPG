import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Level extends JPanel{

	private Player person1;
	
	protected Grid grid;
	private IconAdapter player;
	private IconAdapter walls;
	protected ArrayList<Monster> monsters;
	private GameIcon GI;
	//private Timer t;
	private int difficulty;
	
	
	public Level(int d, int w, int h){
		
		super();
		//t = new Timer(200,listener);
		//t.start();
		difficulty = d;
		grid = new Grid(w,h);
		person1 = new Player(grid.path);
		monsters = new ArrayList<Monster>();
		monsters.add(new Monster(grid.path, grid.path.getVertex(w-1, 0), 1, difficulty,person1));
		//level 4 has a boss, so only add the other two monsters if on the first three levels
		if(difficulty != 4) {
			monsters.add(new Monster(grid.path, grid.path.getVertex(w-1, h-1), 2, difficulty,person1));
			monsters.add(new Monster(grid.path, grid.path.getVertex(0, h-1), 3, difficulty,person1));
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
	
	public GameIcon getGI(){
		return GI;
	}
	
	public void addAttack(Attack a){
		GI.addAttack(a);
	}
	
	public void addAttacks(ArrayList<Attack> a){
		GI.addAttacks(a);
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
		
		LOOP:for(int i = 0; i < attacks.size(); i++){
			for(int k = 0; k < monsters.size();k++){
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
				System.out.println(attacks.get(i).getLocation().getX());
				System.out.println(attacks.get(i).getLocation().getY());
				if(grid.getVertex2(attacks.get(i).getVertex().getX()/2, attacks.get(i).getVertex().getY()/2) != null ){
				if(grid.getVertex2(attacks.get(i).getVertex().getX(), attacks.get(i).getVertex().getY()) != null ){
					System.out.println(attacks.get(i).getLocation().getX());
					System.out.println(attacks.get(i).getLocation().getY());
=======
				if(grid.getVertex2(attacks.get(i).getVertex().getX(), attacks.get(i).getVertex().getY()) != null ){//isnt detecting walls
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
				if(grid.getVertex2(attacks.get(i).getVertex().getX(), attacks.get(i).getVertex().getY()) != null ){//isnt detecting walls
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
				if(grid.getVertex2(attacks.get(i).getVertex().getX(), attacks.get(i).getVertex().getY()) != null ){//isnt detecting walls
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
				if(grid.getVertex2(attacks.get(i).getVertex().getX(), attacks.get(i).getVertex().getY()) != null ){//isnt detecting walls
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
					collisions.add(attacks.get(i));
					break LOOP;
				}
				else if(monsters.get(k).getLocation() == attacks.get(i).getLocation() & attacks.get(i).getType() != 1){
					System.out.println("attack type = " + attacks.get(i).getType());
					
					monsters.get(k).incrementHealth(-attacks.get(i).getStrength());
					collisions.add(attacks.get(i));
					if(monsters.get(k).getHealth() <= 0){
						monsters.remove(k);
					}
					
				}
				else if(person1.getLocation() == attacks.get(i).getLocation()){
					
					person1.incrementHealth(-attacks.get(i).getStrength());
					collisions.add(attacks.get(i));

					
				}
				
			}
		}
		if(collisions.isEmpty() == false){
			
			GI.removeAttacks(collisions);
			
		}
		return collisions;
		
	}
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	ActionListener listener = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			if(GI == null) {
				return;
			}
			DetectCollision(GI.getAttacks());
			for(int i = 0; i < monsters.size();i++){
				if(monsters.get(i).withinRange(person1,4)){
					addAttack(new Attack(monsters.get(i).getAttack(),monsters.get(i).getDirection(),monsters.get(i).getLocation(),1));
				if(monsters.get(i).isClose()){
					addAttack(new Attack(monsters.get(i).getAttack(),monsters.get(i).getDirection(),monsters.get(i).getLocation()));
				}
			}
			repaint();
		}
	};
=======
	
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
	
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
	
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
	
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
}

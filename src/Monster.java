import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.Timer;


public class Monster extends Person {

	private int Type;
	private int Level;
	private Hashtable<Vertex, String> mPath;
	private Timer t;
	private Player player;
	
	protected Monster(Path p, Vertex v, int type, int level, Player p1) {
		super(p, v);
		Type = type;
		player = p1;
		Level = level;
		if(level == 1){
			setAttack(15);
			t = new Timer(1000,monsterRefresh);
		}
		if(level == 2){
			setAttack(25);
			t = new Timer(750,monsterRefresh);
		}
		if(level == 3){
			setAttack(40);
			t = new Timer(500,monsterRefresh);
		}
		else{
			setAttack(50);
			t = new Timer(400,monsterRefresh);
		}
		mPath = new Hashtable<Vertex, String>();
		t.start();
		
	}
	
	public int getLevel() {
		return Level;
	}

	public int getType() {
		return Type;
	}

	
	public Vertex next(Player p){
		Vertex v = this.getVertex();
		mPath.put(v, "discovered");
		ArrayList<Vertex> locations = new ArrayList<Vertex>();
		locations.addAll(v.adjacentVertices(this));
		for(int i = 0; i < locations.size(); i++){
			
			Vertex check = locations.get(i);
			
			if(check == p.getVertex()){
				System.out.println("near player");
				return check;
			}
			System.out.println(check.getX());
			System.out.println(check.getY());
			if(mPath.containsKey(check) == false){
				mPath.put(check,"discovered");
				System.out.println("here");
				return check;	
			}
			
		}
		for(int i = 0; i < locations.size();i++){ //If we're here, then we need to retrace steps in maze
			Vertex check = locations.get(i);
			if(mPath.get(check) == "discovered"){
				mPath.put(check, "explored");
				return check;
			}
		}
		
		return null;
		
	}
	ActionListener monsterRefresh = new ActionListener() { //movement sucks as of now
		  public void actionPerformed(ActionEvent evt) {
			    Vertex x = next(player);
				int  newX = x.getX() - (int)getX();
				int  newY = x.getY() - (int)getY();
				move(newX,newY);
				
			  }
		};
}

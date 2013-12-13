import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.Timer;


public class Monster extends Person {

	private int Type;
	private int Level;
	private Hashtable<Vertex, String> mPath;
	private Timer t;
	private Player player;
	private boolean close;
	
	protected Monster(Path p, Vertex v, int type, int level, Player p1) {
		super(p, v);
		Type = type;
		player = p1;
		Level = level;
		close =  false;
		vert = v;
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
			t = new Timer(700,monsterRefresh);
		}
		else{
			setAttack(50);
			t = new Timer(500,monsterRefresh);
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
	
	public Vertex getVertex(){
		return vert;
	}
	
	public boolean isClose(){
		return close;
	}

<<<<<<< HEAD
	public boolean withinRange(Player p, int depth){
		if(p.getLocation().dist(loc)<depth) {
			return true;
		}
		else {
			return false;
=======
	public boolean withinRange(Player p, int depth,Vertex v){
		for(Iterator<Vertex> i = v.connections.iterator();i.hasNext();){
			Vertex n = i.next();
			if((p.getVertex().getX() == n.getX())&&(p.getVertex().getY() == n.getY())){
				close = true;
				System.out.println("Near player");
				return true;
			}
			if(depth < 6){
				return withinRange(p,depth+1,n);
			}
>>>>>>> ceec8a7b3bcd7c2f5aea090cf92c51a6938c6136
		}
	}
	
<<<<<<< HEAD
	public Vertex next(Player p, Vertex v, int depth){ //monsters essentially go through map switching locations but attack player if close
		v.setInPath();
		if(v.getX() == p.getX() & v.getY() == p.getY()){
			path.resetMarkers();
			return v;
		}
		else {
			for(int i = 0; i<v.connections.size(); i++){
				if(!v.connections.get(i).inPath()){
					Vertex temp = next(p,v.connections.get(i), depth +1);
					if(temp != null){
						return v.connections.get(i);
					}
				}
			
=======
	public Vertex next(Player p, Vertex v){ //monsters essentially go through map switching locations but attack player if close
		mPath.put(v, "discovered");
		for(Iterator<Vertex>i = v.connections.iterator();i.hasNext();){
			Vertex check = i.next();
			
			if(check.getX() == p.getX() & check.getY() == p.getY()){
				System.out.println("near player");
				mPath.clear();
				return check;
			}
			else if(mPath.containsKey(check) == false){
				System.out.println("contains key");
				mPath.put(check,"discovered");
				return next(p,check);
					
>>>>>>> ceec8a7b3bcd7c2f5aea090cf92c51a6938c6136
			}
		}
		return null;
		
	}
	
	ActionListener monsterRefresh = new ActionListener() { //movement sucks as of now
		  public void actionPerformed(ActionEvent evt) {
<<<<<<< HEAD
			  	path.resetMarkers();
			    Vertex x = next(player,vert,0);
			    if(x!= null){
					int  newX = (x.getX() - (int)vert.getX())/2;
					int  newY = (x.getY() - (int)vert.getY())/2;
					move(newX,newY);
=======
			    Vertex x = next(player,vert);
			    if(x != null){
			    	int  newX = x.getX() - (int)vert.getX();
			    	int  newY = x.getY() - (int)vert.getY();
			    	move(newX,newY);
>>>>>>> ceec8a7b3bcd7c2f5aea090cf92c51a6938c6136
			    }
				
			  }
		};
}

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
	private Vertex vert;
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
		}
		close = false;
		return false;
		
	}
	
	public Vertex next(Player p, Vertex v, int depth){ //monsters essentially go through map switching locations but attack player if close
		mPath.put(v, "discovered");
		if(v.getX() == p.getX() & v.getY() == p.getY()){
			System.out.println("near player");
			mPath.clear();
			//Level.grid.path.resetMarkers();
			return v;
		}
		for(Iterator<Vertex>i = v.connections.iterator();i.hasNext();){
			Vertex check = i.next();
			
			
			if(mPath.containsKey(check) == false){
				System.out.println("contains key");
				mPath.put(check,"discovered");
				Vertex temp = next(p,check,depth+1);
				if(depth == 0 & temp != null){
					return check;
				}
				else if (temp != null){
					return temp;
				}
				
					
			}
			
		}
		
		mPath.put(v, "explored");
		return null;
		
	}
	
	ActionListener monsterRefresh = new ActionListener() { //movement sucks as of now
		  public void actionPerformed(ActionEvent evt) {
			    Vertex x = next(player,vert,0);
			    if(x != null){
			    	int  newX = x.getX() - (int)vert.getX();
			    	int  newY = x.getY() - (int)vert.getY();
			    	if(isValidMove(newX,newY)){
			    		move(newX,newY);
			    	}
			    	else {
			    		move(loc.getX()-vert.getX(),loc.getY()-vert.getY());
			    	}
			    }
				
			  }
		};
}

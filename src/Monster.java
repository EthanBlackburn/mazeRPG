import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

import javax.swing.Timer;


public class Monster extends Person {

	private int Type;
	private int Level;
	private Hashtable<Vertex, String> mPath;
	private Timer t;
	private Player player;
	private boolean close;
	private Vertex startVert;
	private Vertex endVert;
	private ArrayList<Vertex> locations;
	
	protected Monster(Path p, Vertex v, int type, int level, Player p1) {
		super(p, v);
		Type = type;
		player = p1;
		Level = level;
		close =  false;
		vert = v;
		startVert = v;
		locations = new ArrayList<Vertex>();
		locations.add(new Vertex(37,1));
		locations.add(new Vertex(37,37));
		locations.add(new Vertex(1,37));
		endVert = nextVert();
		if(level == 1){
			setAttack(15);
			t = new Timer(1500,monsterRefresh);
		}
		if(level == 2){
			setAttack(25);
			t = new Timer(1250,monsterRefresh);
		}
		if(level == 3){
			setAttack(40);
			t = new Timer(1000,monsterRefresh);
		}
		else{
			setAttack(50);
			t = new Timer(750,monsterRefresh);
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
		return false;
	}

	
	public Vertex nextVert(){
		
		if(startVert.getX() == locations.get(0).getX() & startVert.getY() == locations.get(0).getY() ){
			return locations.get(1);
		}
		else if(startVert.getX() == locations.get(1).getX() & startVert.getY() == locations.get(1).getY() ){
			return locations.get(2);
		}
		else if(startVert.getX() == locations.get(2).getX() & startVert.getY() == locations.get(2).getY() ){
			return locations.get(0);
		}
		else{
			return null;
		}
	}
	
	public Vertex next(Vertex goal, Vertex v, int depth){ //monsters essentially go through map switching locations but attack player if close
		mPath.put(v, "discovered");
		if(v.getX() == goal.getX() & v.getY() == goal.getY()){
			mPath.clear();
			return v;
		}
		for(Iterator<Vertex>i = v.connections.iterator();i.hasNext();){
			Vertex check = i.next();
			if(mPath.containsKey(check) == false){
				mPath.put(check,"discovered");
				Vertex temp = next(goal,check,depth+1);
				if(depth == 0 & temp != null){
					return check;
				}
				else if (temp != null){
					return temp;
				}
					
			}
		}
		return null;
		
	}
	
	ActionListener monsterRefresh = new ActionListener() { //movement sucks as of now
		  public void actionPerformed(ActionEvent evt) {
			  	path.resetMarkers();
			    Vertex x = next(player.getVertex(),vert,0);
			    if(x!= null){
					int  newX = (x.getX() - (int)vert.getX())/2;
					int  newY = (x.getY() - (int)vert.getY())/2;
					move(newX,newY);
			  }
		}
	};
}

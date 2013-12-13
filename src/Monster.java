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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
				close = true;
				System.out.println("Near player");
=======
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
				return true;
			}
			if(depth < 6){
				return withinRange(p,depth+1,n);
			}
		}
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
		return false;
	}
			
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
=======
		
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
			
			
=======
		
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
			
			
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
		
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
			
			
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
			if(mPath.containsKey(check) == false){
				mPath.put(check,"discovered");
				Vertex temp = next(goal,check,depth+1);
				if(depth == 0 & temp != null){
					return check;
				}
				else if (temp != null){
					return temp;
				}
				
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
					
			}
		}
		return null;
		
	}
	
	ActionListener monsterRefresh = new ActionListener() { //movement sucks as of now
		  public void actionPerformed(ActionEvent evt) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
			  	path.resetMarkers();
			    Vertex x = next(player,vert);
			    if(x!= null){
					int  newX = (x.getX() - (int)vert.getX())/2;
					int  newY = (x.getY() - (int)vert.getY())/2;
					move(newX,newY);				
=======
=======
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
			    Vertex x = next(nextVert(),vert,0);
			    if(x != null){
			    	int  newX = x.getX() - (int)vert.getX();
			    	int  newY = x.getY() - (int)vert.getY();
			    	if(isValidMove(newX,newY)){
			    		move(newX,newY);
			    	}
			    	else {
			    		move(loc.getX()-vert.getX(),loc.getY()-vert.getY());
			    	}
			    	if(withinRange(player,6,getVertex())){
			    		close = true;
			    	}
			    	else{
			    		close = false;
			    	}
			    	if(getVertex().getX() == endVert.getX() & getVertex().getY() == endVert.getY()){
			    		startVert = new Vertex(endVert);
			    		endVert = nextVert();
			    	}
			    }
				
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
			  }
		}
	};
}

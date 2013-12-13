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
                if(level != 4){
                		endVert = nextVert();
                }
                else {
                	endVert = path.getVertex(0, 0);
                }
                if(level == 1){
                        setAttack(5);
                        t = new Timer(750,monsterRefresh);
                }
                else if(level == 2){
                        setAttack(10);
                        t = new Timer(625,monsterRefresh);
                }
                else if(level == 3){
                        setAttack(20);
                        t = new Timer(500,monsterRefresh);
                }
                else{
                        setAttack(50);
                        if(type==1);
                        t = new Timer(375,monsterRefresh);
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
        
        @Override
        public boolean isValidMove(double dx, double dy){
        	if(super.isValidMove(dx,dy)) {
        		if(!(getX() + dx == player.getX() & getY() + dy == player.getY())){
        			return true;
        		}
        	}
        	return false;
        	
        }
        
        public boolean isClose(){
                if(playerDist() <3 & (player.getX() == getX() | player.getY() == getY()))
                	return true;
                else return false;
        }
        
        public double playerDist() {
        	return getLocation().dist(player.getLocation());
        }

        public boolean withinRange(Player p, int depth,Vertex v){
                for(Iterator<Vertex> i = v.connections.iterator();i.hasNext();){
                        Vertex n = i.next();
                        if((p.getVertex().getX() == n.getX())&&(p.getVertex().getY() == n.getY())){
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
                        return path.getVertex(0, 0);
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
                
                mPath.put(v, "explored");
                return null;
                
        }
        
        ActionListener monsterRefresh = new ActionListener() { //movement sucks as of now
                  public void actionPerformed(ActionEvent evt) {
                	  		//set sights on a "random" vertex
                	  
                            Vertex x = next(nextVert(),vert,0);
                            //if player is close enough, monster should set player's location as its
                            //next destination
                            if(playerDist()<8){
                            	x = next(player.getVertex(),vert,0);
                            }
                            if(x != null){
                            		//move one space at a time (every vertex is two spaces apart)
                                    int  newX = (x.getX() - (int)vert.getX())/2;
                                    int  newY = (x.getY() - (int)vert.getY())/2;
                                    //if it isn't a valid move, then we've set our sights on an unreachable vertex
                                    //if this is the case then we should head towards the last touched vertex.
                                    //this problem can happen because a person might be on a space that is between 
                                    //two vertices.
                                    if(isValidMove(newX,newY)){
                                            move(newX,newY);
                                    }
                                    else {
                                            move(loc.getX()-vert.getX(),loc.getY()-vert.getY());
                                    }
                                    if(playerDist() < 4){
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
                                
                          }
                };
}
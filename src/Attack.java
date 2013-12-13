
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Attack{

	private int strength;
	private String direction;
	private Location loc;
	private Timer t;
	private Vertex vert;
	private int Type;
	
	public Attack(int s,String d, Location l, int type){
		direction = d;
		strength = s;
		loc = l;
		Type = type;
		vert = new Vertex(loc);
		t = new Timer(500,attackListener);
		t.start();
		
	}
	
	public int getStrength(){
		return strength;
	}
	
	public void setLocation(Location l){
		loc = l;
		
	}
	
	public Location getLocation(){
		return loc;
	}
	
	public Vertex getVertex(){
		return vert;
	}
	
	public String getDirection(){
		return direction;
	}
	
	public int getType(){
		return Type;
	}

	ActionListener attackListener = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			int x = loc.getX();
			int y = loc.getY();
			if(direction == "up"){
				y+=-1.0;
			}
			if(direction == "down"){
				y+=1.0;
			}
			if(direction == "right"){
				x+=1.0;
			}
			if(direction == "left"){
				x+=-1.0;
			}
			
			loc.setLocation(x, y);
		
		}
	};

}

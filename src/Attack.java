
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class Attack{

        private int strength;
        private String direction;
        private int Type;
        private Location loc;
        private Timer t;
        private Vertex vert;
        public Attack(int s,String d, Location l, int type){
                direction = d; //attacks have directions. This is for the purpose of movement and loading the proper image
                strength = s; 
                loc = l;
                Type = type;
                int tempy = loc.getY(); //we have to initialize the attack one square ahead of the player, else the game will detect a collision
                int tempx = loc.getX();//this will result in the player injuring himself and the attack being destroyed
                if(direction == "up"){
                        tempy+=-1.0;
                }
                else if(direction == "down"){
                        tempy+=1.0;
                }
                else if(direction == "right"){
                        tempx+=1.0;
                }
                else{
                        tempx+=-1.0;
                }
                
                loc.setLocation(tempx, tempy);
                vert = new Vertex(loc);
                t = new Timer(500,attackListener);
                t.start();
                
        }
        
        public int getStrength(){ //returns the strength of the attack
                return strength;
        }
        
        public void setLocation(Location l){ //method is called when attack is initialized and moves
                loc = l;
                
        }
        
        public Location getLocation(){ //getter for location
                return loc;
        }
        
        public int getType(){ //getter for type
        	return Type;
        }
        
        public Vertex getVertex(){ //vertex of attack
                return vert;
        }
        
        public String getDirection(){//getter method for direction
                return direction;
        }

        ActionListener attackListener = new ActionListener(){//listener that updates attacks location. According to the current settings
                public void actionPerformed(ActionEvent evt){//of mazeboard, this is called every 100ms. Attacks move quickly
                        int x = loc.getX();
                        int y = loc.getY();
                        if(direction == "up"){ 
                                y+=-1.0;//Note: Y-axis was flipped upside down. 
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

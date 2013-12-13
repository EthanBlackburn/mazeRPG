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
        
        private Grid grid;
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
                
                //level 4 has a boss, so only add the other two monsters if on the first three levels
                if(difficulty != 4) {
                		monsters.add(new Monster(grid.path, grid.path.getVertex(w-1, 0), 1, difficulty,person1));
                        monsters.add(new Monster(grid.path, grid.path.getVertex(w-1, h-1), 2, difficulty,person1));
                        monsters.add(new Monster(grid.path, grid.path.getVertex(0, h-1), 3, difficulty,person1));
                }
                else {
                	monsters.add(new Monster(grid.path, grid.path.getVertex(9, 9), 1, difficulty,person1));
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
                	System.out.print("attack loc");
                	attacks.get(i).getLocation().printLocation();
                		if(monsters.size() == 0) {
                			if(grid.getVertex2(attacks.get(i).getVertex().getX()/2, attacks.get(i).getVertex().getY()/2) != null ){//isnt detecting walls
                                collisions.add(attacks.get(i));
                            }
                		}
                        for(int k = 0; k < monsters.size();k++){
                        	System.out.print("monster loc = ");
                        	monsters.get(k).getLocation().printLocation();
                        	double monsterX = monsters.get(k).getLocation().getX();
                        	double monsterY = monsters.get(k).getLocation().getY();
                        	double personX = person1.getLocation().getX();
                        	double personY = person1.getLocation().getY();
                        	double attackX = attacks.get(i).getLocation().getX();
                        	double attackY = attacks.get(i).getLocation().getY();
                        	double diff1X = monsterX-attackX;
                        	double diff1Y = monsterY-attackY;
                        	double diff2X = personX-attackX;
                        	double diff2Y = personY-attackY;
                        	System.out.println("diff = " + (diff1X + diff1Y));
                        	System.out.println(monsters.get(k).getType());
                        	if(attacks.get(i).getType() == 0){
                        		if((diff1X == 0 & diff1Y <=2 & diff1Y>=-1) | (diff1Y == 0 & diff1X <=2 & diff1Y>=-2)){
	                                
	                                monsters.get(k).incrementHealth(-attacks.get(i).getStrength());
	                                System.out.println("health = " + monsters.get(k).getHealth());
	                                collisions.add(attacks.get(i));
	                                if(monsters.get(k).getHealth() <= 0){
	                                        monsters.remove(k);
	                                }
                        		}
                        	}
                        	else if((diff2X == 0 & diff2Y <=2 & diff2Y>=-1) | (diff2Y == 0 & diff2X <=2 & diff2Y>=-2)){
                                
                                person1.incrementHealth(-attacks.get(i).getStrength());
                                System.out.println("person health = " + person1.getHealth());
                                collisions.add(attacks.get(i));

                                
                        	}
                        	
                            if(grid.getVertex2(attacks.get(i).getVertex().getX()/2, attacks.get(i).getVertex().getY()/2) != null ){//isnt detecting walls
                                collisions.add(attacks.get(i));
                            }
                                
                                
                        }
                }
                if(collisions.isEmpty() == false){
                        
                        GI.removeAttacks(collisions);
                        
                }
                return collisions;
                
        }
        
}
import java.awt.FlowLayout;
import java.util.HashSet;
import java.util.Set;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
=======
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
=======
>>>>>>> df073f836643c24963154643eeaf4db6492c972a
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MazeBoard extends JPanel{
	
	
	private Level l;
	private Player person1;
	private int width;
	private int height;
	private Display disp;
	private Timer t;
	private final Set<Integer> pressed;
	
	
	public MazeBoard(int w, int h) {
		super();
		disp = new Display(1);
		width = w;
		height = h;
		l = new Level(1,w,h);
		
		pressed = new HashSet<Integer>();
		person1 = l.getPlayer();
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
		setLayout(new FlowLayout());
		add(disp);
		add(l);
		l.setFocusable(true);
		t = new Timer(200,Alistener);
		t.start();
		
	}
	
	public Level getLevel(){
		return l;
	}
	
	
	
	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public synchronized void keyPressed(KeyEvent e) {
			pressed.add(e.getKeyCode());
			if(pressed.contains(KeyEvent.VK_DOWN)){
			person1.move(0, 1.0);
				if (pressed.size() > 1) {
					if(pressed.contains(KeyEvent.VK_SPACE)){
						Location attackLoc = new Location(person1.getLocation());
						l.addAttack(new Attack(person1.getAttack(),"down",attackLoc, 0));
						pressed.remove(KeyEvent.VK_SPACE);
						
					}
				}
				//pressed.remove(KeyEvent.VK_DOWN);
				repaint();
			}
			else if(pressed.contains(KeyEvent.VK_UP)){
				person1.move(0, -1.0);
				if (pressed.size() > 1) {
		        	if(pressed.contains(KeyEvent.VK_SPACE)){
		        		Location attackLoc = new Location(person1.getLocation());
		        		l.addAttack(new Attack(person1.getAttack(),"up",attackLoc, 0));
		        		pressed.remove(KeyEvent.VK_SPACE);
		        		
		        	}
		        }
				//pressed.remove(KeyEvent.VK_UP);
				repaint();
			}
			
			else if(pressed.contains(KeyEvent.VK_LEFT)){
				person1.move(-1.0, 0);
				if (pressed.size() > 1) {
		        	if(pressed.contains(KeyEvent.VK_SPACE)){
		        		Location attackLoc = new Location(person1.getLocation());
		        		l.addAttack(new Attack(person1.getAttack(),"left",attackLoc, 0));
		        		pressed.remove(KeyEvent.VK_SPACE);
		        		
		        	}
		        }
				//pressed.remove(KeyEvent.VK_LEFT);
				repaint();
			}
			else if(pressed.contains(KeyEvent.VK_RIGHT)){
				person1.move(1.0, 0);
				if (pressed.size() > 1) {
		        	if(pressed.contains(KeyEvent.VK_SPACE)){
		        		Location attackLoc = new Location(person1.getLocation());
		        		l.addAttack(new Attack(person1.getAttack(),"right",attackLoc, 0));
		        		pressed.remove(KeyEvent.VK_SPACE);
		        		
		        	}
		        }
				
				repaint();			
			}
			else{
				pressed.clear();
			}
			
			if((person1.getVertex().getX() == 37) && (person1.getVertex().getY() == 37) & l.getDifficulty() < 4){ //if player is on stairs then go to next level
				int diff = l.getDifficulty()+1;
				remove(l);
				t.stop();
				disp.setLevel(diff);
				l = new Level(diff,width,height);
				person1 = l.getPlayer();
				add(l);
				revalidate();
				l.setFocusable(true);
				repaint();
				t.restart();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() != KeyEvent.VK_SPACE){
				pressed.remove(e.getKeyCode());
			}
		}
		
		
	}
	
	ActionListener Alistener = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			l.DetectCollision(l.getGI().getAttacks());
			for(int i = 0; i < l.monsters.size();i++){
				if(l.monsters.get(i).isClose()){
					String dir;
					if(person1.getVertex().getY() == l.monsters.get(i).getY() & person1.getVertex().getX() >= l.monsters.get(i).getX()){
						dir = "right";
					}
					else if(person1.getVertex().getY() == l.monsters.get(i).getY() & person1.getVertex().getX() < l.monsters.get(i).getX()){
						dir = "left";
					}
					else if(person1.getVertex().getX() == l.monsters.get(i).getX() & person1.getVertex().getY() >= l.monsters.get(i).getY()){
						dir = "down";
					}
					else {
						dir = "up";
					}
					l.addAttack(new Attack(l.monsters.get(i).getAttack(),dir,new Location(l.monsters.get(i).getLocation())));
				}
			}
			disp.setHealth(person1.getHealth());
			if(person1.getHealth() <= 0){
				t.stop();
				int n = JOptionPane.showConfirmDialog(null,"You lost! Play again?", "Game Over!",JOptionPane.YES_NO_OPTION);
				if(n==0){
					remove(l);
					disp.setLevel(1);
					l = new Level(1,width,height);
					person1 = l.getPlayer();
					add(l);
					revalidate();
					l.setFocusable(true);
					t.restart();
				}
				else{
					System.exit(ABORT);
				}
			}
			else if(l.getDifficulty() == 4 & l.monsters.size() == 0){
				t.stop();
				int n = JOptionPane.showConfirmDialog(null,"You won! Play again?", "Game Over!",JOptionPane.YES_NO_OPTION);
				if(n==0){
					remove(l);
					disp.setLevel(1);
					l = new Level(1,width,height);
					person1 = l.getPlayer();
					add(l);
					revalidate();
					l.setFocusable(true);
					t.restart();
				}
				else{
					System.exit(ABORT);
				}
			}
			repaint();
		}
	};
	
	
	
}



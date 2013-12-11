import java.awt.FlowLayout;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class MazeBoard extends JPanel{
	
	
	private Level l;
	private Player person1;
	private int delay;
	private Timer timer;
	private int width;
	private int height;
	private final Set<Integer> pressed;
	private ArrayList<Attack> attacks;
	private Person attacker;
	
	
	public MazeBoard(int w, int h) {
		super();
		delay = 500; 
		width = w;
		height = h;
		l = new Level(1,w,h);
		attacks = new ArrayList<Attack>();
		pressed = new HashSet<Integer>();
		person1 = l.getPlayer();
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
		setLayout(new GridLayout());
		add(l);
		l.setFocusable(true);
		timer = new Timer(delay, monsterRefresh);
		timer.start();
		
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
						attacks.add(new Attack(person1.getAttack(),"down",person1.getLocation()));
						pressed.remove(KeyEvent.VK_SPACE);
						attacker = person1;
					}
				}
				//pressed.remove(KeyEvent.VK_DOWN);
				repaint();
			}
			else if(pressed.contains(KeyEvent.VK_UP)){
				person1.move(0, -1.0);
				if (pressed.size() > 1) {
		        	if(pressed.contains(KeyEvent.VK_SPACE)){
		        		attacks.add(new Attack(person1.getAttack(),"up",person1.getLocation()));
		        		pressed.remove(KeyEvent.VK_SPACE);
		        		attacker = person1;
		        	}
		        }
				//pressed.remove(KeyEvent.VK_UP);
				repaint();
			}
			
			else if(pressed.contains(KeyEvent.VK_LEFT)){
				person1.move(-1.0, 0);
				if (pressed.size() > 1) {
		        	if(pressed.contains(KeyEvent.VK_SPACE)){
		        		attacks.add(new Attack(person1.getAttack(),"left",person1.getLocation()));
		        		pressed.remove(KeyEvent.VK_SPACE);
		        		attacker = person1;
		        	}
		        }
				//pressed.remove(KeyEvent.VK_LEFT);
				repaint();
			}
			else if(pressed.contains(KeyEvent.VK_RIGHT)){
				person1.move(1.0, 0);
				if (pressed.size() > 1) {
		        	if(pressed.contains(KeyEvent.VK_SPACE)){
		        		attacks.add(new Attack(person1.getAttack(),"right",person1.getLocation()));
		        		pressed.remove(KeyEvent.VK_SPACE);
		        		attacker = person1;
		        	}
		        }
				l.addAttacks(attacks);
				repaint();			
			}
			
			if((person1.getVertex().getX() == 37) && (person1.getVertex().getY() == 37)){ //if player is on stairs then go to next level
				int diff = l.getDifficulty()+1;
				remove(l);
				if(diff > 4){
					//either make fifth level with trophy or popup window
				}
				l = new Level(diff,width,height);
				person1 = l.getPlayer();
				attacks = new ArrayList<Attack>();
				add(l);
				revalidate();
				l.setFocusable(true);
				timer = new Timer(delay, monsterRefresh);
				timer.start();
				repaint();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() != KeyEvent.VK_SPACE){
				pressed.remove(e.getKeyCode());
			}
		}
	}
		
	
	ActionListener monsterRefresh = new ActionListener() { //movement sucks as of now
		  public void actionPerformed(ActionEvent evt) {
			  ArrayList<Attack> collided = new ArrayList<Attack>();
		      for(int i = 0; i < l.monsters.size(); i++){
				Vertex x = l.monsters.get(i).next(person1);
				collided.addAll(l.DetectCollision(attacks));
				int  newX = x.getX() - (int)l.monsters.get(i).getX();
				int  newY = x.getY() - (int)l.monsters.get(i).getY();
				l.monsters.get(i).move(newX,newY);
				repaint();
				
			  }
		      attacks.removeAll(collided);
		  }
		};
	
	
	
}



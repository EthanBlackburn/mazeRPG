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
	private int width;
	private int height;
	private Display disp;
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
						l.addAttack(new Attack(person1.getAttack(),"down",attackLoc));
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
		        		l.addAttack(new Attack(person1.getAttack(),"up",attackLoc));
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
		        		l.addAttack(new Attack(person1.getAttack(),"left",attackLoc));
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
		        		l.addAttack(new Attack(person1.getAttack(),"right",attackLoc));
		        		pressed.remove(KeyEvent.VK_SPACE);
		        		
		        	}
		        }
				
				repaint();			
			}
			
			if((person1.getVertex().getX() == 37) && (person1.getVertex().getY() == 37)){ //if player is on stairs then go to next level
				int diff = l.getDifficulty()+1;
				remove(l);
				if(diff > 4){
					//either make fifth level with trophy or popup window
				}
				l = new Level(diff,width,height);
				disp.setLevel(diff);
				person1 = l.getPlayer();
				add(l);
				revalidate();
				l.setFocusable(true);
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
	
	
	
}



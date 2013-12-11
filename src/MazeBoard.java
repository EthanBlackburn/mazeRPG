import java.awt.FlowLayout;
import java.util.Random;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
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

public class MazeBoard extends JPanel{
	
	
	private Level l;
	private Player person1;
	
	
	public MazeBoard(int w, int h) {
		super();
		
		l = new Level(4,w,h);
		person1 = l.getPlayer();
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
		setLayout(new GridLayout());
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
		public void keyPressed(KeyEvent e) {
			Random rnd = new Random();
			for(int i = 0; i < l.monsters.size(); i++){
				int tempx = (int)(l.monsters.get(i)).getX();
				int tempy = (int)(l.monsters.get(i)).getY();
				while((int)l.monsters.get(i).getX() == tempx & (int)l.monsters.get(i).getY() == tempy){
					int x = rnd.nextInt(3)-1;
					int y;
					if(x==0){
						y = (rnd.nextInt(2)-1);
						if(y == 0){
							y=1;
						}
					}
					else {
						y = 0;
					}
					(l.monsters.get(i)).move(x,y);
				}
			}
			switch(e.getKeyCode()){
			case KeyEvent.VK_DOWN:person1.move(0, 1.0);
			repaint();
			break;
			case KeyEvent.VK_UP:person1.move(0, -1.0);
			repaint();
			break;
			case KeyEvent.VK_LEFT:person1.move(-1.0, 0);
			repaint();
			break;
			case KeyEvent.VK_RIGHT:person1.move(1.0, 0);
			repaint();
			break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}
	
	
	
}



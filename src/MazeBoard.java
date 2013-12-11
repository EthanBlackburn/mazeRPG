import java.awt.FlowLayout;
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
	
	//board should have a person
	private Player person1;
	
	//board should have a grid, path, and walls
	private Grid grid;
	private Path path;
	private ArrayList<Wall> walls;
	private BufferedImage image;
	
	
	public MazeBoard(int w, int h) {
		super();
	
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
		setLayout(new GridLayout());
		
		grid = new Grid(w,h);
		//person1 = new Player()
		this.walls = new ArrayList();
		repaint();
	}
	
	public void addPath(Path p) {
		path = p;
	}
	
	public void addWall(Wall w) {
		Wall wall = w;
		walls.add(wall);
	}
	
	public Grid getGrid(){
		return grid;
	}
	
	
	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()){
			case KeyEvent.VK_DOWN:person1.move(0, -1);
			break;
			case KeyEvent.VK_UP:person1.move(0, 1);
			break;
			case KeyEvent.VK_LEFT:person1.move(-1, 0);
			break;
			case KeyEvent.VK_RIGHT:person1.move(1, 0);
			break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
	}
	
	
	
}



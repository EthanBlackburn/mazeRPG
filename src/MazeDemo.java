import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;


public class MazeDemo {

	static MazeBoard scene;
	public static void main(String[] args) {
		
		final JFrame frame = new JFrame();
	      
	      
	  
		//walls.add(new Wall(new Vertex(10,10),new Vertex(10,20)));
		scene = new MazeBoard(30,20);
	    //final Grid grid = new Grid(30,20);
	    final GameIcon wall = new GameIcon(scene.getGrid());
	    final JLabel comp = new JLabel(wall);
	    frame.setLayout(new FlowLayout());
	    frame.add (comp);
	    frame.add(scene);
	    frame.pack();
	     
	    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    frame.setVisible (true);
	    frame.repaint();

	}
	
	

}

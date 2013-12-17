
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class MazeDemo {

	public static void main(String[] args) {
		/*
		 * Main JFrame that contains everything.
		 * It initialized the mazeboard JPanel. Everything
		 * else is simply contained by mazeboard
		 */
		
		final JFrame frame = new JFrame();
	      
	      
	  
		
		final MazeBoard scene = new MazeBoard(19,19);
	    frame.setLayout(new FlowLayout());
	    frame.add(scene);
	    frame.pack();
	     
	    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    frame.setVisible (true);
	    frame.repaint();

	}
	
	

}

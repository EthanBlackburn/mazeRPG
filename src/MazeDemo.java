
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class MazeDemo {

	public static void main(String[] args) {
		
		final JFrame frame = new JFrame();
	      
	      
	  
		
		final MazeBoard scene = new MazeBoard(30,20);
	    frame.setLayout(new FlowLayout());
	    frame.add(scene);
	    frame.pack();
	     
	    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    frame.setVisible (true);
	    frame.repaint();

	}
	
	

}

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MazeDemo {

	public static void main(String[] args) {
		
		final JFrame frame = new JFrame();
	     
	    final Grid grid = new Grid(30,20);
		
	    final GameIcon wall = new GameIcon(grid);
	    final JLabel comp = new JLabel(wall);
	    frame.setLayout(new FlowLayout());
	    frame.add (comp);
	    frame.pack();
	     
	    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	    frame.setVisible (true);
	    frame.repaint();

	}

}

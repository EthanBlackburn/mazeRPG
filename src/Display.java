import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Display extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4347982897832715628L;
	private int level;
	private int health;
	public Display(int l){
		level = l;
		health = 200;
		JLabel healthLabel = new JLabel("Health: " + health);
		JLabel levelLabel = new JLabel("Level: " + level);
		setLayout(new FlowLayout());
		add(healthLabel);
		add(levelLabel);
	}
	
	public void decrementHealth(int dec){
		health -= dec;
	}
	
	public void setLevel(int l){
		level = l;
	}
}

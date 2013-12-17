import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Display extends JPanel {

	/**
	 * this class is the information bar for the game. It includes player health and level number
	 */
	private static final long serialVersionUID = 4347982897832715628L;
	private int level;
	private int health;
	private JLabel healthLabel;
	private JLabel levelLabel;
	public Display(int l){
		level = l;
		health = 200;
		healthLabel = new JLabel("Health: " + health);
		levelLabel = new JLabel("Level: " + level);
		setLayout(new BoxLayout(this,1));
		add(healthLabel);
		add(levelLabel);
	}
	
	public void setHealth(int h){ //health setter. Sets health to JLabel
		health =h;
		healthLabel.setText("Health: " + health);
	}
	
	public void setLevel(int l){ //level setter. Sets level to JLabel
		level = l;
		levelLabel.setText("Level: " + level);
	}

}

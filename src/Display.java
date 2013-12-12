import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Display extends JPanel {

	private int level;
	private int health;
	private JLabel healthLabel;
	private JLabel levelLabel;
	public Display(int l){
		level = l;
		health = 200;
		healthLabel = new JLabel("Health: " + health);
		levelLabel = new JLabel("Level: " + level);
		setLayout(new FlowLayout());
		add(healthLabel);
		add(levelLabel);
	}
	
	public void setHealth(int h){
		health =h;
		healthLabel.setText("Health: " + health);
	}
	
	public void setLevel(int l){
		level = l;
		levelLabel.setText("Level: " + level);
	}

}

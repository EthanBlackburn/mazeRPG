import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Display extends JPanel {

	/**
	 * 
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
<<<<<<< HEAD
		setLayout(new FlowLayout());
=======
		setLayout(new BoxLayout(this,1));
>>>>>>> b5044d3741109dfcb2560613112d068f4385a1d7
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

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JComponent;


public class IconAdapter extends JComponent{
/*adapter for the icons in this game. 
 * the icons represent the images used for each element in the game
 */
	private Icon icon;
	public IconAdapter(Icon icon){ 
		this.icon = icon;
	}
	
	public void paintComponent(Graphics g){ //paint method
		icon.paintIcon(this, g, 0, 0);
	}
	
	public Dimension getPreferredSize(){ //set dimension of icon
		return new Dimension(icon.getIconHeight(), icon.getIconHeight());
	}
}

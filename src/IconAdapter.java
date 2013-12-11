import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.JComponent;


public class IconAdapter extends JComponent{

	private Icon icon;
	public IconAdapter(Icon icon){
		this.icon = icon;
	}
	
	public void paintComponent(Graphics g){
		icon.paintIcon(this, g, 0, 0);
	}
	
	public Dimension getPreferredSize(){
		return new Dimension(icon.getIconHeight(), icon.getIconHeight());
	}
}

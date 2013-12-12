import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;


public class BackgroundIcon implements Icon{

	private Graph graph;
	private BufferedImage image;
	private int scaleFactor;
	
	public BackgroundIcon(Graph g) {
		graph = g;
	}
	
	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return graph.getHeight();
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return graph.getWidth();
	}

	@Override
	public void paintIcon(Component arg0, Graphics arg1, int x, int y) {
		Graphics2D g2 = (Graphics2D) arg1;
		scaleFactor = 17;
		java.net.URL url = getClass().getResource("/resources/environment/ground.png");
		try {                
	         image = ImageIO.read(url);
	    } catch (IOException ex) {
	           System.out.println("couldnt load ground image");
	    }
		
		Image temp= image.getScaledInstance(scaleFactor,scaleFactor,Image.SCALE_DEFAULT);
		for(int i = 0; i< getIconWidth(); i++){
			for(int j = 0; j< getIconHeight(); j++){
				g2.drawImage(temp,graph.getVertex(i,j).getX()*scaleFactor,graph.getVertex(i,j).getY()*scaleFactor,null);
				for(int k = 0; k< graph.getVertex(i, j).connections.size(); k++) {
					
					g2.drawImage(temp,graph.getVertex(i,j).getMidX(graph.getVertex(i, j).getConnection(k))*scaleFactor,graph.getVertex(i,j).getMidY(graph.getVertex(i, j).getConnection(k))*scaleFactor,null);
				}
			}
		}
		
	}

}

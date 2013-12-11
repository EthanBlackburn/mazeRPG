import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;


public class GraphIcon implements Icon{

	private Graph graph;
	private BufferedImage image;
	private int scaleFactor;
	
	public GraphIcon(Graph g) {
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
		java.net.URL url = getClass().getResource("/resources/environment/tree.png");
		try {                
	         image = ImageIO.read(url);
	    } catch (IOException ex) {
	           System.out.println("couldnt load tree image");
	    }
		for(int i = 0; i< getIconWidth(); i++){
			System.out.println("c");
			for(int j = 0; j< getIconHeight(); j++){
				System.out.println("d");
				g2.drawImage(image.getScaledInstance(scaleFactor,scaleFactor,Image.SCALE_DEFAULT),graph.getVertex(i,j).getX()*scaleFactor,graph.getVertex(i,j).getY()*scaleFactor,null);
				for(int k = 0; k< graph.getVertex(i, j).connections.size(); k++) {
					System.out.println("b");
					
					g2.drawImage(image.getScaledInstance(scaleFactor,scaleFactor,Image.SCALE_DEFAULT),graph.getVertex(i,j).getMidX(graph.getVertex(i, j).getConnection(k))*scaleFactor,graph.getVertex(i,j).getMidY(graph.getVertex(i, j).getConnection(k))*scaleFactor,null);
				}
			}
		}
		
	}

}

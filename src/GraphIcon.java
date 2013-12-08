import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.Icon;


public class GraphIcon implements Icon{

	private Graph graph;
	
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
	public void paintIcon(Component arg0, Graphics arg1, int arg2, int arg3) {
		System.out.println("a");
		Graphics2D g2 = (Graphics2D) arg1;
		System.out.println("x");
		for(int i = 0; i< getIconWidth(); i++){
			System.out.println("c");
			for(int j = 0; j< getIconHeight(); j++){
				System.out.println("d");
				for(int k = 0; k< graph.getVertex(i, j).connections.size(); k++) {
					System.out.println("b");
					Line2D.Double l1 = graph.getVertex(i, j).toLine((graph.getVertex(i,j)).getConnection(k), 10);
					g2.draw(l1);
				}
			}
		}
		
	}

}

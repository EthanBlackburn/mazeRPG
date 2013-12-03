import java.util.ArrayList;
import java.math.*;

public class Path {
	
	//there is one Path in the game and it is a set of Vertices
	private ArrayList<Vertex> Vertices;
	
	public Path() {
	}
	
	public void addVertex(Vertex vert) {
		Vertices.add(vert);
		for(int i = 0; i<Vertices.size(); i++){
			int dx = Vertices.get(i).getX() - vert.getX();
			int dy = Vertices.get(i).getY() - vert.getY();
			if(dx == 1 | dx == -1 | dy == 1 | dy == -1){
				vert.addConnection(Vertices.get(i));
			}
		}
	}
}

import java.util.ArrayList;
import java.math.*;

public class Path extends Graph{
	
	//there is one Path in the game and it is a set of Vertices
	private Vertex path[][];
	
	public Path(int w, int h) {
		super(w,h);
		for(int i = 0; i<w; i++) {
			for(int j = 0; j<h; j++) {
				graph[i][j] = new Vertex(2*i +1,2*j+1);
			}
		}
	}
}

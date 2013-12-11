import java.util.ArrayList;
import java.math.*;

public class Path extends Graph{
	
	public Path(int w, int h) {
		super(w,h);
		for(int i = 0; i<w; i++) {
			for(int j = 0; j<h; j++) {
				graph[i][j] = new Vertex(2*i +1,2*j+1);
			}
		}
	}
	
	
	//forward and backward pretty much arbitrarily chooses which is the forward and backward vertex.
	//For the sake of use the two functions only need to distinguish between any two connected vertices
	public Vertex forward(Vertex vert) {
		//some of the vertices only have one connection, in which case we don't distinguish between forward or backward
		if((vert.connections).size() > 1 ){
			return vert.connections.get(1);
		}
		else {
			return vert.connections.get(0);
		}
	}
	
	public Vertex backward(Vertex vert) {
		return vert.connections.get(0);
	}
}

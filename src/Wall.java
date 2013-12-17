
public class Wall extends Graph{
	
	//create a wall from existing vertices
	//by convention we will say w and h are the number rowsXcolumns of cells
	public Wall(int w, int h){
		//create the wall and connect all the neighboring vertices.
		super(w+1,h+1);
		for(int i = 0; i<= w ; i++) {
			for(int j = 0; j<= h; j++){
				graph[i][j] = new Vertex(2*i,2*j);
			}
		}
		//initially make every vertex connected to every adjacent vertex in the wall (this is before
		//we make the maze).
		for(int i = 0; i< w +1; i++) {
			for(int j = 0; j<h +1; j++){
					if(j<h)
						graph[i][j].addConnection(graph[i][j+1]);
					else if(j>0)
						graph[i][j].addConnection(graph[i][j-1]);
					if(i<w)
						graph[i][j].addConnection(graph[i+1][j]);
					else if(w>0)
						graph[i][j].addConnection(graph[i-1][j]);
			}
		}
	}
}

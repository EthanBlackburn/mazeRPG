
public abstract class Graph {

	protected Vertex[][] graph;
	private int height;
	private int width;
	
	//create a wall from existing vertices
	//by convention we will say w and h are the number rowsXcolumns of cells
	public Graph(int w, int h){
		//create the wall and connect all the neighboring vertices.
		graph = new Vertex[w][h];	
		width = w;
		height = h;
	}
	
	public Vertex getVertex(int i, int j) {
		return graph[i][j];
	}
	
	public int getHeight(){
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void resetMarkers(){
		for(int i = 0; i< width; i++){
			for(int j = 0; j< height; j++)
				graph[i][j].setNotInPath();
		}
	}
}

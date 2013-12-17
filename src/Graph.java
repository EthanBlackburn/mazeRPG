//graph is a simple implementation of a group of Vertices
//Vertices are stored in a 2D array
public abstract class Graph {

	protected Vertex[][] graph;
	private int height;
	private int width;
	
	//by convention we will say w and h are the number rowsXcolumns of cells
	public Graph(int w, int h){
		//create the wall and connect all the neighboring vertices.
		graph = new Vertex[w][h];	
		width = w;
		height = h;
	}
	
	
	public Vertex getVertex(int i, int j) {//returns the Vertex at a given integer coordinate
		try{
			return graph[i][j];
		}catch(Exception e){
			
			return graph[0][0];
		}
	}
	
	public int getHeight(){//returns the total number of rows 
		return height;
	}
	
	public int getWidth() {//returns the total number of columns
		return width;
	}
	
	public void resetMarkers(){//rest all the markers
		for(int i = 0; i< width; i++){
			for(int j = 0; j< height; j++)
				graph[i][j].setNotInPath();
		}
	}
}

import java.util.Random;
//import java.lang.Math;
public class Grid {
	
	//grid will have walls and a path
	private Vertex walls[][];
	private Vertex path[][];

	//represents the walls width and height
	private int width;
	private int height;
	
	//represents the number of cells width and height
	private int cellsWidth;
	private int cellsHeight;
	
	//instantiate a grid of size wXh vertices for the path and (w+1)X(h+1) vertices for walls
	//connect all the vertices to their neighboring vertices. These will begin as walls and will be erased as the maze is created
	public Grid(int w, int h) {
		//create the wall and connect all the neighboring vertices.
		walls = new Vertex[w+1][h+1];
		for(int i = 0; i<= w ; i++) {
			for(int j = 0; j<= h; j++){
				walls[i][j] = new Vertex(2*i,2*j);
			}
		}
		for(int i = 0; i< w +1; i++) {
			for(int j = 0; j<h +1; j++){
					if(j<h)
						walls[i][j].addConnection(walls[i][j+1]);
					else if(j>0)
						walls[i][j].addConnection(walls[i][j-1]);
					if(i<w)
						walls[i][j].addConnection(walls[i+1][j]);
					else if(w>0)
						walls[i][j].addConnection(walls[i-1][j]);
			}
		}
		//create the "Path"
		path = new Vertex[w][h];
		for(int i = 0; i<w; i++) {
			for(int j = 0; j<h; j++) {
				path[i][j] = new Vertex(2*i +1,2*j+1);
			}
		}
		width = w+1;
		height = h+1;
		cellsWidth = w;
		cellsHeight = h;
		makeMaze(1,1);
	}
	
	//function to create a maze from the walls and path vertices using a randomized depth first search method
	public boolean makeMaze(int x, int y) {
		//if out of bounds return false
		if(x < 0 | x >= cellsWidth | y<0 | y>=cellsHeight) {
			return false;
		}
		//if vertex is already in path, return false
		else if(path[x][y].inPath()){
			return false;
		}
		//this is the case where we attempt to connect to an adjacent path vertex that hasn't been marked
		else {
			//create random number generator
			Random rand = new Random();
			path[x][y].setInPath();
			int a = x;
			int b = y;
			boolean triedOne = false;
			boolean triedTwo = false;
			boolean triedThree = false;
			boolean triedFour = false;
			while(!triedOne | !triedTwo | !triedThree | !triedFour){
				int numX = rand.nextInt(2);
				int numY = rand.nextInt(2);
				
				if(numX == 0) {
					if(numY == 0 & !triedOne){
						a = x;
						b = y-1;
						triedOne = true;
					}
					else if(numY == 1 & !triedTwo){
						a = x+1;
						b = y;
						triedTwo = true;
					}
				}
				else {
					if(numY == 0 & !triedThree){
						a = x-1;
						b = y;
						triedThree = true;
					}
					else if(numY ==1 & !triedFour){
						a = x;
						b = y+1;
						triedFour = true;
					}
				}
				if(makeMaze(a,b)){
					path[x][y].addConnection(path[a][b]);
					if(y == b){
						int m = x;
						if(a>x){
							m = a;
						}
						walls[m][y].removeConnection(walls[m][y+1]);
					}
					else if( x == a){
						int m = y;
						if(b>y){
							m = b;
						}
						walls[x][m].removeConnection(walls[x+1][m]);
					}
				}
			}
			return true;
		}
		
	}
	
	//return width
	public int getWidth() {
		return width;
	}
	
	//return height
	public int getHeight() {
		return height;
	}
	
	public Vertex getVertex(int i, int j){
		return path[i][j];
	}
	
	public Vertex getVertex2(int i, int j) {
		return walls[i][j];
	}
}

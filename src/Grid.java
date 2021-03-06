import java.util.ArrayList;
import java.util.Random;
//import java.lang.Math;
public class Grid {
	
	//grid will have walls and a path
	protected Wall walls;
	protected Path path;
	
	//width and height will measure the number of cell rows and columns
	private int width;
	private int height;
	
	//instantiate a grid of size wXh vertices for the path and (w+1)X(h+1) vertices for walls
	//connect all the vertices to their neighboring vertices. These will begin as walls and will be erased as the maze is created
	public Grid(int w, int h) {
		
		width = w;
		height = h;
		
		//create the wall and connect all the neighboring vertices.
		walls = new Wall(w,h);
		path = new Path(w,h);
		
		makeMaze(1,1);
		
		//reset all of the markers of path
		resetMarkers();
		
		
	}
	
	public void resetMarkers() {
		path.resetMarkers();
		walls.resetMarkers();
	}
	
	//function to create a maze from the walls and path vertices using a randomized DFS method
	public boolean makeMaze(int x, int y) {
		//if out of bounds return false
		if(x < 0 | x >= width | y<0 | y>=height) {
			return false;
		}
		//if vertex is already in path, return false
		else if(path.getVertex(x, y).inPath()){
			return false;
		}
		//this is the case where we attempt to connect to an adjacent path vertex that hasn't been marked
		else {
			//create random number generator
			Random rand = new Random();
			path.getVertex(x, y).setInPath();
			//instantiate a and b
			int a = 5;// = x;
			int b = 5;// = y;
			

			//four possible random attempts from any path vertex. it is to our benefit
			//to insure that we don't try any possibility more than once.
			//each boolean below keeps track of these attempts.
			boolean triedOne = false;
			boolean triedTwo = false;
			boolean triedThree = false;
			boolean triedFour = false;
			while(!triedOne | !triedTwo | !triedThree | !triedFour){
				int numX;
				int numY;
				
				//set numX/Y to a random bit value, but only if both 1 and 0 are still
				//possible enumerations for numX and numY respectively
				if(!triedOne | !triedTwo){
					if(!triedThree | !triedFour){
						numX = rand.nextInt(2);
					}
					else {
						numX = 0;
					}
				}
				else{
					numX = 1;
				}
				
				if(!triedOne | !triedThree){
					if(!triedTwo | !triedFour){
						numY = rand.nextInt(2);
					}
					else {
						numY = 0;
					}
				}
				else{
					numY = 1;
				}
				
				//(numX,numY) == (0,0), (0,1), (1,0), (1,1) represent the four possible 
				//adjacent cells (path vertices) to (x,y).
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
					removeMazeWall(x,y,a,b);
				}
			}
			return true;
		}
		
	}
	
	//removeWall will remove the wall between the path vertices at location (x,y) and (a,b)
	//and connect (x,y) and (a,b) in the path.
	private void removeMazeWall(int x, int y, int a, int b) {
		//return if !(abs(x-a)==1 xor abs(y-b)==1) to insure correct path connections
		if(!(((x-a == 1 | x-a == -1) & y-b==0) | ((y-b == 1 | y-b == -1) & x-a==0))){
			return;
		}
		
		//add the desired path connection
		path.getVertex(x, y).addConnection(path.getVertex(a, b));
		
		//path.getVertex(a, b).addConnection(newVert);
		//if y==b then the path vertices in question are horizontal to each other
		if(y == b){
			int m = x;
			if(a>x){
				m = a;
			}
			walls.getVertex(m, y).removeConnection(walls.getVertex(m,y+1));
		}
		else if(x==a){
			int m = y;
			if(b>y){
				m = b;
			}
			walls.getVertex(x,m).removeConnection(walls.getVertex(x+1,m));
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
	
	public Vertex getVertex2(int i, int j) {
		if(walls.getVertex(i,j)!= null){
			return walls.getVertex(i,j);
		}
		else{
			return null;
		}
	}
	
}

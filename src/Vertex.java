import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;


//this class will be the basis for any graph
public class Vertex {
	
	//vertex location
	public Location loc;
	protected ArrayList<Vertex> connections;
	
	//isInPath marks a vertex if it is part of the path of the maze
	private boolean isInPath;
	
	//create vertex with a location and an empty connections
	public Vertex(int x, int y) {
		loc = new Location(x,y);
		connections = new ArrayList();
		isInPath = false;
	}
	
	//create vertex from an existing location
	public Vertex(Location loc1) {
		loc = new Location(loc1);
		connections = new ArrayList();
		isInPath = false;
	}
	
	public Vertex(Vertex vert) {
		loc = new Location(vert.loc);
		connections = vert.connections;
		isInPath = false;
	}
	
	//set the vertex location
	public void setVertex(int x, int y) {
		loc.setLocation(x,  y);
	}
	
	//returns the integer location of the x coordinate of the vertex
	public int getX() {
		return (int)loc.getX();
	}
	
	//returns the integer location of the y coordinate of the vertex
	public int getY() {
		return (int)loc.getY();
	}
	
	public int getMidX(Vertex v){
		return (v.getX() + getX())/2;
	}
	
	public int getMidY(Vertex v){
		return (v.getY() + getY())/2;
	}
	
	//returns the location of the vertex
	public Location getLocation() {
		return loc;
	}
	
	public void setNotInPath() {
		isInPath = false;
	}
	
	
	
	public void setInPath() {
		isInPath = true;
	}
	
	public boolean inPath() {
		return isInPath;
	}
	
	//checks if a certain vertex is in the connections
	public boolean isInConnections(Vertex Vert) {
		for(int i = 0; i< connections.size(); i++){
			if(connections.get(i) == Vert) {
				return true;
			}
		}
		return false;
	}
	
	//add a connection to the vertex
	public void addConnection(Vertex newVert) {
		//only add a vertex to the connections if it isn't already in there
		if(!isInConnections(newVert)){
			connections.add(newVert);
		}
		//then make sure both vertices are in each others connections
		if(!newVert.isInConnections(this)){
			newVert.addConnection(this);
		}
		
	}
	
	//remove a connections
	public void removeConnection(Vertex oldVert) {

		//remove connection only if it is in the connections
		if(isInConnections(oldVert)){
			connections.remove(oldVert);

		}
		if(oldVert.isInConnections(this)){
			oldVert.removeConnection(this);

		}
	}
	
	//get the connected vertex at a certain index
	public Vertex getConnection(int i) {
		if(connections.size() > 0){
			return connections.get(i);
		}
		else {
			return null;
		}
	}
	
	//create a new Point2D.Double out of a vertex
	public Point2D.Double toPoint(int modif, int x, int y) {
		return new Point2D.Double(modif*getX()+x, modif*getY()+y);
	}
	
	//create a new Line2D.Double out of two virtices
	public Line2D.Double toLine(Vertex vert, int modif, int x, int y) {
		return new Line2D.Double(toPoint(modif, x, y), vert.toPoint(modif,x,y));
	}
	
	public ArrayList<Vertex> adjacentVertices(Person p){
		ArrayList<Vertex> moves = new ArrayList<Vertex>();
		if(p.isValidMove(1.0, 0)){
			moves.add(new Vertex(p.getVertex().getX()+1,p.getVertex().getY()));
		}
		if(p.isValidMove(-1.0, 0)){
			moves.add(new Vertex(p.getVertex().getX()-1,p.getVertex().getY()));
		}
		if(p.isValidMove(0, 1.0)){
			moves.add(new Vertex(p.getVertex().getX(),p.getVertex().getY()+1));
		}
		if(p.isValidMove(0, -1.0)){
			moves.add(new Vertex(p.getVertex().getX(),p.getVertex().getY()-1));
		}
		
		return moves;
	}
	
	
}

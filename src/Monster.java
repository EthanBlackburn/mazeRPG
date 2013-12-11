import java.util.ArrayList;
import java.util.Hashtable;


public class Monster extends Person {

	private int Type;
	private int Level;
	private Hashtable<Vertex, String> mPath;
	
	protected Monster(Path p, Vertex v, int type, int level) {
		super(p, v);
		Type = type;
		Level = level;
		if(level == 1){
			setAttack(15);
		}
		if(level == 2){
			setAttack(25);
		}
		if(level == 3){
			setAttack(40);
		}
		else{
			setAttack(50);
		}
		mPath = new Hashtable<Vertex, String>();
		
	}
	
	public int getLevel() {
		return Level;
	}

	public int getType() {
		return Type;
	}

	
	public Vertex next(Player p){
		Vertex v = this.getVertex();
		mPath.put(v, "discovered");
		ArrayList<Vertex> locations = new ArrayList<Vertex>();
		locations.addAll(v.adjacentVertices(this));
		for(int i = 0; i < locations.size(); i++){
			
			Vertex check = locations.get(i);
			
			if(check == p.getVertex()){
				System.out.println("near player");
				return check;
			}
			System.out.println(check.getX());
			System.out.println(check.getY());
			if(mPath.containsKey(check) == false){
				mPath.put(check,"discovered");
				System.out.println("here");
				return check;	
			}
			
		}
		for(int i = 0; i < locations.size();i++){ //If we're here, then we need to retrace steps in maze
			Vertex check = locations.get(i);
			if(mPath.get(check) == "discovered"){
				mPath.put(check, "explored");
				return check;
			}
		}
		
		return null;
		
	}
}

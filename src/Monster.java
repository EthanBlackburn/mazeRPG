
public class Monster extends Person {

	private int Type;
	private int Level;
	
	protected Monster(Path p, Vertex v, int type, int level) {
		super(p, v);
		Type = type;
		Level = level;
		
	}
	
	public int getLevel() {
		return Level;
	}

	public int getType() {
		return Type;
	}
}


public class Player extends Person{
	//Player has a location
	private int level;
	public Player(Path p, Vertex v) {
		super(p,v);
		maxHealth = 200;
		setHealth(maxHealth);
		level = 1;
	}
}

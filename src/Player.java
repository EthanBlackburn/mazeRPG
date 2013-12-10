
public class Player extends Person{
	//Player has a location
	public Player(Path p, Vertex v) {
		super(p,v);
		maxHealth = 200;
		setHealth(maxHealth);
	}
}

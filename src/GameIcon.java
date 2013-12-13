import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.Icon;

public class GameIcon implements Icon{

	private Grid grid;
	private int height;
	private int width;
	private Player play;
	private int difficulty;
	private ArrayList<Monster> monsters;
	private ArrayList<Attack> attacks;
	
	
	public GameIcon(Grid g, Player p, ArrayList<Monster> m){
		attacks = new ArrayList<Attack>();
		grid = g;
		height = 42*grid.getHeight();
		width = 42*grid.getWidth();
		if(height > 42*17){
			height = 42*17;
		}
		if(width > 62*17){
			width = 62*17;
		}
		play = p;
		monsters = m;
		if(monsters.size() ==1){
			difficulty = 4;
		}
		else {
			difficulty = 1;
		}
	}
	
	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return height;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	
	public ArrayList<Attack> getAttacks(){
		return attacks;
	}
	
	public void addAttack(Attack a){
		attacks.add(a);
	}
	
	public void addAttacks(ArrayList<Attack> a){
		attacks.addAll(a);
	}
	
	public void removeAttacks(ArrayList<Attack>a){
		attacks.removeAll(a);
	}
	

	@Override
	public void paintIcon(Component arg0, Graphics arg1, int arg2, int arg3) {
		Graphics2D g2 = (Graphics2D) arg1;
		

		
		int dx = 5;
		int dy = 5;
			
			
			
		BasicStroke stroke2 = new BasicStroke(10);
		//create a GraphIcon for the grid.walls and paint it black
		g2.setStroke(stroke2);
		GraphIcon w = new GraphIcon(grid.walls);
		w.paintIcon(arg0, g2, dx, dy);	
		
	
		BackgroundIcon background = new BackgroundIcon(grid.path);
		background.paintIcon(arg0, g2, 0, 0);
		GraphIcon wall = new GraphIcon(grid.walls);
		
		wall.paintIcon(arg0, g2, dx, dy);
		
		//draw monsters
		for(int i = 0; i<monsters.size(); i++) {
			MonsterIcon monster = new MonsterIcon(monsters.get(i));
			monster.paintIcon(arg0, g2, 17*(int)((monsters.get(i)).getX()-1), 17*(int)((monsters.get(i)).getY()-1));
		}
		if(difficulty<4){
			StairsIcon stairs = new StairsIcon();
			stairs.paintIcon(arg0, g2, 0, 0);
		}
		
		//draw player
		PlayerIcon person = new PlayerIcon(play);
		person.paintIcon(arg0, g2, 17*(int)play.getX(), 17*(int)play.getY()-3);
		//draw attacks
		for(int i = 0; i < attacks.size();i++){
			AttackIcon atkIcon = new AttackIcon(attacks.get(i));
			atkIcon.paintIcon(arg0, g2, 0, 0);
		}
		
	}

}

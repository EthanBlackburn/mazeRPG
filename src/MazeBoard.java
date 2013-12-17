import java.awt.FlowLayout;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MazeBoard extends JPanel{
	
	
	private Level l;
	private Player person1;
	private int width;
	private int height;
	private Display disp;
	private JButton help;
	private int delay[];
	private Timer t;
	private Timer s;
	private final Set<Integer> pressed; //set of pressed keys
	
	/*
	 * class that contains all elements of the game
	 * holds and instance of level. Each time a level is cleared,
	 * the current instance is destroyed and a new one is created with
	 * greater difficulty. The levels have "refresh" timers of 100ms to
	 * create the smallest delay possible for movements and attacks
	 * while allowing the game to run smoothly
	 */
	public MazeBoard(int w, int h) {
		super();
		disp = new Display(1);
		help = new JButton("Help");
		width = w;
		height = h;
		delay = new int[3];
		for(int i = 0; i<3; i++){
			delay[i] = 0;
		}
		l = new Level(1,w,h);
		
		pressed = new HashSet<Integer>();
		person1 = l.getPlayer();
		KeyListener listener = new MyKeyListener();
		addKeyListener(listener);
		setFocusable(true);
		setLayout(new FlowLayout());
		add(disp);
		add(l);
		l.setFocusable(true);
		t = new Timer(100,Alistener); //level is updated ever 100ms
		s = new Timer(100,Mlistener);
		t.start();
		s.start();
		help.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String instructions = "Welcome!\n"
						+ "++++++++++++++\n"
						+"How to play:\n"
						+"The object of the game is to reach the top level\n"
						+"and defeat the final boss. The monsters will attack\n"
						+"you as you work your way to the top.\n"
						+"+++++++++++++++\n"
						+"Movement is done via the arrow keys. You can shoot\n"
						+"via the wasd keys or by holding space and pressing\n"
						+"an arrow. Good luck!\n";
				JOptionPane.showMessageDialog(null, instructions, "How to play", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		add(help);
	}
	
	
	//this method listens for pushes on the keyboard.
	//Only the arrow keys, spacebar and wasd key have an effect
	//all other keys are ignored
	public class MyKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public synchronized void keyPressed(KeyEvent e) {
			int temp = e.getKeyCode();
			boolean shoot = pressed.contains(KeyEvent.VK_SPACE);
			if(temp == KeyEvent.VK_DOWN){
				if(shoot) {
					Location attackLoc = new Location(person1.getLocation());
	        		l.addAttack(new Attack(person1.getAttack(),"down",attackLoc,0));
				}
				else {
					person1.move(0, 1);
				}
				repaint();
			}
			else if((temp == KeyEvent.VK_UP)){
				if(shoot) {
					Location attackLoc = new Location(person1.getLocation());
	        		l.addAttack(new Attack(person1.getAttack(),"up",attackLoc,0));
				}
				else {
					person1.move(0, -1);
				}
				repaint();
			}
			
			else if((temp == KeyEvent.VK_LEFT)){
				if(shoot) {
					Location attackLoc = new Location(person1.getLocation());
	        		l.addAttack(new Attack(person1.getAttack(),"left",attackLoc,0));
				}
				else {
					person1.move(-1, 0);
				}
				repaint();
			}
			else if((temp == KeyEvent.VK_RIGHT)){
				if(shoot) {
					Location attackLoc = new Location(person1.getLocation());
	        		l.addAttack(new Attack(person1.getAttack(),"right",attackLoc,0));
				}
				else {
					person1.move(1,0);
				}
				repaint();			
			}

			else if(temp == KeyEvent.VK_SPACE){
				pressed.add(temp);
			}
			else if(temp == KeyEvent.VK_W){
				Location attackLoc = new Location(person1.getLocation());
        		l.addAttack(new Attack(person1.getAttack(),"up",attackLoc,0));
        		repaint();
			}
			else if(temp == KeyEvent.VK_A){
				Location attackLoc = new Location(person1.getLocation());
        		l.addAttack(new Attack(person1.getAttack(),"left",attackLoc,0));
        		repaint();
			}
			else if(temp == KeyEvent.VK_S){
				Location attackLoc = new Location(person1.getLocation());
        		l.addAttack(new Attack(person1.getAttack(),"down",attackLoc,0));
        		repaint();
			}
			else if(temp == KeyEvent.VK_D){
				Location attackLoc = new Location(person1.getLocation());
        		l.addAttack(new Attack(person1.getAttack(),"right",attackLoc,0));
        		repaint();
			}
			
			else{
				pressed.clear();
			}
			
			if((((person1.getVertex().getX() == 37) && (person1.getVertex().getY() == 37)) | l.monsters.size() == 0) & l.getDifficulty() < 4){ //if player is on stairs or size of monsters is 0 then go to next level
				t.stop();
				s.stop();
				int diff = l.getDifficulty()+1;
				remove(l);
				t.stop();
				disp.setLevel(diff);
				l = new Level(diff,width,height);
				person1 = l.getPlayer();
				add(l);
				revalidate();
				l.setFocusable(true);
				repaint();
				pressed.clear();
				t.restart();
				s.restart();
				}

		}

		@Override
		public void keyReleased(KeyEvent e) { //once key is released, remove it from the set of pressed keys
			pressed.remove(e.getKeyCode());
		}
		
		
	}
	

	ActionListener Mlistener = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			for(int i = 0; i<3; i++){
				if(delay[i]<500){
					delay[i]+=100;
				}
			}
		}
	};
	//Alistener is called every 100ms to check for collisions, movement, changes in health, to repaint and to check for game-ending situations
	ActionListener Alistener = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			
			l.DetectCollision(l.getGI().getAttacks()); 
			for(int i = 0; i < l.monsters.size();i++){
				if(l.monsters.get(i).isClose() & delay[i]>=500){
					System.out.println("----");
					String dir;
					int diffx = person1.getX() - l.monsters.get(i).getX();
					int diffy = person1.getY() - l.monsters.get(i).getY();
					int absx = Math.abs(diffx);
					int absy = Math.abs(diffy);
					if(absy > absx) {
						dir = "down";
						if(diffy<0) {
							dir = "up";
						}
					}
					else {
						dir = "right";
						if(diffx<0) {
							dir = "left";
						}
					}
					l.addAttack(new Attack(l.monsters.get(i).getAttack(),dir, new Location( l.monsters.get(i).getLocation() ),1 ) );
					delay[i] = 0;

				}
			}
			disp.setHealth(person1.getHealth());
			if(person1.getHealth() <= 0){ //player is dead
				t.stop();
				s.stop();

				int n = JOptionPane.showConfirmDialog(null,"You lost! Play again?", "Game Over!",JOptionPane.YES_NO_OPTION);
				if(n==0){
					remove(l);
					disp.setLevel(1);
					l = new Level(1,width,height);
					person1 = l.getPlayer();
					add(l);
					revalidate();
					l.setFocusable(true);
					t.restart();

					s.restart();
				}
				else{
					System.exit(ABORT);
				}
			}
			else if(l.getDifficulty() == 4 & l.monsters.size() == 0){ //player beats final boss
				t.stop();

				s.stop();

				int n = JOptionPane.showConfirmDialog(null,"You won! Play again?", "Game Over!",JOptionPane.YES_NO_OPTION);
				if(n==0){
					remove(l);
					disp.setLevel(1);
					l = new Level(1,width,height);
					person1 = l.getPlayer();
					add(l);
					revalidate();
					l.setFocusable(true);
					t.restart();
					s.restart();
				}
				else{
					System.exit(ABORT);
				}
			}
			repaint();
		}
	};
	
	
	
	
	
}



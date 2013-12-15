import java.awt.FlowLayout;
import java.util.HashSet;
<<<<<<< HEAD
import java.util.Set;
=======
import java.util.Set;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
>>>>>>> b5044d3741109dfcb2560613112d068f4385a1d7
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MazeBoard extends JPanel{
	
	
	private Level l;
	private Player person1;
	private int width;
	private int height;
	private Display disp;
<<<<<<< HEAD
	private Timer t;
=======
	private int delay[];
	private Timer t;
	private Timer s;
>>>>>>> b5044d3741109dfcb2560613112d068f4385a1d7
	private final Set<Integer> pressed;
	
	
	public MazeBoard(int w, int h) {
		super();
		disp = new Display(1);
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
<<<<<<< HEAD
		t = new Timer(200,Alistener);
		t.start();
=======
		t = new Timer(100,Alistener);
		s = new Timer(100,Mlistener);
		t.start();
		s.start();
>>>>>>> b5044d3741109dfcb2560613112d068f4385a1d7
		
	}
	
	public Level getLevel(){
		return l;
	}
	
	
	
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
<<<<<<< HEAD
			else{
				pressed.clear();
			}
			
			if((person1.getVertex().getX() == 37) && (person1.getVertex().getY() == 37) & l.getDifficulty() < 4){ //if player is on stairs then go to next level
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
				t.restart();
=======
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
				String difficulty[] = {"first","second","third"};
				int n = JOptionPane.showConfirmDialog(null,"You Beat the " + difficulty[l.getDifficulty()-1]+" Level! continue?", "Game Over!",JOptionPane.YES_NO_OPTION);
				if(n==0){
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
				else{
					System.exit(ABORT);
				}
				
>>>>>>> b5044d3741109dfcb2560613112d068f4385a1d7
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			pressed.remove(e.getKeyCode());
		}
		
		
	}
	
<<<<<<< HEAD
	ActionListener Alistener = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			l.DetectCollision(l.getGI().getAttacks());
			for(int i = 0; i < l.monsters.size();i++){
				if(l.monsters.get(i).isClose()){
					String dir;
					if(person1.getVertex().getY() == l.monsters.get(i).getY() & person1.getVertex().getX() >= l.monsters.get(i).getX()){
						dir = "right";
					}
					else if(person1.getVertex().getY() == l.monsters.get(i).getY() & person1.getVertex().getX() < l.monsters.get(i).getX()){
						dir = "left";
					}
					else if(person1.getVertex().getX() == l.monsters.get(i).getX() & person1.getVertex().getY() >= l.monsters.get(i).getY()){
						dir = "down";
					}
					else {
						dir = "up";
					}
					l.addAttack(new Attack(l.monsters.get(i).getAttack(),dir,new Location(l.monsters.get(i).getLocation())));
=======
	ActionListener Mlistener = new ActionListener(){
		public void actionPerformed(ActionEvent evt){
			for(int i = 0; i<3; i++){
				if(delay[i]<500){
					delay[i]+=100;
				}
			}
		}
	};
	
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
>>>>>>> b5044d3741109dfcb2560613112d068f4385a1d7
				}
			}
			disp.setHealth(person1.getHealth());
			if(person1.getHealth() <= 0){
				t.stop();
<<<<<<< HEAD
=======
				s.stop();
>>>>>>> b5044d3741109dfcb2560613112d068f4385a1d7
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
<<<<<<< HEAD
=======
					s.restart();
>>>>>>> b5044d3741109dfcb2560613112d068f4385a1d7
				}
				else{
					System.exit(ABORT);
				}
			}
			else if(l.getDifficulty() == 4 & l.monsters.size() == 0){
				t.stop();
<<<<<<< HEAD
=======
				s.stop();
>>>>>>> b5044d3741109dfcb2560613112d068f4385a1d7
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
<<<<<<< HEAD
=======
					s.restart();
>>>>>>> b5044d3741109dfcb2560613112d068f4385a1d7
				}
				else{
					System.exit(ABORT);
				}
			}
			repaint();
		}
	};
	
	
	
}



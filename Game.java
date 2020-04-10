package Scripts;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel {

    Player p1 = new Player(this,645,345);
    Bateria bateria = new Bateria(this);
    
    public static int tiempo=20;
    
    Floor floors[][]=new Floor[5][10];
    
    Wall wall1 = new Wall(this,128,384,128,128); 
   Wall wall2= new Wall(this,256,384,128,128); 
    Wall wall3 = new Wall(this,384,384,128,128);
   
    Pilas pila = new Pilas(this,256,0);
    Enemy enemy = new Enemy (this,256,512);
    Enemy enemy2 = new Enemy(this,640,0);
    
    Trampa trampa = new Trampa(this,128,256);
    Trampa trampa2 = new Trampa(this,768,128);
    
   boolean lightsOn=false;
    
    int CamX=0;
    int CamY=0;
   
    
    public boolean isGameOver=false;
   
    public int getTiempo(){
        return tiempo;
    }
    
    public Game(){
        isGameOver=false;
        addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e){
           }
           @Override
           public void keyReleased(KeyEvent e){
               p1.keyReleased(e);
           }
           @Override
           public void keyPressed(KeyEvent e){
               p1.keyPressed(e);
           }
        });
        setFocusable(true);
    }
    int a=1;
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
	g2d.setColor(Color.white);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                floors[i][j]= new Floor(this,j*128,i*128);
                floors[i][j].paint(g2d);
            }
        }
        
        wall1.paint(g2d);
        wall2.paint(g2d);
        wall3.paint(g2d);
        
       //lightsOn=true;
        
        trampa.paint(g2d);
        trampa2.paint(g2d);
        enemy.paint(g2d);
        enemy2.paint(g2d); 
        pila.paint(g2d);
        bateria.paint(g2d);
        p1.paint(g2d);
    }
    float waitGameOver=5;
    
    public void gameOver(){
        
       
        if(isGameOver){
            
            p1.canMove=false;
             waitGameOver-=0.02;
            if(waitGameOver<=0){
                CamX=0;
                CamY=0;
                p1.gameOver();
                bateria.gameOver();
                pila.gameOver();
                waitGameOver=5;
                enemy.gameOver();
                enemy2.gameOver();
                isGameOver=false;
                trampa.gameOver();
                trampa2.gameOver();
            }
        }
        
    }
    
    private void move(){
        p1.move();
        bateria.countdown();
        gameOver();
    }
    
    void checkCollision(){
       wall1.checkCollision();
       wall2.checkCollision();
       wall3.checkCollision();
      
    }
    
    public void camera(){
        
    }
    
    public void checkWallCollision(){
        wall1.enemyWall();
        wall2.enemyWall();
        wall3.enemyWall();
        
    }
 
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Game");
        Game game = new Game();
        frame.add(game);
        frame.setSize(1286,669);
        
        frame.setResizable(false);
        game.setBackground(Color.black);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while(true){
            game.camera();
            game.repaint();
            game.move();
            Thread.sleep(20);
            
        }
    }
}
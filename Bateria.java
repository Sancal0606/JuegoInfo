package Scripts;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Bateria {
    Game game;
    public double countdown;
    public double numeroInicial;
    private int pila;
    Image p0;
    Image p1;
    Image p2;
    Image p3;
    Image p4;
    Image currentSprite;
    
    public Bateria(Game _game){
        game = _game;
        numeroInicial = 24;
        countdown = numeroInicial;
        pila = 4;
        ImageIcon b0 = new ImageIcon("src/Images/Bateria0.png");
        ImageIcon b1 = new ImageIcon("src/Images/Bateria1.png");
        ImageIcon b2 = new ImageIcon("src/Images/Bateria2.png");
        ImageIcon b3 = new ImageIcon("src/Images/Bateria3.png");
        ImageIcon b4 = new ImageIcon("src/Images/Bateria4.png");
    
        p0 = b0.getImage();
        p1 = b1.getImage();
        p2 = b2.getImage();
        p3 = b3.getImage();
        p4 = b4.getImage();
        currentSprite = p4;
    }
    
    void countdown(){
        if(game.p1.lightOn){
            countdown-=0.02;
        }
        if(countdown<=numeroInicial){
           pila=4;
           currentSprite = p4;
        }
        if(countdown<=(numeroInicial*.75)){
            pila=3;
            currentSprite = p3;
        }
        if(countdown<=(numeroInicial*.50)){
            pila=2;
            currentSprite = p2;
        }
        if(countdown<=(numeroInicial*.25)){
            pila=1;
            currentSprite = p1;
        }    
        if(countdown<=(numeroInicial*.0)){
            pila=0;
            game.p1.pila=false;
            game.p1.lightOn =false;
            currentSprite = p0;
        }
    }
    
    public void gameOver(){
        countdown = numeroInicial;
    }
    
    public void paint(Graphics2D g){
        g.setColor(Color.green);
        g.setFont(new Font("Verdana", Font.BOLD, 30));
        //g.drawString(String.valueOf(pila),(game.getWidth()/2)-20,30);
        g.drawImage(currentSprite,(game.getWidth()/2)-16 ,30, null);
    }
}

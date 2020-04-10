package Scripts;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Pilas {
    Game game;
    int radius = 50;
    Color color;
    public boolean isActive=true;
    int posX;
    int posY;
    boolean light;
    Image pila;
    Image currentImage;
    
    public Pilas(Game _game, int _posX, int _posY){
        game = _game;
        posX = _posX;
        posY = _posY;
        ImageIcon p0 = new ImageIcon("src/Images/Pila.png");
        pila = p0.getImage();
        currentImage = pila;
    }
    
    public void paint(Graphics2D g){
      if(isActive){
        if(collisionLight()&&game.p1.lightOn){
         light=true;
       }
       else{
           light=false;
       } 
       if(light){
         color = Color.green;
         currentImage=pila;
       }
       else{
           color = Color.black;
           currentImage=null;
       }
       g.setColor(color);
       g.drawImage(currentImage, posX+42-game.CamX,posY+20-game.CamY, null);
      
       checkCollision();
      }
      
    }
    
    void checkCollision(){
        if(collisionPlayer()){
            game.bateria.countdown+=(game.bateria.numeroInicial*.25);
            isActive=false;
            color=Color.green;
            game.p1.pila=true;
        }
    }
    
    public void gameOver(){
        isActive = true;
        
    }
    
    public Rectangle getBounds(){
        return new Rectangle(posX-game.CamX+42, posY-game.CamY+20, 44, 88);    
    }
    public Rectangle getBoundsLight(){
        return new Rectangle(posX-game.CamX, posY-game.CamY, 128, 128);    
    }
    
    private boolean collisionLight(){
        return game.p1.getBoundsLight().intersects(getBoundsLight());
    }
    
    private boolean collisionPlayer(){
        return game.p1.getBoundsPlayer().intersects(getBounds());
    }
}

package Scripts;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Floor {
    public Game game;
    Image image;
    ImageIcon i0;
    boolean light=true;
    int x;
    int y;
    
    
    Floor(Game _game,int posX,int posY){
        game = _game;
        x=posX;
        y=posY;
    }
    
    public void paint(Graphics g){
         i0 = new ImageIcon("src/Images/Floor.png");
         image = i0.getImage();
        if((collisionLight()&&game.p1.lightOn)||collisionPlayer()||game.lightsOn==true){
         light=true;
       }else{
            light=false;
        }
        if(light==true)
            g.drawImage(image, x-game.CamX, y-game.CamY, null);
    }
    
   private boolean collisionPlayer(){
        
        return game.p1.getBoundsPlayerLight().intersects(getBounds());
        
    }
    
     private boolean collisionLight(){
        
        return game.p1.getBoundsLight().intersects(getBounds());
        
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x-game.CamX, y-game.CamY, 128, 128);
    }
}

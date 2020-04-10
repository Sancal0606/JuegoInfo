package Scripts;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Trampa {
    Game game;
    int posX;
    int posY;
    Color color;
    boolean light;
    Image trampa;
    Image actualTrampa;
    
    public Trampa(Game _game, int _posX, int _posY){
        game = _game;
        posX = _posX;
        posY = _posY;
         ImageIcon lDL = new ImageIcon("src/Images/Trampa.png");
        trampa = lDL.getImage();
        actualTrampa=null;
    }
    
    public void paint(Graphics2D g){
       
        if(collisionLight()&&game.p1.lightOn){
         light=true;
       }
       else{
           light=false;
       }
        if(light){
            actualTrampa = trampa;
        }
        else if (!collisionPlayer()&&!collisionEnemy()&&!collisionEnemy2()){
            actualTrampa = null;
        }
          //g.setColor(color);
        //g.fillRect(posX-game.CamX, posY-game.CamY, 128, 128);
        g.drawImage(actualTrampa, posX-game.CamX, posY-game.CamY,null);
        collision();
    }
    
    void collision(){
        if(collisionPlayer()){
            color=Color.red;
            game.isGameOver = true;
            game.p1.positionX=posX+14;
            actualTrampa = trampa;
            game.p1.positionY=posY+7;
        }
        if(collisionEnemy()){
            color = Color.red;
            game.enemy.currentImage = game.enemy.front1;
            actualTrampa = trampa;
            game.enemy.isActive = false;
            game.enemy.x=posX;
            game.enemy.y = posY;
        }
         if(collisionEnemy2()){
            color = Color.red;
           game.enemy2.currentImage = game.enemy2.front1;
            actualTrampa = trampa;
            game.enemy2.isActive = false;
            game.enemy2.x=posX;
            game.enemy2.y = posY;
        }
    }
    
    public void gameOver(){
        actualTrampa = null;
    }
    
    private boolean collisionLight(){
        return game.p1.getBoundsLight().intersects(getBoundsTrampa());
    }
    
    private boolean collisionPlayer(){
        return game.p1.getBoundsPlayer().intersects(getBoundsTrampaPlayer());
    }
    private boolean collisionEnemy(){
        return game.enemy.getBounds().intersects(getBoundsTrampa());
    }
    
    private boolean collisionEnemy2(){
        return game.enemy2.getBounds().intersects(getBoundsTrampa());
    }
    
    public Rectangle getBoundsTrampaPlayer(){
        return new Rectangle(posX-game.CamX, posY-game.CamY, 100,50);
    }
    public Rectangle getBoundsTrampa(){
        return new Rectangle(posX-game.CamX, posY-game.CamY, 128,128);
    }
   
}

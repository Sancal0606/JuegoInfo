package Scripts;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Wall {
    Game game;
    
    int posX;
    int posY;
    int sizeX;
    int sizeY;
    boolean light;
    Color color;
    
    public Wall(Game _game, int _posX,int _posY, int _sizeX, int _sizeY){
        posX=_posX;
        posY=_posY;
        sizeX=_sizeX;
        sizeY=_sizeY;
        game =_game;
    }
    
    public void paint(Graphics2D g){
       g.setColor(color);
        g.fillRect(posX+(-game.CamX), posY+(-game.CamY), sizeX, sizeY);
        
    }
    
    public Rectangle getBoundsWall(){
        return new Rectangle(posX-game.CamX, posY-game.CamY, sizeX, sizeY);
    }

    public void checkCollision(){
         
         if(collisionLight()&&game.p1.lightOn){
         light=true;
       }
       else{
           light=false;
       }
         if(light){
             color = Color.white;
         }
          else{
           color = Color.black;
       }
         
       
       if((game.p1.positionX+(game.p1.horzMove*game.p1.speed)+65>posX&&
               game.p1.positionX+(game.p1.horzMove*game.p1.speed)<posX+sizeX)&&
               (game.p1.positionY+(game.p1.vertMove*game.p1.speed)+116>posY)&&
               (game.p1.positionY+(game.p1.vertMove*game.p1.speed)<posY+sizeY-80)){
        
           if(game.p1.vertMove==game.p1.speed)
               game.p1.vertMove=0; 
           if(game.p1.horzMove==-game.p1.speed)
               game.p1.horzMove=0;
           if(game.p1.vertMove==-game.p1.speed) 
                game.p1.vertMove=0;
           if(game.p1.horzMove==game.p1.speed)
                game.p1.horzMove=0;
       }
        
     }
 
    public void enemyWall(){
       
        if(game.enemy.x+game.enemy.xa+128>posX&&
               game.enemy.x+game.enemy.xa+6<posX+sizeX&&
               game.enemy.y+game.enemy.ya+108>posY&&
               game.enemy.y+game.enemy.ya+6<posY+sizeY){
            if(game.enemy.xa>0){
               game.enemy.xa=0; 
               game.enemy.tryY=true;
           }
           if(game.enemy.xa<0){
               game.enemy.xa=0;
               game.enemy.tryY=true;
           }
           if(game.enemy.ya<0){
                game.enemy.ya=0;    

           }
           if(game.enemy.ya>0){
               game.enemy.ya=0;

           }
            if(game.enemy.y+game.enemy.ya<posY-12+108&&game.enemy.x+game.enemy.xa+128>posX&&
               game.enemy.x+game.enemy.xa+6<posX+sizeX ){
               game.enemy.notYUp=true;
           }else
                game.enemy.notYUp=false;
            if(game.enemy.y+game.enemy.ya+108>posY+12&&game.enemy.x+game.enemy.xa+128>posX&&
               game.enemy.x+game.enemy.xa+6<posX+sizeX)
                game.enemy.notYDown=true;
            else
                game.enemy.notYDown=false;
            
       }
        enemy2Wall();
    }
    
    public void enemy2Wall(){
        if(game.enemy2.x+game.enemy2.xa+128>posX&&
               game.enemy2.x+game.enemy2.xa+6<posX+sizeX&&
               game.enemy2.y+game.enemy2.ya+108>posY&&
               game.enemy2.y+game.enemy2.ya+6<posY+sizeY){
           
           if(game.enemy2.xa>0){
               game.enemy2.x-=game.enemy2.speed; 
               game.enemy2.tryY=true;
           }
           if(game.enemy2.xa<0){
               game.enemy2.x+=game.enemy2.speed;
               game.enemy2.tryY=true;
           }
           if(game.enemy2.ya<0){
                game.enemy2.y+=game.enemy2.speed;    
                
           }
           if(game.enemy2.ya>0){
               game.enemy2.y-=game.enemy2.speed;
              
           }
           
           if(game.enemy2.y+game.enemy2.ya<posY-12+128&&game.enemy2.x+game.enemy2.xa+128>posX&&
               game.enemy2.x+game.enemy2.xa+6<posX+sizeX ){
               game.enemy2.notYUp=true;
           }else
                game.enemy2.notYUp=false;
            if(game.enemy2.y+game.enemy2.ya+108>posY+12&&game.enemy2.x+game.enemy2.xa+128>posX&&
               game.enemy2.x+game.enemy2.xa+6<posX+sizeX)
                game.enemy2.notYDown=true;
            else
                game.enemy2.notYDown=false;
               
       }
    }
    
    private boolean collisionLight(){
        return game.p1.getBoundsLight().intersects(getBoundsWall());
    }
   
    
}

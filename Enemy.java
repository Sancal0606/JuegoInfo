package Scripts;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class Enemy {
    Game game;
    int x;
    int y;
    int xa=0;
    int ya =0;
    int speed;
    int inicialX=800;
    int inicialY=600;
    int rangeX=300;
    int rangeY=300;
    String state;
    public Color color;
    public boolean isActive;
    public boolean seen;
    public boolean collisionX=false;
    public boolean collisionY=false;
    boolean light;
    
    Image front1;
    Image back1;
    Image right1;
    Image left1;
    Image front2;
    Image back2;
    Image right2;
    Image left2;
    Image currentImage;
    Image hide;
    
    boolean facingRight;
    boolean facingLeft;
    boolean facingBack;
    boolean facingFront=true;
    boolean isWalking;
    
    public Enemy(Game _game, int _x, int _y){
        game = _game;
        state = "idle";
        speed=1;
        color = Color.black;
        isActive = true;
        seen=false;
        x=_x;
        y=_y;
        inicialX = x;
        inicialY = y;
        
        
        ImageIcon f1 = new ImageIcon("src/Images/EnemyFront01.png");
        ImageIcon f2 = new ImageIcon("src/Images/EnemyFront02.png");
        ImageIcon b1 = new ImageIcon("src/Images/EnemyBack01.png");
        ImageIcon b2 = new ImageIcon("src/Images/EnemyBack02.png");
        ImageIcon l1 = new ImageIcon("src/Images/EnemyIzq01.png");
        ImageIcon l2 = new ImageIcon("src/Images/EnemyIzq02.png");
        ImageIcon r1 = new ImageIcon("src/Images/EnemyDer01.png");
        ImageIcon r2 = new ImageIcon("src/Images/EnemyDer02.png");
        ImageIcon h = new ImageIcon("src/Images/EnemyHide.png");
        
        front1= f1.getImage();
        back1 = b1.getImage();
        left1 = l1.getImage();
        right1 = r1.getImage();
        front2= f2.getImage();
        back2 = b2.getImage();
        left2 = l2.getImage();
        right2 = r2.getImage();
        hide = h.getImage();
        currentImage = null;
    }
    
    
    double wait3=1;
    boolean waitTres=false;
    public void paint (Graphics2D g){
        
        g.setColor(color);
        //g.fillRect(x-game.CamX, y-game.CamY, 128, 128);
        state();
        if(collisionLight()&&game.p1.lightOn){
         light=true;
       }
       else{
           light=false;
       }
       
        if(light){
            waitTres=true;
            color = Color.pink;
            anim();
            
        }
        if(wait3<0){
            seen=true;
        }
        if(waitTres)
            wait3-=0.02;
       g.drawImage(currentImage, x-game.CamX, y-game.CamY, null);
    }
    
    void state(){
        
        
       if(isActive){
        
        if(game.p1.lightOn&&seen){
           state="chase";
           chase();
           color=Color.pink;
           anim();
        }
        if(seen&&game.p1.lightOn==false){
            color=Color.blue;
            currentImage = hide;
        }
       }
        attack();
        
    }

    boolean tryY=false;
    boolean notYUp=false;
     boolean notYDown=false;
    
    void chase(){
        if(isActive) {
           
            
            if(game.p1.positionX-20-x<0&&tryY==false){
                xa=-speed;
            }
            if(game.p1.positionX-20-x>0&&tryY==false)
                xa=speed;
            if(game.p1.positionX-20-x==0)
                xa=0;
            if(collisionX==true)
                xa=0;
     
            if(game.p1.positionX-20==x||tryY==true){
                if(game.p1.positionY-y<0){
                    ya=-speed;
                    if(tryY==true&&notYDown==false)
                        ya-=2;
                }
                    
                if(game.p1.positionY-y>0){
                    ya=speed;
                    if(tryY==true&&notYUp==false)
                        ya+=2;
                }
                if(game.p1.positionY-y==0)
                    ya=0;
               
            } 
            if(game.p1.positionX-20!=x&&tryY==false)
                ya=0;
              
            tryY=false;    
           
            game.checkWallCollision();
           if(xa<0){
                
                facingRight=false;
                facingLeft=true;
                facingBack=false;
                facingFront=false;
           }
           if(xa>0){
                facingRight=true;
                facingLeft=false;
                facingBack=false;
                facingFront=false;
           }
           if(ya<0){
                facingRight=false;
                facingLeft=false;
                facingBack=true;
                facingFront=false;
           }
           if(ya>0){
                facingRight=false;
                facingLeft=false;
                facingBack=false;
                facingFront=true;
           }
           
            
            x+=xa;  
            y+=ya; 
        }
    }      
        
    void attack(){
        if(isActive){
        if(collisionPlayer()){
            isActive=false;
            game.isGameOver=true;
            currentImage = front1;
        }
    }
    }
    private boolean collisionPlayer(){
        
        return game.p1.getBoundsPlayer().intersects(getBoundsPlayer());
        
    }
    
     private boolean collisionLight(){
        
        return game.p1.getBoundsLight().intersects(getBoundsLight());
        
    }
    
    public void gameOver(){
        x = inicialX;
        y = inicialY;
        isActive=true;
        color = Color.black;
        wait3=1;
        currentImage = null;
        waitTres=false;
        seen=false;
        facingFront=true;
        facingBack=false;
        facingLeft=false;
        facingRight=false;
    }
    double seg=0;
    int a =0;
    public void anim(){
       if(isActive){
          
        if(facingLeft){
            if(seg<=0){
                    if(a==0){
                        currentImage=left2;
                        a=1;
                    }
                    else if(a==1){
                        currentImage=left1;
                        a=0;
                    }
                    seg=1;
                    
                }
                seg-=0.02;
        }
        if(facingRight){
            if(seg<=0){
                    if(a==0){
                        currentImage=right2;
                        a=1;
                    }
                    else if(a==1){
                        currentImage=right1;
                        a=0;
                    }
                    seg=1;
                    
                }
                seg-=0.02;
        }
        if(facingBack){
             if(seg<=0){
                    if(a==0){
                        currentImage=back2;
                        a=1;
                    }
                    else if(a==1){
                        currentImage=back1;
                        a=0;
                    }
                    seg=1;
                    
                }
                seg-=0.02;
        }
        if(facingFront){
             if(seg<=0){
                    if(a==0){
                        currentImage=front2;
                        a=1;
                    }
                    else if(a==1){
                        currentImage=front1;
                        a=0;
                    }
                    seg=1;
                    
                }
                seg-=0.02;
        }
       }
    }
    
    public Rectangle getBounds(){
        return new Rectangle(x+(-game.CamX)+24, y+(-game.CamY)+24, 80, 108);
    }
    
    public Rectangle getBoundsPlayer(){
        return new Rectangle(x+(-game.CamX)+24, y+(-game.CamY), 100, 128);
    }
    
    public Rectangle getBoundsLight(){
        return new Rectangle(x+(-game.CamX), y+(-game.CamY), 140, 128+16);
    }
    
}

package Scripts;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player {
    private Game game;
    public int horzMove=0;
    public int vertMove=0;
    public int positionX=300;
    public int positionY=10;
    public int speed;
    public boolean couldMove=true;
    int inicialX;
    int inicialY;
    
    public Image actualSprite;
    private Image sprite00;
    private Image sprite01;
    private Image sprite02;
    private Image sprite03;
    private Image sprite04;
    private Image WalkingFront01;
    private Image WalkingFront02;
    private Image WalkingRight01;
    private Image WalkingLeft01;
    private Image WalkingBack01;
    private Image WalkingBack02;
    
    private Image lampDer;
    private Image lampIzq;
    private Image lampArr;
    private Image lampAba;
    private Image lampArrDer;
    private Image lampAbaDer;
    private Image lampArrIzq;
    private Image lampAbaIzq;
    private Image actualLamp;
    
    public int spriteSizeX;
    public int spriteSizeY;    
    
    private int addX=0;
    private int addY=114;
    private int addX2=0;
    private int addY2 = 114;
    private int sizeX=40;
    private int sizeY=110;
    public int col=1;
    
            
            
            
    
    public boolean lightOn = false;
    public boolean pila = true;
    public int dirHorzLight=0;
    public int dirVertLight = 0;
    
    boolean canMove=true;
    
    boolean facingRight;
    boolean facingLeft;
    boolean facingBack;
    boolean facingFront=true;
    boolean isWalking;
    
    
    public Player (Game _game, int _x, int _y){
        this.game = _game;
        positionX = _x;
        positionY = _y;
        inicialX = _x;
        inicialY = _y;
        speed = 2;
        loadSprite();
         addY = 114;
        addY = 114;
        addX=0;
        addY2 = 114;
        addX2=50;
        sizeX=1;
        sizeY=116;
    }
    
    private void loadSprite(){
        
        ImageIcon i0 = new ImageIcon("src/NewPlayer/IdleFront.png");
        ImageIcon i1 = new ImageIcon("src/NewPlayer/IdleFront.png");
        ImageIcon i2 = new ImageIcon("src/NewPlayer/IdleLeft.png");
        ImageIcon i3 = new ImageIcon("src/NewPlayer/IdleBack.png");
        ImageIcon i4 = new ImageIcon("src/NewPlayer/IdleRight.png");
        ImageIcon wR = new ImageIcon("src/NewPlayer/WalkingRight01.png");
        ImageIcon wF1 = new ImageIcon("src/NewPlayer/WalkingFront01.png");
        ImageIcon wF2 = new ImageIcon("src/NewPlayer/WalkingFront02.png");
        ImageIcon wL = new ImageIcon("src/NewPlayer/WalkingLeft01.png");
        ImageIcon wB1 = new ImageIcon("src/NewPlayer/WalkingBack01.png");
        ImageIcon wB2 = new ImageIcon("src/NewPlayer/WalkingBack02.png");
        ImageIcon lL = new ImageIcon("src/Images/LampIzq.png");
        ImageIcon lU = new ImageIcon("src/Images/LampArr.png");
        ImageIcon lD = new ImageIcon("src/Images/LampAba.png");
        ImageIcon lR = new ImageIcon("src/Images/LampDer.png");
        ImageIcon lUL = new ImageIcon("src/Images/LampArrIzq.png");
        ImageIcon lUR = new ImageIcon("src/Images/LampArrDer.png");
        ImageIcon lDR = new ImageIcon("src/Images/LampAbaDer.png");
        ImageIcon lDL = new ImageIcon("src/Images/LampAbaIzq.png");
        
        
        sprite00= i0.getImage();
        sprite01 = i1.getImage();
        sprite02 = i2.getImage();
        sprite03 = i3.getImage();
        sprite04 = i4.getImage();
        WalkingRight01 =wR.getImage();
        WalkingFront01=wF1.getImage();
        WalkingFront02=wF2.getImage();
        WalkingBack01=wB1.getImage();
        WalkingBack02=wB2.getImage();
        WalkingLeft01 =wL.getImage();
        
        lampDer = lR.getImage();
        lampIzq = lL.getImage();
        lampArr = lU.getImage();
        lampAba = lD.getImage();
        lampArrDer = lUR.getImage();
        lampArrIzq = lUL.getImage();
        lampAbaDer = lDR.getImage();
        lampAbaIzq = lDL.getImage();
        
        actualSprite = sprite01;
        actualLamp= lampAba;
        spriteSizeX = actualSprite.getWidth(game);
        spriteSizeY= actualSprite.getHeight(game);
    }
    
    public void move(){
        game.checkCollision();
        if(canMove){
            positionX= positionX+ horzMove;
            if((positionX+(horzMove)<430||positionX+horzMove+128>860)||positionY+vertMove<230||positionY+vertMove+128>460){
              
            }
            if(horzMove!=0||vertMove!=0){
                isWalking=true;
            }
               
            else if(horzMove==0&&vertMove==0){
                isWalking=false; 
            }
           game.CamX+=horzMove;
            game.CamY+=vertMove;
            positionY=positionY+ vertMove;   
            anim();
        }
    
    }
    
    public void keyReleased(KeyEvent e){
        horzMove=0;
        vertMove=0;
        dirHorzLight=0;
        dirVertLight = 0;
        seg=-0.25;
        seg2=0.25;
    }

    public void keyPressed(KeyEvent e){
          
        if(e.getKeyCode()==KeyEvent.VK_A){
            horzMove=-speed; 
        }
        if(e.getKeyCode()==KeyEvent.VK_D){
            horzMove=speed;
        }
        if(e.getKeyCode()==KeyEvent.VK_W){
            vertMove=-speed;
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            vertMove=speed;
        }
        if(e.getKeyCode()==KeyEvent.VK_SHIFT&&pila&&canMove){
            lightOn=!lightOn;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            dirVertLight = 1;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            dirVertLight = -1;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            dirHorzLight = 1;
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT){
            dirHorzLight = -1;
        }
    }
    
    private void getDirection(){
        if(dirHorzLight==1){
            actualLamp=lampDer;
            facingRight=true;
            facingLeft=false;
            facingFront=false;
            facingBack=false;
            addX=80;
            addY=0;
            addX2=80;
            addY2=100;
            sizeX=115;
            sizeY=1;
        }
        if(dirHorzLight==-1){
            actualLamp=lampIzq;
            facingRight=false;
            facingLeft=true;
            facingFront=false;
            facingBack=false;
            addX= -100;
            addY=0;
            addX2= -80;
            addY2=100;
            sizeX=80;
            sizeY=1;
        }
        if(dirVertLight==1){
            actualLamp=lampAba;
            facingRight=false;
            facingLeft=false;
            facingFront=true;
            facingBack=false;
            addY = 114;
            addX=0;
            addY2 = 114;
            addX2=50;
            sizeX=1;
            sizeY=116;
        }
        if(dirVertLight==-1){
            actualLamp=lampArr;
            facingRight=false;
            facingLeft=false;
            facingFront=false;
            facingBack=true;
            addY = -114;
            addX=0;
            addY2 = -30;
            addX2=50;
            sizeX=1;
            sizeY=30;
        }
        if(dirVertLight==0&&dirHorzLight==0){
           // actualSprite = sprite00;
            
        }
        if(dirVertLight==1&& dirHorzLight==1){
            actualLamp= lampAbaDer;
            addX=100;
            addY=98;
            addX2=184;
            addY2=230;
            sizeX=1;
            sizeY=1;
        }
        if(dirVertLight==1&&dirHorzLight==-1){
            actualLamp= lampAbaIzq;
            addX=-100;
            addY=98;
            addX2=-80;
            addY2=230;
            sizeX=1;
            sizeY=1;
        }
        if(dirVertLight==-1&&dirHorzLight==1){
            actualLamp= lampArrDer;
            addX=80;
            addY=-90;
            addX2=184;
            addY2=-28;
            sizeX=1;
            sizeY=1;
        }
        if(dirVertLight==-1&&dirHorzLight==-1){
            actualLamp= lampArrIzq;
            addX=-100;
            addY=-98;
            addX2=-80;
            addY2=-28;
            sizeX=1;
            sizeY=1;
        }
    }
    
    public int getPositionX(){
        return positionX;
    }
    
    public int getPositionY(){
        return positionY;
    }
    
    public void gameOver(){
        positionX =inicialX;
        positionY = inicialY;
        lightOn = false;
        pila=true;
        canMove=true;
    }

    public void paint(Graphics2D g){
       getDirection();
      
       
        
       
        g.drawImage(actualSprite,positionX-game.CamX,positionY-game.CamY,null);
        g.setColor(Color.yellow);
        
        if(lightOn){
            g.drawImage(actualLamp, positionX+addX-game.CamX, positionY+addY-game.CamY+12, null);
        }
       
      
    }
    
 
    double seg=0;
    double seg2=0.25;
    public void anim(){
        if(facingRight){
            if(isWalking==false)
                actualSprite=sprite04;
            if(isWalking==true){
                
                if(seg<=0){
                    if(actualSprite==WalkingRight01)
                        actualSprite=sprite04;
                    else if(actualSprite==sprite04)
                        actualSprite=WalkingRight01;
                    
                    seg=0.25;
                    
                }
                seg-=0.02;
            }
        }
        if(facingLeft){
            
            if(isWalking==false)
                actualSprite=sprite02;
            if(isWalking==true){
                
                if(seg<=0){
                    actualSprite=WalkingLeft01;
                    seg=0.25;
                    
                }
                if(seg2<=0){
                       actualSprite=sprite02;
                       seg2=0.25;
                    }
                if(actualSprite!=WalkingLeft01);
                    seg-=0.02;
                if(actualSprite!=sprite02)
                    seg2-=0.02;
            }
        }
        if(facingBack){
            if(isWalking==false)
                actualSprite=sprite03;
            if(isWalking==true){
                
                if(seg<=0){
                    actualSprite=WalkingBack02;
                    seg=0.25;
                    
                }
                if(seg2<=0){
                       actualSprite=WalkingBack01;
                       seg2=0.25;
                    }
                if(actualSprite!=WalkingBack02);
                    seg-=0.02;
                if(actualSprite!=WalkingBack01)
                    seg2-=0.02;
            }
        }
        if(facingFront){
             if(isWalking==false)
                actualSprite=sprite01;
            if(isWalking==true){
                
                if(seg<=0){
                    actualSprite=WalkingFront02;
                    seg=0.25;
                    
                }
                if(seg2<=0){
                       actualSprite=WalkingFront01;
                       seg2=0.25;
                    }
                if(actualSprite!=WalkingFront02);
                    seg-=0.02;
                if(actualSprite!=WalkingFront01)
                    seg2-=0.02;
            }
        }
    }
    
    public Rectangle getBoundsLight() {
        return new Rectangle(positionX+addX2-game.CamX, positionY+addY2-game.CamY, sizeX, sizeY);
    }
    
    public Rectangle getBoundsPlayer() {
        return new Rectangle(positionX-game.CamX, positionY-game.CamY+10,80, 70);
    }
     public Rectangle getBoundsPlayerLight() {
        return new Rectangle(positionX-game.CamX+50, positionY-game.CamY+100,1,1);
    }
}

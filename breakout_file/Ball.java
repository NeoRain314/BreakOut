import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ball
 * 
 * @author (Ihr Name) 
 * @29.01.2026
 */

public class Ball extends Actor
{
    
    private double dx = 2;//2 - Greenfoot.getRandomNumber(3);
    private double dy = -2;//Greenfoot.getRandomNumber(2) - 3;
    private double speed = 3;
    private int width = this.getImage().getWidth();
    private int type = 0; //0:normal, 1:star(glidesThroughBlocks)
    private int sound_delay = 0;
    
    /*private GreenfootSound hit_paddle = new GreenfootSound("hit_paddle.wav");
    private GreenfootSound hit_block = new GreenfootSound("hit_block.wav");
    private GreenfootSound starhit_block = new GreenfootSound("starhit_block.wav");
    private GreenfootSound loseball = new GreenfootSound("loseball.wav");*/
    
    public Ball(int type){
    }
    
    /**
     * Act - tut, was auch immer Ball tun will. Diese Methode wird aufgerufen, 
     * sobald der 'Act' oder 'Run' Button in der Umgebung angeklickt werden. 
     */
    public void act() 
    {
        setLocation((int) (getX() + Math.round(dx)), (int) (getY() + Math.round(dy)));
        reflectOnPaddle();
        reflectOnBlock();
        reflectOnWall();
        removeOnWall();
        
        if(getWorld() == null) return; //damit act nicht weiter ausgeführt wird wenn ball unten verschwindet
        
        
        if(type==1 && ((Spielfeld)getWorld()).timer_time == 0){
            type = 0; ///////////////////////////////////////////vieleicht noch schöner machen ...
            this.setImage("ball.png");
        }
        if(sound_delay > 0){
            sound_delay --;
        }
        
    }

    
    private void reflectOnPaddle(){
        Actor paddle = getOneIntersectingObject(Paddle.class);
        if(paddle != null){
            SoundManager.playSound("hit_paddle.wav");
            
            int paddleLeft = paddle.getX() - paddle.getImage().getWidth()/2 + 5;
            int paddleRight = paddle.getX() + paddle.getImage().getWidth()/2 -5;
            if(getX() < paddleLeft && dx>0) {
                dx = -dx;
                setLocation(getX()-1, getY());
            }else if(getX() > paddleRight && dx<0){
                dx = -dx;
                setLocation(getX()+1, getY());
            }
            double a = paddle.getX()-getX();
            if(dx<0){
                dx = -1 - Math.sqrt(a*a)/10;
            }else{
                dx = 1 + Math.sqrt(a*a)/10;
            }
            
            if(dx > speed-1) dx = speed-1;
            if(dx < -speed+1) dx = -speed+1;
            
            double b = Math.pow(speed, 2) - Math.pow(dx, 2);
            dy = -Math.sqrt(b);
            
            //dy = -dy;
        }
    }
    
    
    
    private void reflectOnBlock(){
        Block block = (Block) getOneIntersectingObject(Block.class);
        if(block != null){
            int blockLeft = block.getX() - block.getImage().getWidth()/2 + 1;
            int blockRight = block.getX() + block.getImage().getWidth()/2 - 1;
            /*if(getX() < blockLeft) {
                dx = -dx;
            }else if(getX() > blockRight){
                dx = -dx;
            }else{
                dy = -dy;
            }*/
            
            if(type == 0){
                dy = -dy;
                SoundManager.playSound("hit_block.wav");
            }else{
                SoundManager.playSound("starhit_block.wav");
            }
            
            ((Spielfeld) getWorld()).score += 200;
            //0:nothing; 1:norm; 2:doubleHit; 3:threehit, 4:star, 5:ballspawn, 6:paddlesize//
            if(block.type == 4){
                /*block.type = 1;
                ((Spielfeld) getWorld()).balls++;*/
                type = 1;
                this.setImage("ball_star.png");
                ((Spielfeld)getWorld()).timer_time += 10*60;
                getWorld().removeObject(block);
            }else
            if(block.type == 5){
                Ball ball = new Ball(0);
                getWorld().addObject(ball, Greenfoot.getRandomNumber(600),Greenfoot.getRandomNumber(300));
                ((Spielfeld) getWorld()).balls++;
                getWorld().removeObject(block);
            }else
            if(block.type == 6){
                Paddle paddle = getWorld().getObjects(Paddle.class).get(0);
                if(paddle.stat == 1){
                    paddle.stat = 0;
                    paddle.setImage("paddle_long.png");
                }else
                if(paddle.stat == 0){
                    paddle.stat = 1;
                    paddle.setImage("paddle_short.png");
                }
                getWorld().removeObject(block);
            }else
            if(block.type > 1){
                block.type--;
            }else getWorld().removeObject(block);
        }
    }
    
    private void reflectOnWall(){
        if(getX() >= 600-width/2){
            dx = -dx;
            SoundManager.playSound("hit_paddle.wav");
        }
        
        if(getX() <= 8){
            dx = -dx;
            SoundManager.playSound("hit_paddle.wav");
            
        }
        if(getY() <= 8){
            dy = -dy;
            SoundManager.playSound("hit_paddle.wav");
        }
    }    

    private void removeOnWall(){
        if(getY() >= 350){
            SoundManager.playSound("loseball.wav");
            ((Spielfeld) getWorld()).balls -= 1;
            ((Spielfeld) getWorld()).score -= 200;
            ((Spielfeld)getWorld()).timer_time = 0; //falls timer läuft --> zurücksetzen
            getWorld().removeObject(this);
        }
        
        //counter von spielfeld wie viele bälle es noch gibt runterzählen!!!
    }
    
    /*private void sound(GreenfootSound sound_name){
        if(sound_delay == 0){
            sound_name.play();
            sound_delay = 5;
        }
    }*/
    
    /*old sound method:
     * private void sound(String sound_name){
        if(sound_delay == 0){
            Greenfoot.playSound(sound_name);
            sound_delay = 0;
        }
    }*/
}

    
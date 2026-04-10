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
    }

    private void reflectOnPaddle(){
        Actor paddle = getOneIntersectingObject(Paddle.class);
        if(paddle != null){
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
            
            dy = -dy;
            
            ((Spielfeld) getWorld()).score += 200;
            if(block.type == 4){
                block.type = 1;
                ((Spielfeld) getWorld()).balls++;
            }else
            if(block.type == 5){
                Ball ball = new Ball(0);
                getWorld().addObject(ball, Greenfoot.getRandomNumber(600),Greenfoot.getRandomNumber(300));
                ((Spielfeld) getWorld()).balls++;
                getWorld().removeObject(block);
            }
            if(block.type > 1){
                block.type--;
            }else getWorld().removeObject(block);
        }
    }
    
    private void reflectOnWall(){
        if(getX() >= 600-width/2) dx = -dx;
        if(getX() <= 8) dx = -dx;
        if(getY() <= 8) dy = -dy;
    }    

    private void removeOnWall(){
        if(getY() >= 350){
            ((Spielfeld) getWorld()).balls -= 1;
            ((Spielfeld) getWorld()).score -= 200;
            getWorld().removeObject(this);
        }
        
        //counter von spielfeld wie viele bälle es noch gibt runterzählen!!!
    }
}
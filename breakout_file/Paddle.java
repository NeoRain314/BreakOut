import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Paddle
 * 
 * @author (Ihr Name) 
 * @29.01.2026
 */
public class Paddle extends Actor
{
    private float move_speed_r = 1;
    private float move_speed_l = 1;
    int width = this.getImage().getWidth();
    
    public void act() 
    {
        // old: if(Greenfoot.isKeyDown("D") && getX()<600-width/2) setLocation(getX() + move_speed, getY());
        // old: if(Greenfoot.isKeyDown("A") && getX()>width/2) setLocation(getX() - move_speed, getY());
        
        if((Greenfoot.isKeyDown("D")||Greenfoot.isKeyDown("right")) && getX()<600-width/2){
            setLocation(getX() + Math.round(move_speed_r), getY());
            if(move_speed_r<4) move_speed_r += 0.1;
        }else move_speed_r = 1;
        
        
        if((Greenfoot.isKeyDown("A")||Greenfoot.isKeyDown("left")) && getX()>width/2){
            setLocation(getX() - Math.round(move_speed_l), getY());
            if(move_speed_l<4) move_speed_l += 0.1;
        }else move_speed_l = 1;
    }    
}

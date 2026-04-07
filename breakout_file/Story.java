import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story extends World
{

    /**
     * Constructor for objects of class Story.
     * 
     */
    
    private boolean key_down = false;
    private int stat = 0;
    
    public Story()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        showText("Press space to start!", 110, 375);
    }
    
    public void act(){
        
       if(Greenfoot.isKeyDown("space")) key_down = true; //damit erst nach loslassen, sonst in menü gleich wieder space gedrückt --> startet neues game
        if(key_down && !Greenfoot.isKeyDown("space")){
            if(stat<3){
                stat++;
                showText("", 110, 375);
                showText("Press space to continue!", 120, 385);
                if(stat>1) showText("", 120, 385); //GEHT NOCH NICHT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                this.setBackground("Story_" + stat + ".png");
            } else {
                Greenfoot.setWorld(new Menu(0));
            }
            key_down = false;
        }
    }
}

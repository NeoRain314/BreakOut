import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story extends World{
    public static Story story;
    
    private boolean key_down = false;
    private int stat = 0;
    public int story_stat = 0;
    
    public Story()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        story = this;
        showText("Press space to start!", 110, 375);
        Greenfoot.setSpeed(50);
    }
    
    public void act(){
        if(stat==7) SoundManager.playMusic("menu_music");
        if(Greenfoot.isKeyDown("space")) key_down = true; //damit erst nach loslassen, sonst in menü gleich wieder space gedrückt --> startet neues game
        if(key_down && !Greenfoot.isKeyDown("space")){
            if(story_stat == 0){
                if(stat<6){
                    stat++;
                    showText("", 110, 375);
                    showText("Press space to continue!", 120, 385);
                    if(stat>1) showText("", 120, 385);
                    if(stat<2) SoundManager.playMusic("menu_music"); 
                    this.setBackground("Story_" + stat + ".png");
                } else {
                    story_stat = 1;
                    stat++;
                    Greenfoot.setWorld(new Menu(0));
                    
                    this.setBackground("Story_" + stat + ".png");
                    showText("Press space to continue!", 120, 385);
                }
            }else{
                if(stat<8){
                    stat++;
                    if(stat>7){
                        showText("", 120, 385);
                        showText("Congrats! Press space to continue playing :D", 215, 385);
                    }
                    this.setBackground("Story_" + stat + ".png");
                } else {
                    Greenfoot.setWorld(Menu.menu);
                    story_stat = 2;
                }
            }
            key_down = false;
        }
            
    }
}

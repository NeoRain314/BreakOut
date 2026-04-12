import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Menu.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Menu extends World {

    public static Menu menu;
    
    public int level = 0;
    public static int total_score = 0;
    public static int maxLevel = 4;
    //Levels: test_level, Level_1, Level_2
    public static int max_unlocked_level = 1;

    private boolean right_pressed = false;
    private boolean left_pressed = false;
    private boolean space_pressed = false;
    
    int test = 0;
    
    public Menu(int achieved_level)
    {    
        // Erstellt eine neue Welt mit 600x400 Zellen und einer Zell-Größe von 1x1 Pixeln.
        super(600, 400, 1);
        menu = this;
        //showText("BREAKOUT", 300, 100);
        showText("Use arrows to switch Level", 300, 300);
        showText("Press space to start!", 300, 330);
        
        showText("Level:", 300, 185);
        showText("<               >", 300, 211);
        
        SoundManager.playMusic("menu_music");
        
    }
    
    public void act(){
        
        if(Greenfoot.isKeyDown("right")){
            if(!right_pressed){
                SoundManager.playSound("hit_paddle.wav");
                level++;
                if(level>maxLevel) level = 1;
            }
            right_pressed = true;
        } else{
            right_pressed = false;
        }
        if(Greenfoot.isKeyDown("left")){
            if(!left_pressed){
                SoundManager.playSound("hit_paddle.wav");
                level--;
                if(level < 1) level = maxLevel;
            }
            left_pressed = true;
        } else{
            left_pressed = false;
        }
        
        if(Greenfoot.isKeyDown("space")){
            if(!space_pressed){
                SoundManager.playSound("select.wav");
                
                if(level <= max_unlocked_level){
                    SoundManager.stopMusic();
                    Greenfoot.setWorld(new Spielfeld(level));
                }
                
                
            }
            space_pressed = true;
        } else {
            space_pressed = false;
        }
        
        if(Greenfoot.isKeyDown("u")){
            max_unlocked_level = maxLevel;
        }

        update();
    }
    
    public void update(){
        showText("" + level, 300, 210);
        if(level > max_unlocked_level) showText("🔒" + level + "🔒", 300, 210);
        showText("SCORE: " + total_score, 300, 100);
    }
    
    /*old sound method
     * private void sound(String sound_name){
        if(sound_delay == 0){
            //Greenfoot.playSound(sound_name);
            GreenfootSound sound = new GreenfootSound(sound_name);
            sound.play();
            sound_delay = 5;
        }
    }*/
}
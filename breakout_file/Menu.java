import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Menu.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Menu extends World
{

    /**
     * Konstruktor für Objekte der Klasse Menu
     * 
     */
    
    public int level = 0;
    private int maxLevel;
    //Levels: test_level, Level_1, Level_2
    private int[] LevelLocks = { //0=locked, 1=unlocked
        1,
        1,
        0,
        0,
        0
    };
    private int sound_delay = 0;
    private boolean right_pressed = false;
    private boolean left_pressed = false;
    private boolean space_pressed = false;
    
    int test = 0;
    
    public Menu(int unlock)
    {    
        // Erstellt eine neue Welt mit 600x400 Zellen und einer Zell-Größe von 1x1 Pixeln.
        super(600, 400, 1);
        showText("BREAKOUT", 300, 100);
        showText("Use arrows to switch Level", 300, 300);
        showText("Press space to start!", 300, 330);
        
        showText("Level:", 300, 185);
        showText("<               >", 300, 211);
        
        maxLevel = LevelLocks.length - 1;
        if(unlock>0 && unlock<maxLevel){            //....................................................... vieleicht noch schöner schreiben .....//
            for(int i = 0; i<=(unlock+1); i++){
                 LevelLocks[i] = 1;
            }
        }else{
            for(int i = 0; i<=(unlock); i++){
                 LevelLocks[i] = 1;
            }
        }
        
        Greenfoot.setSpeed(50);
    }
    
    public void act(){
        if(sound_delay > 0) sound_delay--;
        
        if(Greenfoot.isKeyDown("right")){
            if(!right_pressed){
                sound("hit_paddle.wav");
                level++;
                if(level>maxLevel) level = 1;
            }
            right_pressed = true;
        } else{
            right_pressed = false;
        }
        if(Greenfoot.isKeyDown("left")){
            if(!left_pressed){
                sound("hit_paddle.wav");
                level--;
                if(level < 1) level = maxLevel;
            }
            left_pressed = true;
        } else{
            left_pressed = false;
        }
        
        if(Greenfoot.isKeyDown("space")){
            if(!space_pressed){
                sound("select.wav");
                
                if(LevelLocks[level]==1){
                    int u = 0; //max level that is already unlocked
                    for(int i = 0; i<LevelLocks.length-1; i++){
                     if(LevelLocks[i+1] == 0 || i==LevelLocks.length-2){
                         u = i;
                         break;
                     }
                    }
                    Greenfoot.setWorld(new Spielfeld(level, u));
                }
                
                
            }
            space_pressed = true;
        } else {
            space_pressed = false;
        }
        
        if(Greenfoot.isKeyDown("u")){
            for(int i = 0; i<LevelLocks.length; i++){
                 LevelLocks[i] = 1;
            }
        }

        update();
    }
    
    public void update(){
        showText("" + level, 300, 210);
        if(LevelLocks[level]==0) showText("🔒" + level + "🔒", 300, 210);
        
    }
    
    private void sound(String sound_name){
        if(sound_delay == 0){
            //Greenfoot.playSound(sound_name);
            GreenfootSound sound = new GreenfootSound(sound_name);
            sound.play();
            sound_delay = 5;
        }
    }
}
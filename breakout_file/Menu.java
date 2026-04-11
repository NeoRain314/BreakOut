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
        1,
        1,
        1
    };
    
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
    }
    
    public void act(){
        if(Greenfoot.isKeyDown("right")){
            while(Greenfoot.isKeyDown("right"));
            level++;
            if(level>maxLevel) level = 1;
        }
        if(Greenfoot.isKeyDown("left")){
            while(Greenfoot.isKeyDown("left"));
            level--;
            if(level<1) level = maxLevel;
        }
        
        if(Greenfoot.isKeyDown("space")){
            if(LevelLocks[level]==1)Greenfoot.setWorld(new Spielfeld(level));
        }

        update();
    }
    
    public void update(){
        showText("" + level, 300, 210);
        if(LevelLocks[level]==0) showText("🔒" + level + "🔒", 300, 210);
        
    }
}
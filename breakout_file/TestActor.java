import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse TestActor.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class TestActor extends Actor
{
    /**
     * Act - tut, was auch immer TestActor tun will. Diese Methode wird aufgerufen, 
     * sobald der 'Act' oder 'Run' Button in der Umgebung angeklickt werden. 
     */
    public void act() 
    {
        if(Greenfoot.mouseClicked(this)){
            GreenfootImage img = getImage();

greenfoot.Color newColor = new greenfoot.Color(
    Greenfoot.getRandomNumber(256),
    Greenfoot.getRandomNumber(256),
    Greenfoot.getRandomNumber(256)
);

for(int x = 0; x < img.getWidth(); x++)
{
    for(int y = 0; y < img.getHeight(); y++)
    {
        if(img.getColorAt(x,y).getAlpha() > 0)
        {
            img.setColorAt(x,y,newColor);
        }
    }
}    setImage(img);
        }
        
    }    
}
import greenfoot.*;

//Soundmanager
public class SoundManager  
{
    // instance variables - replace the example below with your own
    private static GreenfootSound game_music = new GreenfootSound("game_music.mp3");
    private static GreenfootSound menu_music = new GreenfootSound("menu_music.mp3");
    private static boolean is_music_playing = false;
    private static int sound_delay = 0;

    public SoundManager(){
        // :D
    }

    public void act(){
        if(sound_delay > 0) sound_delay--;
    }
    
    public static void playMusic(String song){
        if(!is_music_playing){
            //if(song == "game_music") game_music.playLoop();
            //if(song == "menu_music") menu_music.playLoop();
            is_music_playing = true;
        }
    }
    
    public static void stopMusic(){
        if(menu_music.isPlaying()) menu_music.stop();
        if(game_music.isPlaying()) game_music.stop();
        is_music_playing = false;
    }
    
    public static void playSound(String sound_name){
        if(sound_delay == 0){
            Greenfoot.playSound(sound_name);
            sound_delay = 0;
        }
    }
}

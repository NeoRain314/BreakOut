import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Spielfeld
 * 
 * @author (Ihr Name) 
 * @29.01.2026
 */
public class Spielfeld extends World
{

    /**
     * Konstruktor für Objekte der Klasse Spielfeld
     * 
     */
    
    //LEVELS: (0:nothing; 1:norm; 2:doubleHit; 3:threehit, 4:star, 5:ballspawn, 6:paddlesize)
    private int[] test_level = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                                1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1, 1,
                                1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1,
                                1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1,
                                1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1,
                                1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1,
                                1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1,
                                1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1}; 
    
    private int[] Level_1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,}; 
    private int[] Level_2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,};
    private int[] Level_3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,};
    private int[] Level_4 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                             0, 1, 5, 1, 0, 1, 1, 4, 4, 1, 1, 0, 1, 5, 1, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                             0, 0, 1, 0, 0, 0, 1, 2, 2, 1, 0, 0, 0, 1, 0, 0,};
    private int[][] Level_list = {
        test_level,
        Level_1,
        Level_2,
        Level_3,
        Level_4
    };
    
    
    private int[] selectedLevel;
    private int level_num;
    
    public boolean show_timer = false;
    public int timer_time = 0;
    public int already_unlocked_level = 0;
    
    private GreenfootSound music = new GreenfootSound("background_music.mp3");
    
    public Spielfeld(int level, int unlocked_level)
    {    
        // Erstellt eine neue Welt mit 600x400 Zellen und einer Zell-Größe von 1x1 Pixeln.
        super(600, 400, 1);
        selectedLevel = Level_list[level];
        level_num = level;
        fillGame();
        test();
        game_stat = 0;
        already_unlocked_level = unlocked_level;
        if(!music.isPlaying()) music.playLoop();
        //prepare();
    }

    public int game_width = getWidth();
    public int game_height = getHeight();
    public int balls = 3;
    public int score = 0;
    public int game_stat = 0; //0-> is running, 1-> lost, 2->won  
    private boolean key_down = false; 
    private int sound_delay = 0;
    
    public Paddle paddle;
    
    
    public void act() {
        if(game_stat==0 && Greenfoot.isKeyDown("space") && getObjects(Ball.class).size() == 0) {
            Ball ball = new Ball(0);
            addObject(ball, paddle.getX(), paddle.getY()-20);
        }
        if(game_stat > 0){
            //removeObjects(getObjects(Actor.class));
            if(Greenfoot.isKeyDown("space")) key_down = true; //damit erst nach loslassen, sonst in menü gleich wieder space gedrückt --> startet neues game
            if(key_down && !Greenfoot.isKeyDown("space")){
                music.stop();
                key_down = false;
                if(game_stat == 1) Greenfoot.setWorld(new Menu(already_unlocked_level));
                if(game_stat == 2 && already_unlocked_level < level_num){
                    Greenfoot.setWorld(new Menu(level_num));
                }else{
                    Greenfoot.setWorld(new Menu(already_unlocked_level));
                }
    
            }
        }
        
        if(timer_time > 0){
            timer_time--;
            show_timer = true;
        }else{
            show_timer = false;
        }
        
        if(sound_delay > 0) sound_delay--;
        
        updateText();
        
    }
    
    private void fillGame() {
        //blocks:
        int temp_pos = 0;
        int columns = 16;
        int rows = selectedLevel.length/columns;
        for(int i=0; i<rows; i++){
            for(int j=0; j<columns; j++){
                if(selectedLevel[temp_pos]!=0){
                    Block block = new Block(selectedLevel[temp_pos]);
                    addObject(block, 30+j*36,40+i*26);
                }
                temp_pos++;
            }
        }
        //paddle:
        /* Test block:
        Block block = new Block();
        addObject(block, 100,50);*/
        
        paddle = new Paddle();
        addObject(paddle, 300, 320);
    }
    
    private void test(){
        updateText();
    }
    
    private void updateText() {
        //Blocks:
        int maxBlocks = 48;
        int currBlocks = getObjects(Block.class).size();
        //showText("Blocks:" + currBlocks + "/" + maxBlocks, 70, 200);
        
        //Balls:
        showText("Balls left:" + balls, 440, 380);
        
        //Score:
        showText("Score:" + score, 70, 380);
        
        //Level:
        showText("Level:" + level_num, game_width-50, 380);
        
        if(currBlocks == 0 && game_stat == 0){
            SoundManager.playSound("win.wav");
            showText("YOU WON!", game_width/2, game_height/2-50);
            showText(":)", game_width/2, game_height/2-30);
            showText("You unlocked the next level!", game_width/2, game_height/2);
            //showText("- press space to continue -", game_width/2, game_height-65);
            game_stat = 2;
            stopGame();
        }
        if(balls == 0 && game_stat == 0){
            SoundManager.playSound("loose.wav");
            showText("YOU LOST!", game_width/2, game_height/2-50);
            showText(":(", game_width/2, game_height/2-30);
            showText("ty again", game_width/2, game_height/2);
            game_stat = 1;
            stopGame();
        }
        if(show_timer){
            showText("Timer:" + timer_time/60, game_width/2, 340);
        }else{
            showText("", game_width/2, 340);
        }
    }
    
    private void stopGame(){
        removeObjects(getObjects(null));
        
        showText("- press space to continue -", game_width/2, game_height-65);
        timer_time = 0;
        showText("", game_width/2, 340);
        /*showText("", 80, 280);
        showText("", 68, 250);*/
    }
    
    /* old method to play sound:
     * private void sound(String sound_name){
        if(sound_delay == 0){
            Greenfoot.playSound(sound_name);
            sound_delay = 2;
        }
    }*/
}
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
    
    //LEVELS: (0:nothing; 1:norm.block; 2:doubleHit.block)
    private int[] test_level = {0, 1, 2, 3, 4, 5, 0, 1, 1, 0, 0, 0, 0, 5, 0, 0,
                                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                                5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,}; 
    private int[] Level_1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                             0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,}; 
    private int[] Level_2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                             5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
                             1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,};
    private int[][] Level_list = {
        test_level,
        Level_1,
        Level_2
    };
    
    
    private int[] selectedLevel;
    private int level_num;
    
    public Spielfeld(int level)
    {    
        // Erstellt eine neue Welt mit 600x400 Zellen und einer Zell-Größe von 1x1 Pixeln.
        super(600, 400, 1);
        selectedLevel = Level_list[level];
        level_num = level;
        fillGame();
        test();
        game_stat = 0;
        //prepare();
    }

    public int game_width = getWidth();
    public int game_height = getHeight();
    public int balls = 3;
    public int score = 0;
    public int game_stat = 0; //0-> is running, 1-> lost, 2->won  
    private boolean key_down = false; 
    
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
                if(game_stat == 1) Greenfoot.setWorld(new Menu(0));
                if(game_stat == 2) Greenfoot.setWorld(new Menu(level_num));
                key_down = false;
            }
        }
        
        updateText();
    }
    
    private void fillGame() {
        //blocks:
        int temp_pos = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<16; j++){
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
            showText("YOU WON!", game_width/2, game_height/2-50);
            showText(":)", game_width/2, game_height/2-30);
            showText("You unlocked the next level!", game_width/2, game_height/2);
            showText("- press space to continue -", game_width/2, game_height-65);
            game_stat = 2;
            stopGame();
        }
        if(balls == 0 && game_stat == 0){
            showText("YOU LOST!", game_width/2, game_height/2-50);
            showText(":(", game_width/2, game_height/2-30);
            showText("ty again", game_width/2, game_height/2);
            showText("- press space to continue -", game_width/2, game_height-65);
            game_stat = 1;
            stopGame();
        }
    }
    
    private void stopGame(){
        removeObjects(getObjects(null));
        /*showText("", 80, 280);
        showText("", 68, 250);*/
    }
}
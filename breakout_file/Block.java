import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Block
 * 
 * @author (Ihr Name) 
 * @29.01.2026
 */

public class Block extends Actor
{
    public int type;
    public Block(int spawn_type){
        type = spawn_type;
    }
    
    public void act(){
        if(type == 1) this.setImage("block_2.png");
        if(type == 2) this.setImage("twohit_block.png");
        if(type == 3) this.setImage("threehit_block.png");
        if(type == 4) this.setImage("star_block.png");
        if(type == 5) this.setImage("special_block.png");
    }
}
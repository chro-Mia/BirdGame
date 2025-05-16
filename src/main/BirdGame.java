package main;

import gameobjects.Bird;
import gameobjects.GameObject;
import gameobjects.ScrollingObject;
import processing.core.PApplet;

import java.util.ArrayList;

public class BirdGame extends PApplet{

    ScrollingObject background;
    Bird bird;
    Integer totalFrame = 0;

    ArrayList<ArrayList<GameObject>> layers;
    final int NUM_LAYERS = 8;
    boolean firstInputFlag = false;
    boolean gameActive = true;
    boolean isJumpBeingHeld = false;

    public void settings(){ size(650, 650); }

    public void setup(){
        background = new ScrollingObject(this, loadImage("assets/background.png"), -1);
        bird = new Bird(this);
        layers = new ArrayList<>();
        for(int i = 0; i < NUM_LAYERS; i++){
            layers.add(new ArrayList<>());
        }

        layers.get(1).add(bird);
    }

    public void keyPressed(){
        if(keyPressed && (key == ' ') && !isJumpBeingHeld){
            firstInputFlag = true;
            bird.jump();
            isJumpBeingHeld = true;
            bird.frame2();
        }
    }

    public void keyReleased(){
        if(key == ' '){
            isJumpBeingHeld = false;
            bird.frame1();
        }
    }

    public void draw(){
        totalFrame++;
        if(gameActive){
            background.scroll();
            for(int i = 0; i < layers.size(); i++){
                for(GameObject object : layers.get(i)){
                    if(bird.detectCollision(object) && object != bird){
                        gameActive = false;
                    }
                    if(object instanceof Bird && firstInputFlag){
                        ((Bird) object).gravity();
                    }
                    if(firstInputFlag){
                        object.move();
                    }
                    object.show();
                }
            }
        }
    }
}

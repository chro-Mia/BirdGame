package main;

import gameobjects.Bird;
import gameobjects.GameObject;
import gameobjects.ScrollingObject;
import processing.core.PApplet;
import processing.core.PImage;

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
    PImage topPipe;
    PImage bottomPipe;

    public void settings(){ size(650, 650); }

    public void setup(){
        background = new ScrollingObject(this, loadImage("assets/background.png"), -1);
        bird = new Bird(this);
        layers = new ArrayList<>();
        for(int i = 0; i < NUM_LAYERS; i++){
            layers.add(new ArrayList<>());
        }

        topPipe = loadImage("assets/toppipe.png");
        bottomPipe = loadImage("assets/bottompipe.png");

        layers.get(2).add(bird);
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

            if(totalFrame % 180 == 0){
                int pipeOffset = (int) (Math.random() * 200) + 350;

                GameObject firstPipe = new GameObject(this, 750, pipeOffset, bottomPipe, 100, 350, true, true);
                layers.get(1).add(firstPipe);
                layers.get(1).add(new GameObject(this, 750, firstPipe.getY() - 500, topPipe, 100, 350, true, true));
            }

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
                    if(object.getX() < -650){
                        object.markGarbage();
                    }
                    object.show();
                }
            }

            //??? we should remove layers cuz its confusing and bad and honestly pretty useless
            for(int i = layers.get(1).size() - 1; i >= 0; i--){
                if(layers.get(1).get(i).isGarbage()){
                    layers.get(1).remove(layers.get(1).get(i));
                }
            }
        }
    }
}

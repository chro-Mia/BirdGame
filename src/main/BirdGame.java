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
    GameObject title;
    GameObject death;
    Integer totalFrame = 0;
    Integer gameTimeFrame = 0;
    float gameSpeedMultiplier = -1f;
    Integer score = 0;

    ArrayList<GameObject> pipes;
    boolean firstInputFlag = false;
    boolean isPlayerAlive = true;
    boolean isJumpBeingHeld = false;
    PImage topPipe;
    PImage bottomPipe;

    public void settings(){ size(650, 650); }

    public void setup(){
        background = new ScrollingObject(this, loadImage("assets/background.png"), -1);
        bird = new Bird(this);
        topPipe = loadImage("assets/toppipe.png");
        bottomPipe = loadImage("assets/bottompipe.png");
        pipes = new ArrayList<>();
        title = new GameObject(this, 100, 100, loadImage("assets/title.png"));
        death = new GameObject(this, 100, 100, loadImage("assets/youlose.png"));
    }

    public void keyPressed(){
        if(keyPressed && (key == ' ') && !isJumpBeingHeld && isPlayerAlive){
            firstInputFlag = true;
            bird.jump();
            isJumpBeingHeld = true;
            bird.frame2();
        }

        //restart after death
        if(keyPressed && (key == ' ') && !isJumpBeingHeld && !isPlayerAlive){
            setup();
            isPlayerAlive = true;
            gameTimeFrame = 0;
            gameSpeedMultiplier = -1f;
            bird.jump();
        }
    }

    public void keyReleased(){
        if(key == ' '){
            isJumpBeingHeld = false;
            bird.frame1();
        }
    }

    public void draw(){

        //GameObject might also need some tweaks cuz its kinda a jumbled mess rn
        //if statements my beloved

        totalFrame++;
        if(isPlayerAlive) {

            //actual game time
            if (firstInputFlag) {
                gameTimeFrame++;
            }

            //difficulty ramp logic
            if (gameTimeFrame % 1980 == 0 && firstInputFlag && gameSpeedMultiplier >= -6.0f) {
                gameSpeedMultiplier--;
            }

            //pipe spawning logic
            if (gameTimeFrame % 180 == 0 && firstInputFlag && gameTimeFrame % 1980 != 0) {
                int pipeOffset = (int) (Math.random() * 200) + 350;
                GameObject firstPipe = new GameObject(this, 750, pipeOffset, bottomPipe, 100, 350, 2 * gameSpeedMultiplier, 0, true, true);
                GameObject secondPipe = new GameObject(this, 750, firstPipe.getY() - 525, topPipe, 100, 350, 2 * gameSpeedMultiplier, 0, true, true);
                pipes.add(firstPipe);
                pipes.add(secondPipe);
            }


            //background logic
            background.move();

        }

        background.show();

        //pipe logic loop
        for (GameObject object : pipes) {
            if (bird.detectCollision(object) && object != bird && isPlayerAlive) {
                isPlayerAlive = false;
                bird.setVy(-8);
                bird.setVx(0);
            }
            if (firstInputFlag && isPlayerAlive) {
                object.move();
            }
            if (object.getX() < -650) {
                object.markGarbage();
            }
            object.show();
        }

        //player logic
        if(firstInputFlag){
            bird.gravity();
        }
        bird.move();
        bird.show();

        text(score, 0, 10);

        if(!firstInputFlag){
            title.show();
        }

        if(!isPlayerAlive){
            death.show();
        }

        //garbage collection
        for(int i = pipes.size() - 1; i >= 0; i--){
            if(pipes.get(i).isGarbage()){
                pipes.remove(i);
            }
        }
    }
}
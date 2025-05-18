package main;

import gameobjects.Bird;
import gameobjects.GameObject;
import gameobjects.ScrollingObject;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;

public class BirdGame extends PApplet{

    boolean firstInputFlag = false;
    boolean isPlayerAlive = true;
    boolean isJumpBeingHeld = false;
    boolean isSpawningPaused = false;

    ArrayList<GameObject> pipes;

    ScrollingObject background;
    Bird bird;
    GameObject title;
    GameObject death;

    PImage topPipe;
    PImage bottomPipe;

    int gameTimeFrame = 0;
    float gameSpeedMultiplier = -1f;
    Integer score = 0;
    int spawnInterval = 180;
    int speedTheGameUpOnThisFrame = -1;

    public void settings(){ size(650, 650); }

    public void setup(){
        background = new ScrollingObject(this, loadImage("assets/background.png"), -1);
        bird = new Bird(this);
        topPipe = loadImage("assets/toppipe.png");
        bottomPipe = loadImage("assets/bottompipe.png");
        pipes = new ArrayList<>();
        title = new GameObject(this, 100, 150, loadImage("assets/title.png"));
        death = new GameObject(this, 100, 150, loadImage("assets/youlose.png"));
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
            spawnInterval = 180;
            bird.jump();
            score = 0;
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

        if(isPlayerAlive) {

            //actual game time
            if (firstInputFlag) {
                gameTimeFrame++;
            }

            if (gameTimeFrame % spawnInterval == 0 && firstInputFlag && !isSpawningPaused) {
                int pipeOffset = (int) (Math.random() * 250) + 300;
                GameObject firstPipe = new GameObject(this, 650, pipeOffset, bottomPipe, 100, 600, 2 * gameSpeedMultiplier, 0, true, true);
                GameObject secondPipe = new GameObject(this, 650, firstPipe.getY() - 775, topPipe, 100, 600, 2 * gameSpeedMultiplier, 0, true, true);
                firstPipe.score();
                pipes.add(firstPipe);
                pipes.add(secondPipe);
            }

            if(pipes.size() >= 20 && gameSpeedMultiplier > -4){
                isSpawningPaused = true;
                speedTheGameUpOnThisFrame = gameTimeFrame + 450;
            }

            if(gameTimeFrame == speedTheGameUpOnThisFrame){
                gameSpeedMultiplier--;
                background.setVx(background.getVx() - 1);
                spawnInterval = (int)(spawnInterval * 0.7);
                isSpawningPaused = false;
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
            if(object.getX() + 100 < bird.getX() && !object.hasBeenScored()){
                object.score();
                score++;
            }
            if (firstInputFlag && isPlayerAlive){
                object.move();
            }
            if(object.getX() <= -100 && (isSpawningPaused || score > 40)){
                object.markGarbage();
            }
            object.show();
        }


        //player logic
        if(firstInputFlag){
            bird.gravity();
        }
        if(bird.getY() < 0 || bird.getY() + 50 > 650){
            isPlayerAlive = false;
        }
        bird.move();
        bird.show();

        if(firstInputFlag){
            if(score >= 0 && score < 10){
                textSize(100);
                text(score, 300, 100);
            }
            else if(score >= 10 && score < 100){
                textSize(100);
                text(score, 275, 100);
            }
        }

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
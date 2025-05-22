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
    Integer highScore = 0;
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
            isSpawningPaused = false;
            gameTimeFrame = 0;
            gameSpeedMultiplier = -1f;
            spawnInterval = 180;
            bird.jump();
            score = 0;
        }
    }

    //the bird actually keeps its wings flapped if you hold down the spacebar
    public void keyReleased(){
        if(key == ' '){
            isJumpBeingHeld = false;
            bird.frame1();
        }
    }

    public void draw(){

        if(isPlayerAlive) {
            if(highScore < score){
                highScore = score;
            }

            //counts the number of frames since the first input, acts as a timer for some game events
            if (firstInputFlag) {
                gameTimeFrame++;
            }

            //pipe spawning logic, every spawnInterval number of frames a pipe pair is spawned with a certain x velocity
            //according to the current difficulty
            if (gameTimeFrame % spawnInterval == 0 && firstInputFlag && !isSpawningPaused) {
                int pipeOffset = (int) (Math.random() * 250) + 300;
                GameObject firstPipe = new GameObject(this, 650, pipeOffset, bottomPipe, 100, 600, 2 * gameSpeedMultiplier, 0, true, true);
                GameObject secondPipe = new GameObject(this, 650, firstPipe.getY() - 775, topPipe, 100, 600, 2 * gameSpeedMultiplier, 0, true, true);
                firstPipe.score();
                pipes.add(firstPipe);
                pipes.add(secondPipe);
            }

            //when the number of pipe pair reaches 10, the game will stop spawning them, and schedule a difficulty
            //increase some number of frames in the future
            if(pipes.size() >= 20 && gameSpeedMultiplier > -4){
                isSpawningPaused = true;
                speedTheGameUpOnThisFrame = (int)(gameTimeFrame + 450 / -gameSpeedMultiplier);
            }

            //when the game time reaches the scheduled frame, the game will increase the speed of pipes, the background
            //and also make pipes spawn more frequently
            //in-game it's hard to tell that the pipes spawn faster, since they are visually more spread out
            //this also unpauses pipe spawning
            if(gameTimeFrame == speedTheGameUpOnThisFrame){
                gameSpeedMultiplier--;
                background.setVx(background.getVx() - 1);
                spawnInterval = (int)(spawnInterval * 0.65);
                isSpawningPaused = false;
            }

            //background logic
            background.move();

        }

        background.show();

        //pipe logic loop
        //check collisions
        //add score
        //move and display objects
        //mark objects as garbage to be removed from the list if pipe spawning is paused, or the score is larger than 40
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


        //this keeps the bird in place before any inputs have been registered
        if(firstInputFlag){
            bird.gravity();
        }
        //this kills the player if they go out of bounds
        if(bird.getY() < 0 || bird.getY() + 50 > 650){
            isPlayerAlive = false;
        }

        //after the first input, the game displays the current run's score, and the highest recorded score
        if(firstInputFlag){
            textSize(30);
            text("High-Score: " + highScore, 0, 30);
            textSize(100);

            //processing does not have text aligning unfortunately, so the game will do some hacky alignment to keep
            //the digits of the score centered whenever the score gets a new digit
            if(score >= 0 && score < 10){
                text(score, 300, 100);
            }
            else if(score >= 10 && score < 100){
                text(score, 275, 100);
            }
            else if(score >= 100 && score < 1000){
                text(score, 250, 100);
            }
        }

        if(!firstInputFlag){
            title.show();
        }

        //if the player has died, we show the end game screen and switch the bird sprite to the dead one
        if(!isPlayerAlive){
            death.show();
            bird.frame3();
        }

        bird.move();
        bird.show();

        //garbage collection
        for(int i = pipes.size() - 1; i >= 0; i--){
            if(pipes.get(i).isGarbage()){
                pipes.remove(i);
            }
        }
    }
}
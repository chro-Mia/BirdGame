import processing.core.PApplet;

import java.util.ArrayList;

public class BirdGame extends PApplet{

    GameObject background;
    Bird bird;
    GameObject pipe;
    ArrayList<ArrayList<GameObject>> layers;
    final int NUM_LAYERS = 8;
    boolean firstInputFlag = false;
    boolean gameActive = true;

    public void settings(){ size(650, 650); }

    public void setup(){
        background = new GameObject(this, 0, 0, loadImage("assets/background.jpg"), 0);
        bird = new Bird(this, loadImage("assets/bird.png"));
        pipe = new GameObject(this, 600, 400, loadImage("assets/pipe.png"), 1, 100, 300, true, true);
        layers = new ArrayList<>();
        for(int i = 0; i < NUM_LAYERS; i++){
            layers.add(new ArrayList<>());
        }

        layers.get(0).add(background);
        layers.get(1).add(pipe);
        layers.get(1).add(bird);
        pipe.vx = -2;
    }

    public void keyPressed(){
        if(keyPressed && (key == ' ')){
            firstInputFlag = true;
            bird.jump();
        }
    }

    public void draw(){
        if(gameActive){
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

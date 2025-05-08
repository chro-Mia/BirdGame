import processing.core.PApplet;

import java.util.ArrayList;

public class BirdGame extends PApplet{

    GameObject background;
    GameObject bird;
    ArrayList<ArrayList<GameObject>> layers;
    final int NUM_LAYERS = 8;

    public void settings(){ size(650, 650); }

    public void setup(){
        background = new GameObject(this, 0, 0, loadImage("assets/background.jpg"), 0);
        bird = new Bird(this, loadImage("assets/bird.png"));
        layers = new ArrayList<>();
        for(int i = 0; i < NUM_LAYERS; i++){
            layers.add(new ArrayList<>());
        }

        layers.get(0).add(background);
        layers.get(1).add(bird);

    }

    public void keyPressed(){}

    public void draw(){
        for(int i = 0; i < layers.size(); i++){
            for(GameObject object : layers.get(i)){
                object.show();
            }
        }
    }
}

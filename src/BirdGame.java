import processing.core.PApplet;
import processing.core.PImage;

public class BirdGame extends PApplet{

    GameObject object;

    public void settings(){ size(650, 650); }

    public void setup(){
        object = new GameObject(0, 0, "assets/tree.jpg");
    }

    public void keyPressed(){}

    public void draw(){
        object.draw();
    }
}

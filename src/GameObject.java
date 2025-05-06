import processing.core.PImage;
import processing.core.PApplet;

public class GameObject extends PApplet {

    int x;
    int y;

    int collisionWidth;
    int collisionHeight;

    PImage image;
    String path;

    boolean garbage;
    boolean draw;
    boolean hasCollision;

    public GameObject(){
        x = 0;
        y = 0;
        collisionWidth = 0;
        collisionHeight = 0;
        image = null;
        path = null;
        garbage = false;
        draw = false;
        hasCollision = false;
    }

    public GameObject(int x, int y, String path){
        this.x = x;
        this.y = y;
        collisionWidth = 0;
        collisionHeight = 0;
        this.path = path;
        image = loadImage(path);
        garbage = false;
        draw = true;
        hasCollision = false;
    }

    public GameObject(int x, int y, int collisionWidth, int collisionHeight, PImage image, boolean draw, boolean hasCollision){
        this.x = x;
        this.y = y;
        this.collisionWidth = collisionWidth;
        this.collisionHeight = collisionHeight;
        this.image = image;
        garbage = false;
        this.draw = draw;
        this.hasCollision = hasCollision;
    }

    public void draw(){
        if(draw){
            image(image, x , y);
        }
    }
}

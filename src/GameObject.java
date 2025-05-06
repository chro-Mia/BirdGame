import processing.core.PImage;
import processing.core.PApplet;

public class GameObject{
    PApplet applet;

    int x;
    int y;

    int collisionWidth;
    int collisionHeight;

    PImage image;

    boolean garbage;
    boolean draw;
    boolean hasCollision;

    public GameObject(PApplet applet){
        this.applet = applet;
        x = 0;
        y = 0;
        collisionWidth = 0;
        collisionHeight = 0;
        image = null;
        garbage = false;
        draw = false;
        hasCollision = false;
    }

    public GameObject(PApplet applet, int x, int y, PImage image){
        this.applet = applet;
        this.x = x;
        this.y = y;
        collisionWidth = 0;
        collisionHeight = 0;
        this.image = image;
        garbage = false;
        draw = true;
        hasCollision = false;
    }

    public GameObject(PApplet applet, int x, int y, int collisionWidth, int collisionHeight, PImage image, boolean draw, boolean hasCollision){
        this.applet = applet;
        this.x = x;
        this.y = y;
        this.collisionWidth = collisionWidth;
        this.collisionHeight = collisionHeight;
        this.image = image;
        garbage = false;
        this.draw = draw;
        this.hasCollision = hasCollision;
    }

    public void show(){
        if(draw){
            applet.image(image, x, y);
        }
    }
}

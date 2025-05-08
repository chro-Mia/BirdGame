import processing.core.PImage;
import processing.core.PApplet;

public class GameObject{
    private final PApplet applet;
    private int layer;

    int x;
    int y;

    int collisionWidth;
    int collisionHeight;

    PImage image;

    boolean garbage;
    boolean show;
    boolean hasCollision;

    public GameObject(PApplet applet){
        this.applet = applet;
        x = 0;
        y = 0;
        layer = 0;
        collisionWidth = 0;
        collisionHeight = 0;
        image = null;
        garbage = false;
        show = false;
        hasCollision = false;
    }

    public GameObject(PApplet applet, int x, int y, PImage image, int layer){
        this.applet = applet;
        this.x = x;
        this.y = y;
        this.layer = layer;
        collisionWidth = 0;
        collisionHeight = 0;
        this.image = image;
        garbage = false;
        show = true;
        hasCollision = false;
    }

    public GameObject(PApplet applet, int x, int y, PImage image, int layer, int collisionWidth, int collisionHeight, boolean show, boolean hasCollision){
        this.applet = applet;
        this.x = x;
        this.y = y;
        this.layer = layer;
        this.collisionWidth = collisionWidth;
        this.collisionHeight = collisionHeight;
        this.image = image;
        garbage = false;
        this.show = show;
        this.hasCollision = hasCollision;
    }

    public void show(){
        if(show){
            applet.image(image, x, y);
        }
    }

    public int getLayer(){
        return layer;
    }
}

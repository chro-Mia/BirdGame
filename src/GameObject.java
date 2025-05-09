import processing.core.PImage;
import processing.core.PApplet;

public class GameObject{
    private final PApplet applet;
    private int layer;

    double x;
    double y;
    double vx;
    double vy;

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
        vx = 0;
        vy = 0;
        layer = 0;
        collisionWidth = 0;
        collisionHeight = 0;
        image = null;
        garbage = false;
        show = false;
        hasCollision = false;
    }

    public GameObject(PApplet applet, double x, double y, PImage image, int layer){
        this.applet = applet;
        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;
        this.layer = layer;
        collisionWidth = 0;
        collisionHeight = 0;
        this.image = image;
        garbage = false;
        show = true;
        hasCollision = false;
    }

    public GameObject(PApplet applet, double x, double y, PImage image, int layer, int collisionWidth, int collisionHeight, boolean show, boolean hasCollision){
        this.applet = applet;
        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;
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
            applet.image(image, (float) x, (float)y);
        }
    }

    public int getLayer(){
        return layer;
    }

    public void move(){
        x += vx;
        y += vy;
    }

    public boolean detectCollision(GameObject obj){
        if(obj.hasCollision && hasCollision){
            return (x >= obj.x && x <= obj.x + obj.collisionWidth && y >= obj.y && y <= obj.y + obj.collisionHeight) ||
                    (x + collisionWidth >= obj.x && x <= obj.x + obj.collisionWidth && y >= obj.y && y <= obj.y + obj.collisionHeight) ||
                    (x + collisionWidth >= obj.x && x <= obj.x + obj.collisionWidth && y + collisionHeight >= obj.y && y <= obj.y + obj.collisionHeight) ||
                    (x >= obj.x && x <= obj.x + obj.collisionWidth && y >= obj.y && y + collisionHeight <= obj.y + obj.collisionHeight);
        }
        return false;
    }
}

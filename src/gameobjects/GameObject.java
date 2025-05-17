package gameobjects;

import processing.core.PImage;
import processing.core.PApplet;

public class GameObject{
    final PApplet applet;

    float x;
    float y;
    float vx;
    float vy;

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
        collisionWidth = 0;
        collisionHeight = 0;
        image = null;
        garbage = false;
        show = false;
        hasCollision = false;
    }

    public GameObject(PApplet applet, float x, float y, PImage image){
        this.applet = applet;
        this.x = x;
        this.y = y;
        vx = 0;
        vy = 0;
        collisionWidth = 0;
        collisionHeight = 0;
        this.image = image;
        garbage = false;
        show = true;
        hasCollision = false;
    }

    public GameObject(PApplet applet,
                      float x,
                      float y,
                      PImage image,
                      int collisionWidth,
                      int collisionHeight,
                      float vx,
                      float vy,
                      boolean show,
                      boolean hasCollision){
        this.applet = applet;
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
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

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void markGarbage(){
        garbage = true;
    }

    public boolean isGarbage(){
        return garbage;
    }

    public void setVx(float vx){
        this.vx = vx;
    }

    public void setVy(float vy){
        this.vy = vy;
    }
}

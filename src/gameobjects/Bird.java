package gameobjects;

import processing.core.PApplet;
import processing.core.PImage;

public class Bird extends GameObject{
    PImage frame1;
    PImage frame2;

    public Bird(PApplet applet) {
        super(applet);
        hasCollision = true;
        frame1 = applet.loadImage("assets/bird1.png");
        frame2 = applet.loadImage("assets/bird2.png");
        this.image = frame1;

        show = true;
        collisionWidth = 60;
        collisionHeight = 60;
        x = 100;
        y = 295;
    }

    public void jump(){
        vy = -11;
    }

    public void gravity(){
        vy += 0.4f;
    }

    public void frame1(){
        image = frame1;
    }

    public void frame2(){
        image = frame2;
    }
}

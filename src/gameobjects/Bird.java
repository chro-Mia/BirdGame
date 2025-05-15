package gameobjects;

import processing.core.PApplet;
import processing.core.PImage;

public class Bird extends GameObject{
    public Bird(PApplet applet) {
        super(applet);
        hasCollision = true;
        this.image = applet.loadImage("assets/bird.png");
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
}

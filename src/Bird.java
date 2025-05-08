import processing.core.PApplet;
import processing.core.PImage;

public class Bird extends GameObject{
    public Bird(PApplet applet, PImage image) {
        super(applet);
        hasCollision = true;
        this.image = image;
        show = true;
        collisionWidth = 30;
        collisionHeight = 30;
        x = 100;
        y = 325;
    }

    public void jump(){
        vy = -10;
    }

    public void gravity(){
        vy += 0.3;
    }
}

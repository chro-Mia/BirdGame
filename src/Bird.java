import processing.core.PApplet;
import processing.core.PImage;

public class Bird extends GameObject{
    public Bird(PApplet applet, PImage image) {
        super(applet);
        hasCollision = true;
        this.image = image;
        show = true;

    }

    public void jump(){

    }
}

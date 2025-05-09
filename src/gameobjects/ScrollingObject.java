package gameobjects;

import processing.core.PApplet;
import processing.core.PImage;

public class ScrollingObject extends GameObject{
    public ScrollingObject(PApplet applet, double x, double y, PImage image, double vx){
        super(applet, x, y, image);
        this.vx = vx;
    }

    @Override
    public void move() {
        super.move();
        if(x < -1 * image.pixelWidth){
            x = image.pixelWidth;
        }
    }
}

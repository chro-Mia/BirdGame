package gameobjects;

import processing.core.PApplet;
import processing.core.PImage;

public class ScrollingObject{

    float x;
    float vx;

    PApplet applet;
    PImage image;

    public ScrollingObject(PApplet applet, PImage image, float vx){
        this.applet = applet;
        this.x = 0;
        this.vx = vx;
        this.image = image;
    }

    public void scroll() {
        x += vx;
        if(x < -1 * image.pixelWidth){
            x = 0;
        }
        applet.image(image, x, 0);
        applet.image(image, x + image.pixelWidth, 0);

    }
}

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

    //when the left half object is entirely off the left border of the screen, it gets teleported back to the beginning
    //seamlessly

    //with certain textures and images, this can make the scrolling infinite and seamless
    public void move() {
        x += vx;
        if(x < -1 * image.pixelWidth){
            x = 0;
        }
    }

    //each object actually displays two images, one at the normal position, and one that is a certain amount of pixels
    //to the right of the original image

    //this allows it to actually scroll without smearing
    public void show(){
        applet.image(image, x, 0);
        applet.image(image, x + image.pixelWidth, 0);
    }

    public void setVx(float vx){
        this.vx = vx;
    }

    public float getVx(){
        return vx;
    }
}

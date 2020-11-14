package demotank;

import java.awt.*;
import java.util.Random;

public class Explode {

    private boolean living = true;
    private  int x,y;
    private tankFrame tf = null;
    private  int step = 0;
    public static int WIDTH = ResourceMgr.exp[0].getWidth();
    public static int HEIGHT = ResourceMgr.exp[0].getHeight();


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Explode(int x, int y, tankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Audio("audio/explode.wav").play();
    }

    public void paint(Graphics g) {
        if(!living) tf.explodes.add(this);

        g.drawImage(ResourceMgr.exp[step++],this.x,this.y,null);
        if(step>=ResourceMgr.exp.length) {
            step = 0;
        }

    }

    public void die() {
        this.living = false;
    }
}

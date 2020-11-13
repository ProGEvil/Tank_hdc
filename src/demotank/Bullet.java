package demotank;

import java.awt.*;



public class Bullet {
    private static final int SPEED = 10;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int x,y;
    private Dir dir;
    private boolean live = true;
    tankFrame tf = null;


    public Bullet(int x,int y,Dir dir,tankFrame tf){

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g){
        if(!live){
            tf.bullets.remove(this);
        }
        Color c = g.getColor();
        switch (dir){
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        switch(dir){
            case LEFT:
                x-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }
        if(x < 0 || y < 0 || x > tankFrame.GAME_WIDTH || y > tankFrame.GAME_HEIGHT) live = false;
    }
}

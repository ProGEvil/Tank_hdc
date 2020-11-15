package demotank;

import org.junit.jupiter.params.shadow.com.univocity.parsers.fixed.FixedWidthFieldLengths;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 6;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private int x,y;

    Rectangle rect = new Rectangle();

    private Dir dir;
    private Group group = Group.BAD;
    private boolean living = true;
    tankFrame tf = null;


    public Bullet(int x,int y,Dir dir,Group group,tankFrame tf){

        this.x = x;
        this.y = y;
        this.group = group;
        this.dir = dir;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height= HEIGHT;
    }

    public void paint(Graphics g){
        if(!living){
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
        //update rect
        rect.x = this.x;
        rect.y = this.y;

        if(x < 0 || y < 0 || x > tankFrame.GAME_WIDTH || y > tankFrame.GAME_HEIGHT) living = false;
    }

    public void collideWith(Tank tank) {
        if (this.group==tank.getGroup()) return;

        Rectangle rect1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if (rect.intersects(tank.rect)){
            tank.die();
            this.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodes.add(new Explode(eX,eY,tf));
        }
    }

    private void die() {
        this.living = false;
    }
}

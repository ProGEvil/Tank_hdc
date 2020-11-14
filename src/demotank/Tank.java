package demotank;

import java.awt.*;
import java.util.Random;

public class Tank {
    Dir dir = Dir.DOWN;
    private  int x,y;
    private static final int SPEED = 2;
    private boolean moving = true;
    private tankFrame tf = null;
    private Random random = new Random();
    private Group group = Group.BAD;
    public static int WIDTH = ResourceMgr.badTankD.getWidth();
    public static int HEIGHT = ResourceMgr.badTankD.getHeight();
    private boolean living = true;

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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Tank(int x, int y, Dir dir, Group group, tankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.group = group;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {

        if (!living) {
            tf.tanks.remove(this);
        }
        Color c = g.getColor();

            switch (dir) {
                case LEFT:
                    g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL: ResourceMgr.badTankL, x, y, null);
                    break;
                case UP:
                    g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU :ResourceMgr.badTankU, x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR :ResourceMgr.badTankR, x, y, null);
                    break;
                case DOWN:
                    g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD :ResourceMgr.badTankD, x, y, null);
                    break;
                default:
                    break;
            }
            move();

    }



    private void move() {
        if (!moving) return ;
            switch (dir) {
                case LEFT:
                    x -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
                case UP:
                    y -= SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
                default:
                    break;
            }
            if(this.group==Group.BAD && random.nextInt(100) > 95) this.fire();
            if(this.group==Group.BAD && random.nextInt(100) > 95) randomDir();

            boundsCheck();
    }

    private void boundsCheck() {
        if(this.x<2) x = 2;
        if(this.y<28) y = 28;
        if(this.x > tankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = tankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if(this.y > tankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = tankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tf.bullets.add(new Bullet(bX,bY,this.dir,this.group,this.tf));

        if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        this.living = false;
    }
}

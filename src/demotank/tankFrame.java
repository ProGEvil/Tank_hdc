package demotank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


public class tankFrame extends Frame {

    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 1000;
    List<Bullet> bullets = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    Tank mytank = new Tank(600,800,Dir.UP,Group.GOOD,this);
    Bullet mybullet = new Bullet(100,100,Dir.DOWN,Group.GOOD,this);
    Explode explode = new Explode(100,200,this);
    

    @Override
    public void paint(Graphics g){

        Color c = Color.RED;
        g.setColor(Color.RED);
        g.drawString("Number of bullets:" + bullets.size(),10,60);
        g.drawString("Number of enemys:" + tanks.size(),10,80);
        g.setColor(c);

        mytank.paint(g);
        for(int i =0;i<bullets.size();i++){
            bullets.get(i).paint(g);
        }
        mytank.paint(g);
        for(int i =0;i<tanks.size();i++){
            tanks.get(i).paint(g);
        }
        mytank.paint(g);
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++){
                bullets.get(i).collideWith(tanks.get(j));

            }
        }
        explode.paint(g);



    }
    class MyKeyListener extends KeyAdapter{
        boolean bl = false;
        boolean br = false;
        boolean bu = false;
        boolean bd = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int Key = e.getKeyCode();
            switch (Key) {
                case KeyEvent.VK_LEFT:
                    bl = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int Key = e.getKeyCode();
            switch (Key) {
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    mytank.fire();
                    break;

                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(!br && !bl && !bd && !bu) mytank.setMoving(false);
            else {
                mytank.setMoving(true);

                if (br) mytank.setDir(Dir.RIGHT);
                if (bl) mytank.setDir(Dir.LEFT);
                if (bu) mytank.setDir(Dir.UP);
                if (bd) mytank.setDir(Dir.DOWN);
            }


        }


    }
    public tankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }
}

package demotank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        tankFrame tF = new tankFrame();

        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            tF.tanks.add(new Tank(50 + i*80,200, Dir.DOWN,Group.BAD,tF));
        }

        while (true) {

            Thread.sleep(25);
            tF.repaint();

        }
    }
}

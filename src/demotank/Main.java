package demotank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        tankFrame tF = new tankFrame();
        for (int i = 0; i < 5; i++) {
            tF.tanks.add(new Tank(50 + i*80,200, Dir.DOWN,Group.BAD,tF));
        }

        while (true) {

            Thread.sleep(25);
            tF.repaint();

        }
    }
}

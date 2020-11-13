package demotank;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        tankFrame tF = new tankFrame();
        while (true) {
            for (int i = 0; i < 5; i++) {
                tF.tanks.add(new Tank(50 + i*80,200, Dir.DOWN, tF));
            }
            Thread.sleep(50);
            tF.repaint();

        }
    }
}

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {

    private Race race;
    private int speed;
    private String name;
    private CountDownLatch cdlReady;
    private CountDownLatch cdlFinish;
    private CyclicBarrier cyclicBarrier;


    public Car(Race race, int speed,int number, CountDownLatch cdlReady, CountDownLatch cdlFinish, CyclicBarrier cyclicBarrier) {
        this.race = race;
        this.speed = speed;
        this.name = "Участник #" + number;
        this.cdlReady = cdlReady;
        this.cdlFinish = cdlFinish;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cdlReady.countDown();
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        cdlFinish.countDown();
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }


}


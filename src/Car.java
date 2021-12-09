import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {

    private static int CARS_COUNT;

    private final Race race;
    private final int speed;
    private final String name;
    private static final CountDownLatch cdlReady;
    private static final CountDownLatch cdlFinish;
    private static final CyclicBarrier cyclicBarrier;

    static {
        CARS_COUNT = 0;
        cdlReady = MainClass.cdlReady;
        cdlFinish = MainClass.cdlFinish;
        cyclicBarrier = MainClass.cyclicBarrier;
    }



    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
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


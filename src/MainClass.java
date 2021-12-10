import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainClass {

    static int CARS_COUNT = 4;
    static final CountDownLatch cdlReady = new CountDownLatch(CARS_COUNT);
    static final CountDownLatch cdlFinish = new CountDownLatch(CARS_COUNT);
    static final CyclicBarrier cyclicBarrier = new CyclicBarrier(CARS_COUNT);

        public static void main(String[] args) {

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT/2), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            final int speed = 20 + (int) (Math.random() * 10);
            cars[i] = new Car(race, speed);
        }

        for (Car car : cars) {
            new Thread(car).start();

        }
        try {
            cdlReady.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            cdlFinish.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}




import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore semaphore;
    final int weight;

    public Tunnel(Integer weight) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.weight = weight;
        this.semaphore = new Semaphore(weight);

    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
                System.out.println(c.getName() + " закончил этап: " + description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
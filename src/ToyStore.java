import java.util.PriorityQueue;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;


public class ToyStore {
    private PriorityQueue<Toy> queue;
    private Random rand;
    private static final int NUM_TOYS = 10;
    private static final String FILENAME = "result.txt";

    public static void main(String[] args) {
        ToyStore store = new ToyStore();
        store.addToy("1", "Teddy Bear", 5);
        store.addToy("2", "Lego Set", 10);
        store.addToy("3", "Doll", 3);
        store.addToy("4", "Plane", 6);
        store.addToy("5", "Train", 9);
        store.addToy("6", "Car", 4);
        store.addToy("7", "Ducky", 7);
        store.play();
    }
    
    public ToyStore() {
        queue = new PriorityQueue<Toy>((t1, t2) -> t1.getWeight() - t2.getWeight());
        rand = new Random();
    }
    
    public void addToy(String id, String name, int weight) {
        Toy toy = new Toy(id, name, weight);
        queue.add(toy);
    }
    
    public void play() {
        try (FileWriter fw = new FileWriter(FILENAME)) {
            for (int i = 0; i < NUM_TOYS; i++) {
                Toy toy = queue.poll();
                String result = String.format("Toy %s with id %s is selected.", toy.getName(), toy.getId());
                System.out.println(result);
                fw.write(result + "\n");
                toy = new Toy(toy.getId(), toy.getName(), toy.getWeight() + rand.nextInt(10));
                queue.add(toy);
            }
        } catch (IOException e) {
            System.err.println("Failed to write result to file.");
            e.printStackTrace();
        }
    }
}
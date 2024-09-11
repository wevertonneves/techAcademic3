import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;
    private String name;
    private int currentStage;
    private List<Item> collectedItems;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.currentStage = 1; // Começa na fase 1
        this.collectedItems = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getCurrentStage() { return currentStage; }
    public List<Item> getCollectedItems() { return collectedItems; }

    public void addItem(Item item) { collectedItems.add(item); }
    public void advanceStage() { currentStage++; }
}

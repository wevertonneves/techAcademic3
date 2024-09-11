import java.util.List;

public class Stage {
    private int id;
    private String description;
    private String question;
    private List<String> options;
    private int correctOptionIndex;
    private Item requiredItem;
    private Challenge challenge;
    private String hint; //

    public Stage(int id, String description, String question, List<String> options, int correctOptionIndex, Item requiredItem, String hint) {
        this.id = id;
        this.description = description;
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.requiredItem = requiredItem;
        this.challenge = new Challenge(question, options, correctOptionIndex, hint); 
        this.hint = hint;
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getQuestion() { return question; }
    public List<String> getOptions() { return options; }
    public int getCorrectOptionIndex() { return correctOptionIndex; }
    public Item getRequiredItem() { return requiredItem; }
    public Challenge getChallenge() { return challenge; }
    public String getHint() { return hint; } // Getter para a dica
}

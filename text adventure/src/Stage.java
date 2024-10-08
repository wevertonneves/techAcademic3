import java.sql.Connection;
import java.util.List;

public class Stage {
    private int id;
    private String description;
    private String question;
    private List<String> options;
    private int correctOptionIndex;
    private Item requiredItem;
    private Challenge challenge;
    private String hint;

    public Stage(int id, String description, String question, List<String> options, int correctOptionIndex, Item requiredItem, String hint, Connection connection) {
        this.id = id;
        this.description = description;
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.requiredItem = requiredItem;
        this.hint = hint;
        // Passa a conexão para o Challenge
        this.challenge = new Challenge(question, options, correctOptionIndex, hint, connection);
    }

    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getQuestion() { return question; }
    public List<String> getOptions() { return options; }
    public int getCorrectOptionIndex() { return correctOptionIndex; }
    public Item getRequiredItem() { return requiredItem; }
    public Challenge getChallenge() { return challenge; }
    public String getHint() { return hint; }
}

import java.util.List;
import java.util.Scanner;

public class Challenge {
    private String question;
    private List<String> options;
    private int correctOptionIndex;
    private String hint;

    public Challenge(String question, List<String> options, int correctOptionIndex, String hint) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.hint = hint;
    }

    public boolean execute() {
        System.out.println(question);


        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ": " + options.get(i));
        }

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if (choice - 1 == correctOptionIndex) {
            System.out.println("Você escolheu a resposta correta!");
            return true;
        } else {
            System.out.println("Resposta errada! Tente novamessssssssnte.");
            return false;
        }
    }
}

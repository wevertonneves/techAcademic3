import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Challenge {
    private String question;
    private List<String> options;
    private int correctOptionIndex;
    private String hint;
    private Connection connection; // Conexão com o banco de dados

    public Challenge(String question, List<String> options, int correctOptionIndex, String hint, Connection connection) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.hint = hint;
        this.connection = connection; // Inicializa a conexão
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
            int wrongAnswerIndex = choice - 1;
            List<String> messages = getErrorMessages(wrongAnswerIndex);
            if (messages != null && !messages.isEmpty()) {
                System.out.println(messages.get(0));
            } else {
                System.out.println("Resposta errada! Tente novamente.");
            }
            return false;
        }
    }

    private List<String> getErrorMessages(int index) {
        List<String> messages = new ArrayList<>();
        String query = "SELECT message FROM stage_messages WHERE option_index = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, index);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                messages.add(resultSet.getString("message"));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Trate exceções de maneira adequada
        }

        return messages;
    }
}

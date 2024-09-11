import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/adventure_game";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public List<Stage> getStages() {
        List<Stage> stages = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String query = "SELECT s.id, s.description, s.question, s.options, s.correct_option_index, s.hint, i.id as item_id, i.name as item_name " +
                    "FROM stages s LEFT JOIN items i ON s.required_item_id = i.id";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                String question = rs.getString("question");
                List<String> options = List.of(rs.getString("options").split(","));
                int correctOptionIndex = rs.getInt("correct_option_index");
                String hint = rs.getString("hint");

                // Carregar o item necessário para a fase
                int itemId = rs.getInt("item_id");
                String itemName = rs.getString("item_name");
                Item requiredItem = new Item(itemId, itemName);

                // Criar o objeto Stage e adicionar à lista
                Stage stage = new Stage(id, description, question, options, correctOptionIndex, requiredItem, hint);
                stages.add(stage);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao carregar fases do banco de dados: " + e.getMessage());
        }

        return stages;
    }
}

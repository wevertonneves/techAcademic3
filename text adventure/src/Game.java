
import java.util.*;

public class Game {
    private Player player;
    private List<Stage> stages;
    private DatabaseManager dbManager;

    public Game(Player player) {
        this.player = player;
        this.stages = new ArrayList<>();
        this.dbManager = new DatabaseManager();
        loadStages();
    }

    private void loadStages() {

        stages = dbManager.getStages();

        if (stages.isEmpty()) {
            System.out.println("Erro: Nenhuma fase.");
        } else {
            System.out.println("Fases carregadas com sucesso: " + stages.size() + " fases encontradas.");
        }
    }


    public void start() {
        System.out.println("Bem-vindo ao jogo, " + player.getName());
        play();
    }

    private void play() {

        while (player.getCurrentStage() <= stages.size()) {
            Stage currentStage = stages.get(player.getCurrentStage() - 1);
            System.out.println("" + currentStage.getDescription());


            Item requiredItem = currentStage.getRequiredItem();
            if (!player.getCollectedItems().contains(requiredItem)) {



                boolean success = currentStage.getChallenge().execute();
                if (success) {

                    player.addItem(requiredItem);
                    System.out.println("Item coletado: " + requiredItem.getName());
                } else {

                    continue;
                }
            } else {
                System.out.println("Você já possui o item necessário: " + requiredItem.getName());
            }


            System.out.println("Avançando para a próxima fase...");
            player.advanceStage();
        }


        System.out.println("Parabéns! Você completou todas as fases!");
    }



    public static void main(String[] args) {

        Player player = new Player(1, "Jogador 1");


        Game game = new Game(player);

        
        game.start();
    }
}
<?php
session_start();
$conn = new mysqli("localhost", "root", "", "adventure_game");
if ($conn->connect_error) {
    die("Erro de conexão: " . $conn->connect_error);
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $answer = $_POST['answer'];
    $currentStageIndex = $_SESSION['current_stage'];
    $stages = $_SESSION['stages'];
    $currentStage = $stages[$currentStageIndex];
    $correctOptionIndex = $currentStage['correct_option_index'];

    if (intval($answer) === intval($correctOptionIndex)) {
        $_SESSION['collected_items'][] = $currentStage['item_name'];
        $_SESSION['current_stage']++;

        // Atualiza o progresso do jogador no banco de dados
        $player_id = $_SESSION['player_id'];
        $collected_items = implode(',', $_SESSION['collected_items']);
        $current_stage = $_SESSION['current_stage'];

        $sql = "UPDATE players SET current_stage = $current_stage, collected_items = '$collected_items' WHERE id = $player_id";
        $conn->query($sql);

        $_SESSION['message'] = "Resposta correta! Avançando para a próxima fase...";
    } else {
        $_SESSION['message'] = "Resposta errada! Tente novamente.";
    }
}

header('Location: index.php');
exit;
?>

<?php
session_start();


$conn = new mysqli("localhost", "root", "", "adventure_game");
if ($conn->connect_error) {
    die("Erro de conexão: " . $conn->connect_error);
}


function loadStages($conn) {
    $stages = [];
    $sql = "SELECT s.id, s.description, s.question, s.options, s.hint, s.correct_option_index, i.name AS item_name 
            FROM stages s LEFT JOIN items i ON s.required_item_id = i.id";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $stages[] = $row;
        }
    }
    return $stages;
}


if (!isset($_SESSION['player_name'])) {
    if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['player_name'])) {
        $player_name = $_POST['player_name'];

        
        $sql = "INSERT INTO players (name, current_stage) VALUES ('$player_name', 0)";
        if ($conn->query($sql) === TRUE) {
            $_SESSION['player_name'] = $player_name;
            $_SESSION['current_stage'] = 0;
            $_SESSION['collected_items'] = [];
            $_SESSION['player_id'] = $conn->insert_id;
            $_SESSION['stages'] = loadStages($conn);
        } else {
            echo "Erro ao registrar jogador: " . $conn->error;
            exit;
        }}}
   
        

if (!isset($_SESSION['stages'])) {
    $_SESSION['stages'] = loadStages($conn);
    $_SESSION['current_stage'] = 0;
    $_SESSION['collected_items'] = [];
}

$currentStageIndex = $_SESSION['current_stage'];
$stages = $_SESSION['stages'];

if ($currentStageIndex >= count($stages)) {
    echo "Parabéns! Você completou todas as fases!";
    session_destroy();
    exit;
}

$currentStage = $stages[$currentStageIndex];
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>techacademic3</title>
    <link rel="stylesheet" href="css/estilos.css">
    <link rel="shorcut icon" href="imagens/incone site.png">
        
    
</head>
<body>
<div class="container">
    <?php if (isset($_SESSION['message'])): ?>
        <div class="message"><?php echo $_SESSION['message']; unset($_SESSION['message']); ?></div>
    <?php endif; ?>

    <h1><?php echo "Você está na fase: " . $currentStage['description']; ?></h1>
    <p><?php echo "Dica: " . $currentStage['hint']; ?></p>
    <form method="post" action="process.php">
        <p><?php echo $currentStage['question']; ?></p>
        <?php
        $options = explode(",", $currentStage['options']);
        foreach ($options as $index => $option) {
            echo "<input type='radio' name='answer' value='{$index}'> {$option}<br>";
        }
        ?>
        <button type="submit">Enviar Resposta</button>
    </form>
</div>


<div class="audio-controls">
    <audio id="background-audio" controls autoplay loop>
        <source src="assets/audio/teste.mp3" type="audio/mpeg">
            </audio>
</div>
</body>
</html>

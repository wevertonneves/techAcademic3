<?php
session_start();

// URL base do seu back-end Java
$baseUrl = "http://localhost:8080";

// Função para fazer requisições HTTP
function httpRequest($url, $method = 'GET', $data = null) {
    $options = [
        'http' => [
            'header' => "Content-Type: application/json\r\n",
            'method' => $method,
            'content' => $data ? json_encode($data) : ''
        ]
    ];
    $context = stream_context_create($options);
    $result = file_get_contents($url, false, $context);
    return json_decode($result, true);
}

// Verifica se o jogador já foi registrado
if (!isset($_SESSION['player_name'])) {
    if ($_SERVER['REQUEST_METHOD'] === 'POST' && isset($_POST['player_name'])) {
        $player_name = $_POST['player_name'];

        // Faz uma requisição POST para registrar o jogador
        $response = httpRequest("$baseUrl/players", 'POST', ['name' => $player_name]);

        if (isset($response['id'])) {
            $_SESSION['player_name'] = $player_name;
            $_SESSION['player_id'] = $response['id'];
            $_SESSION['current_stage'] = 0;
            $_SESSION['collected_items'] = [];

            // Carrega as fases via API
            $_SESSION['stages'] = httpRequest("$baseUrl/stages");
        } else {
            echo "Erro ao registrar jogador.";
            exit;
        }
    } else {
        // Mostra o formulário de entrada do nome do jogador
        echo '<!DOCTYPE html>
        <html lang="pt-BR">
        <head>
            <meta charset="UTF-8">
            <title>Jogo Text Adventure</title>
            <style>/* CSS Aqui */</style>
        </head>
        <body>
            <div class="container">
                <h1>Bem-vindo ao Jogo Text Adventure!</h1>
                <form method="post">
                    <label for="player_name">Digite seu nome:</label>
                    <input type="text" id="player_name" name="player_name" required>
                    <button type="submit">Começar Jogo</button>
                </form>
            </div>
        </body>
        </html>';
        exit;
    }
}

// Carrega as fases
$currentStageIndex = $_SESSION['current_stage'];
$stages = $_SESSION['stages'];

if ($currentStageIndex >= count($stages)) {
    echo "Parabéns! Você completou todas as fases!";
    session_destroy();
    exit;
}

$currentStage = $stages[$currentStageIndex];
?>

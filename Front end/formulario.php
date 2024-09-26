<!DOCTYPE html>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        
        h1 {
            color: #4CAF50;
        }
        form {
            margin-top: 20px;
        }
    </style>

<body>
    <img src="imagens/inicio.jpg" alt="Imagem de início">
    <h1>Bem-vindo ao Jogo O Despertar do Guardião</h1>
    <form method="post">
        <label for="player_name">Digite seu nome:</label>
        <input type="text" id="player_name" name="player_name" required>
        <button type="submit">INICIAR A JORNADA</button>
    </form>
</body>
</html>

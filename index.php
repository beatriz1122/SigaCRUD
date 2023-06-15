<!DOCTYPE html>
<html>
<head>
    <title>Tela com Botões</title>
    <style>
        .button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            text-align: center;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 4px;
        }

        .button:hover {
            background-color: #45a049;
        }

        .button-red {
            background-color: #f44336;
        }

        .button-red:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <h2>Tela com Botões</h2>

    <?php
        // Verificar se o botão "Botão 1" foi clicado
        if (isset($_POST['botao1'])) {
            echo "<p>O Botão 1 foi clicado!</p>";
        }

        // Verificar se o botão "Botão 2" foi clicado
        if (isset($_POST['botao2'])) {
            echo "<p>O Botão 2 foi clicado!</p>";
        }
    ?>

<a href="loginp.php"><button>Professor</button></a>
<a href="logina.php"><button>Aluno</button></a>
</body>
</html>

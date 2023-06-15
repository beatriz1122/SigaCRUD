<!DOCTYPE html>
<html>
<head>
    <title>Tela de Login</title>
    <style>
        .container {
            width: 300px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
        }

        .container h2 {
            text-align: center;
        }

        .container label {
            display: block;
            margin-bottom: 10px;
        }

        .container input[type="text"],
        .container input[type="password"] {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
        }

        .container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        .container input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error-message {
            color: #f44336;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <?php
        // Função para realizar a autenticação no banco de dados
        function autenticar($email, $senha) {
            // Substitua as credenciais do banco de dados pelas suas próprias
            $servername = "localhost";
            $username = "root";
            $password = "";
            $dbname = "siga";

            // Conectando ao banco de dados
            $conn = new mysqli($servername, $username, $password, $dbname);

            // Verificando erros na conexão
            if ($conn->connect_error) {
                die("Erro na conexão com o banco de dados: " . $conn->connect_error);
            }

            // Query para verificar se o email e senha correspondem a um registro na tabela "pro"
            $sql = "SELECT * FROM pro WHERE email = '$email' AND senha = '$senha'";
            $result = $conn->query($sql);

            // Verificando se a consulta retornou algum resultado
            if ($result->num_rows > 0) {
                // Autenticação bem-sucedida
                echo "<p>Login bem-sucedido!</p>";
            } else {
                // Autenticação falhou
                echo "<p class='error-message'>Email ou senha incorretos!</p>";
            }

            // Fechando a conexão com o banco de dados
            $conn->close();
        }

        // Verificar se o formulário de login foi submetido
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            // Obter os valores do formulário
            $email = $_POST["email"];
            $senha = $_POST["senha"];

            // Chamar a função de autenticação
            autenticar($email, $senha);
        }
    ?>

    <div class="container">
        <h2>Tela de Login</h2>
        <form method="POST">
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required>

            <label for="senha">Senha:</label>
            <input type="password" id="senha" name="senha" required>

            <input type="submit" value="Login">
        </form>
    </div>
</body>

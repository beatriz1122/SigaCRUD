<!DOCTYPE html>
<html>
<head>
    <title>Formulário de Aluno</title>
</head>
<body>
    <h2>Formulário de Aluno</h2>
    <form action="processar.php" method="POST">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="senha">Senha:</label>
        <input type="password" id="senha" name="senha" required><br><br>
        
        <label for="ra">RA:</label>
        <input type="text" id="ra" name="ra" required><br><br>
        
        <input type="submit" value="Enviar">
    </form>
</body>
</html>

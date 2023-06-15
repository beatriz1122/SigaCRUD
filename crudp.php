<!DOCTYPE html>
<html>
<head>
    <title>Formulário de Professor</title>
</head>
<body>
    <h2>Formulário de Professor</h2>
    <form action="processar.php" method="POST">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>
        
        <label for="senha">Senha:</label>
        <input type="password" id="senha" name="senha" required><br><br>
        
        <label for="area">Área:</label>
        <input type="text" id="area" name="area" required><br><br>
        
        <input type="submit" value="Enviar">
    </form>
</body>
</html>

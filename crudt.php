<!DOCTYPE html>
<html>
<head>
    <title>Formulário de TCC</title>
</head>
<body>
    <h2>Formulário de TCC</h2>
    <form action="processar.php" method="POST">
        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required><br><br>
        
        <label for="tema">Tema:</label>
        <input type="text" id="tema" name="tema" required><br><br>
        
        <label for="in1">Integrante 1:</label>
        <input type="text" id="in1" name="in1" required><br><br>
        
        <label for="in2">Integrante 2:</label>
        <input type="text" id="in2" name="in2" required><br><br>
        
        <label for="in3">Integrante 3:</label>
        <input type="text" id="in3" name="in3" required><br><br>
        
        <label for="pro">Professor(a):</label>
        <input type="text" id="pro" name="pro" required><br><br>
        
        <label for="des">Descrição:</label>
        <textarea id="des" name="des" rows="4" cols="50" required></textarea><br><br>
        
        <input type="submit" value="Enviar">
    </form>
</body>
</html>

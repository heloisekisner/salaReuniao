<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Cadastro de Sala</title></head>
<body>
    <h2>Cadastro de Sala de Reunião</h2>
    <form action="salas?action=insert" method="post">
        Nome: <input type="text" name="nome" required><br>
        Capacidade: <input type="number" name="capacidade" required><br>
        Localização: <input type="text" name="localizacao" required><br>
        Projetor: <input type="checkbox" name="projetor"><br>
        Ar-condicionado: <input type="checkbox" name="ar"><br>
        Nº de Cadeiras: <input type="number" name="cadeiras" required><br>
        Tipo de Mesa: <input type="text" name="mesa" required><br>
        Recursos Adicionais: <input type="text" name="recursos"><br><br>
        <input type="submit" value="Salvar">
    </form>
    <p><a href="salas">Voltar à lista</a></p>
</body>
</html>

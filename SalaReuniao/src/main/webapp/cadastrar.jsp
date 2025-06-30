<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <title>Cadastrar Sala</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    body {
      margin: 0;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: #f8f9fa;
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
    }
    .container {
      background-color: white;
      padding: 2rem 2.5rem;
      border-radius: 16px;
      box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
      max-width: 600px;
      width: 100%;
    }
    h2 { text-align: center; color: #333; }
    form { display: flex; flex-direction: column; gap: 0.8rem; }
    input[type="text"],
    input[type="number"],
    input[type="submit"] {
      padding: 0.6rem;
      border: 1px solid #ccc;
      border-radius: 8px;
      font-size: 1rem;
    }
    input[type="submit"] {
      background-color: #007bff;
      color: white;
      cursor: pointer;
      transition: background-color 0.3s ease;
    }
    input[type="submit"]:hover {
      background-color: #0056b3;
    }
    label { font-weight: bold; }
    a { text-align: center; display: block; margin-top: 1rem; color: #007bff; text-decoration: none; }
    a:hover { text-decoration: underline; }
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 1rem;
    }
    th, td {
      padding: 0.75rem;
      border: 1px solid #ccc;
      text-align: left;
    }
    th {
      background-color: #007bff;
      color: white;
    }
  </style>
</head>
<body>
  <div class="container">
    
<h2>Cadastrar Sala</h2>
<form action="salas?action=insert" method="post">
  <label>Nome:</label><input type="text" name="nome" required>
  <label>Capacidade:</label><input type="number" name="capacidade" required>
  <label>Localização:</label><input type="text" name="localizacao" required>
  <label>Projetor:</label><input type="checkbox" name="projetor">
  <label>Ar-condicionado:</label><input type="checkbox" name="ar">
  <label>Nº Cadeiras:</label><input type="number" name="cadeiras" required>
  <label>Tipo de Mesa:</label><input type="text" name="mesa">
  <label>Recursos Adicionais:</label><input type="text" name="recursos">
  <input type="submit" value="Cadastrar">
</form>
<a href="salas">Voltar</a>

  </div>
</body>
</html>
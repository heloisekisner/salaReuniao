<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <title>Editar Sala</title>
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
    <%@ page import="com.exemplo.servlet.SalaReuniao" %>
<%
  SalaReuniao sala = (SalaReuniao) request.getAttribute("sala");
%>
<h2>Editar Sala</h2>
<form action="salas?action=update" method="post">
  <input type="hidden" name="id" value="<%= sala.getId() %>">
  <label>Nome:</label><input type="text" name="nome" value="<%= sala.getNome() %>" required>
  <label>Capacidade:</label><input type="number" name="capacidade" value="<%= sala.getCapacidade() %>" required>
  <label>Localização:</label><input type="text" name="localizacao" value="<%= sala.getLocalizacao() %>" required>
  <label>Projetor:</label><input type="checkbox" name="projetor" <%= sala.isPossuiProjetor() ? "checked" : "" %>>
  <label>Ar-condicionado:</label><input type="checkbox" name="ar" <%= sala.isPossuiArCondicionado() ? "checked" : "" %>>
  <label>Nº Cadeiras:</label><input type="number" name="cadeiras" value="<%= sala.getNumeroCadeiras() %>" required>
  <label>Tipo de Mesa:</label><input type="text" name="mesa" value="<%= sala.getTipoMesa() %>">
  <label>Recursos Adicionais:</label><input type="text" name="recursos" value="<%= sala.getRecursosAdicionais() %>">
  <input type="submit" value="Atualizar">
</form>
<a href="salas">Voltar</a>

  </div>
</body>
</html>
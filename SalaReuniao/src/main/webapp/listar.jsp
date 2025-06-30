<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.exemplo.servlet.SalaReuniao" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <title>Listar Salas</title>
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
      max-width: 800px;
      width: 100%;
    }

    h2 {
      text-align: center;
      color: #333;
    }

    a {
      display: inline-block;
      margin: 1rem 0;
      color: #007bff;
      text-decoration: none;
      font-weight: bold;
    }

    a:hover {
      text-decoration: underline;
    }

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

    td a {
      margin-right: 0.5rem;
    }
  </style>
</head>
<body>
  <div class="container">
    <h2>Salas Cadastradas</h2>
    <a href="salas?action=new">+ Cadastrar Nova Sala</a>

    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Capacidade</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <%-- Adicionando @SuppressWarnings("unchecked") para suprimir o aviso de "unchecked cast" --%>
        <% @SuppressWarnings("unchecked")
           List<SalaReuniao> lista = (List<SalaReuniao>) request.getAttribute("listaSalas");
           if (lista != null) {
             for (SalaReuniao s : lista) {
        %>
        <tr>
          <td><%= s.getId() %></td>
          <td><%= s.getNome() %></td>
          <td><%= s.getCapacidade() %></td>
          <td>
            <a href="salas?action=edit&id=<%= s.getId() %>">Editar</a>
            |
            <a href="salas?action=delete&id=<%= s.getId() %>" onclick="return confirm('Tem certeza?')">Excluir</a>
          </td>
        </tr>
        <%
            }
          }
        %>
      </tbody>
    </table>
  </div>
</body>
</html>
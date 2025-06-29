<%@ page contentType="text/html;charset=UTF-8" %> <%-- Define tipo de conteúdo --%>
<%@ page import="java.util.*, com.exemplo.servlet.SalaReuniao" %> <%-- Importa classes necessárias --%>

<html>
<head><title>Lista de Salas</title></head>
<body>
    <h2>Salas de Reunião Cadastradas</h2>

    <%-- Link para cadastrar nova sala --%>
    <p><a href="salas?action=new">Cadastrar Nova Sala</a></p>

    <%-- Tabela com todas as salas cadastradas --%>
    <table border="1">
        <tr>
            <th>Nome</th>
            <th>Capacidade</th>
            <th>Localização</th>
            <th>Projetor</th>
            <th>Ar</th>
            <th>Cadeiras</th>
            <th>Mesa</th>
            <th>Recursos</th>
            <th>Ações</th>
        </tr>

        <% 
            // Recupera a lista de salas enviada pelo servlet
            List<SalaReuniao> lista = (List<SalaReuniao>) request.getAttribute("listaSalas");
            for (SalaReuniao s : lista) { // Percorre a lista para exibir os dados
        %>
        <tr>
            <td><%= s.getNome() %></td>
            <td><%= s.getCapacidade() %></td>
            <td><%= s.getLocalizacao() %></td>
            <td><%= s.isPossuiProjetor() ? "Sim" : "Não" %></td>
            <td><%= s.isPossuiArCondicionado() ? "Sim" : "Não" %></td>
            <td><%= s.getNumeroCadeiras() %></td>
            <td><%= s.getTipoMesa() %></td>
            <td><%= s.getRecursosAdicionais() %></td>
            <td>
                <%-- Link para editar a sala --%>
                <a href="salas?action=edit&id=<%= s.getId() %>">Editar</a> |
                <%-- Link para excluir a sala --%>
                <a href="salas?action=delete&id=<%= s.getId() %>">Excluir</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>

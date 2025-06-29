<%@ page contentType="text/html;charset=UTF-8" %> <%-- Define o tipo de conteúdo --%>
<%@ page import="com.exemplo.servlet.SalaReuniao" %> <%-- Importa a classe SalaReuniao para uso no JSP --%>

<%
    // Recupera o objeto 'sala' enviado pelo servlet
    SalaReuniao s = (SalaReuniao) request.getAttribute("sala");
%>

<html>
<head><title>Editar Sala</title></head>
<body>
    <h2>Editar Sala</h2>

    <%-- Formulário para atualizar dados da sala --%>
    <form action="salas?action=update" method="post">
        <%-- Campo oculto para o ID da sala --%>
        <input type="hidden" name="id" value="<%= s.getId() %>">

        Nome: <input type="text" name="nome" value="<%= s.getNome() %>" required><br>
        Capacidade: <input type="number" name="capacidade" value="<%= s.getCapacidade() %>" required><br>
        Localização: <input type="text" name="localizacao" value="<%= s.getLocalizacao() %>" required><br>
        Projetor: <input type="checkbox" name="projetor" <%= s.isPossuiProjetor() ? "checked" : "" %>><br>
        Ar-condicionado: <input type="checkbox" name="ar" <%= s.isPossuiArCondicionado() ? "checked" : "" %>><br>
        Nº de Cadeiras: <input type="number" name="cadeiras" value="<%= s.getNumeroCadeiras() %>" required><br>
        Tipo de Mesa: <input type="text" name="mesa" value="<%= s.getTipoMesa() %>" required><br>
        Recursos Adicionais: <input type="text" name="recursos" value="<%= s.getRecursosAdicionais() %>"><br><br>

        <input type="submit" value="Atualizar">
    </form>

    <%-- Link para voltar à listagem --%>
    <p><a href="salas">Voltar à lista</a></p>
</body>
</html>

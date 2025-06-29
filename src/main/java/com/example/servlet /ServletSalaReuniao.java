package com.exemplo.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet controlador principal do sistema.
 * Gerencia as requisições relacionadas à entidade SalaReuniao
 * e direciona para as páginas JSP ou executa operações no DAO.
 */
@WebServlet("/salas")
public class ServletSalaReuniao extends HttpServlet {
    private SalaReuniaoDAO salaDAO;

    // Inicializa o DAO quando o servlet é carregado
    @Override
    public void init() {
        salaDAO = new SalaReuniaoDAO();
    }

    // Método para tratar requisições POST, redireciona para doGet
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response); // Faz com que POST e GET sejam tratados da mesma forma
    }

    // Método para tratar requisições GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action"); // Pega parâmetro action da URL

        try {
            // Verifica qual ação executar, caso nula, assume 'list'
            switch (action == null ? "list" : action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertSala(request, response);
                    break;
                case "delete":
                    deleteSala(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateSala(request, response);
                    break;
                default:
                    listSalas(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex); // Encapsula erros de SQL
        }
    }

    // Exibe a lista de todas as salas cadastradas
    private void listSalas(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<SalaReuniao> listaSalas = salaDAO.selectAllSalas(); // Pega todas as salas do DAO
        request.setAttribute("listaSalas", listaSalas); // Envia para a JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("listar.jsp");
        dispatcher.forward(request, response);
    }

    // Exibe o formulário para cadastrar nova sala
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrar.jsp");
        dispatcher.forward(request, response);
    }

    // Exibe o formulário de edição com dados da sala selecionada
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); // Pega id da URL
        SalaReuniao sala = salaDAO.selectSala(id); // Busca sala no banco
        request.setAttribute("sala", sala); // Envia sala para a JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("editar.jsp");
        dispatcher.forward(request, response);
    }

    // Cadastra uma nova sala no banco
    private void insertSala(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        SalaReuniao s = new SalaReuniao();

        // Captura os dados enviados pelo formulário e popula o objeto
        s.setNome(request.getParameter("nome"));
        s.setCapacidade(Integer.parseInt(request.getParameter("capacidade")));
        s.setLocalizacao(request.getParameter("localizacao"));
        s.setPossuiProjetor(request.getParameter("projetor") != null);
        s.setPossuiArCondicionado(request.getParameter("ar") != null);
        s.setNumeroCadeiras(Integer.parseInt(request.getParameter("cadeiras")));
        s.setTipoMesa(request.getParameter("mesa"));
        s.setRecursosAdicionais(request.getParameter("recursos"));

        salaDAO.insertSala(s); // Insere a sala no banco
        response.sendRedirect("salas"); // Redireciona para a listagem
    }

    // Atualiza uma sala existente no banco
    private void updateSala(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        SalaReuniao s = new SalaReuniao();

        // Captura os dados do formulário e atualiza o objeto sala
        s.setId(Integer.parseInt(request.getParameter("id")));
        s.setNome(request.getParameter("nome"));
        s.setCapacidade(Integer.parseInt(request.getParameter("capacidade")));
        s.setLocalizacao(request.getParameter("localizacao"));
        s.setPossuiProjetor(request.getParameter("projetor") != null);
        s.setPossuiArCondicionado(request.getParameter("ar") != null);
        s.setNumeroCadeiras(Integer.parseInt(request.getParameter("cadeiras")));
        s.setTipoMesa(request.getParameter("mesa"));
        s.setRecursosAdicionais(request.getParameter("recursos"));

        salaDAO.updateSala(s); // Atualiza os dados no banco
        response.sendRedirect("salas"); // Redireciona para a listagem
    }

    // Exclui uma sala do banco pelo ID
    private void deleteSala(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); // Pega id da URL
        salaDAO.deleteSala(id); // Exclui do banco
        response.sendRedirect("salas"); // Redireciona para a listagem
    }
}

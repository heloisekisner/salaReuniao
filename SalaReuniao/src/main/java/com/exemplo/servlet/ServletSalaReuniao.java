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
    // Adicione esta linha para resolver o aviso de serialVersionUID
    private static final long serialVersionUID = 1L;

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
            // Aqui, é crucial logar a exceção completa para depuração
            ex.printStackTrace(); // Imprime a pilha de chamadas no console do servidor
            throw new ServletException("Erro de banco de dados ao processar requisição.", ex); // Encapsula erros de SQL com uma mensagem mais útil
        } catch (NumberFormatException ex) {
            // Captura erros de formato de número, comuns em campos INT vazios ou inválidos
            ex.printStackTrace();
            // Você pode redirecionar para uma página de erro ou exibir uma mensagem ao usuário
            request.setAttribute("errorMessage", "Erro: Um valor numérico inválido foi fornecido. Por favor, verifique os campos de Capacidade, Nº de Cadeiras, etc.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp"); // Ou para a própria página do formulário
            dispatcher.forward(request, response);
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
        // Tratamento para NumberFormatException caso o 'id' não seja um número válido
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id")); // Pega id da URL
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "ID da sala inválido.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
            return; // Interrompe a execução para evitar mais erros
        }
        
        SalaReuniao sala = salaDAO.selectSala(id); // Busca sala no banco
        request.setAttribute("sala", sala); // Envia sala para a JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("editar.jsp");
        dispatcher.forward(request, response);
    }

    // Cadastra uma nova sala no banco
    private void insertSala(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException { // Adicionado ServletException para propagar erros
        SalaReuniao s = new SalaReuniao();

        // Captura os dados enviados pelo formulário e popula o objeto
        s.setNome(request.getParameter("nome"));
        s.setLocalizacao(request.getParameter("localizacao"));
        s.setPossuiProjetor(request.getParameter("projetor") != null);
        s.setPossuiArCondicionado(request.getParameter("ar") != null);
        s.setTipoMesa(request.getParameter("mesa"));
        s.setRecursosAdicionais(request.getParameter("recursos"));

        try {
            s.setCapacidade(Integer.parseInt(request.getParameter("capacidade")));
            s.setNumeroCadeiras(Integer.parseInt(request.getParameter("cadeiras")));
        } catch (NumberFormatException e) {
            // Captura NumberFormatException aqui para dar feedback mais específico ao usuário
            request.setAttribute("errorMessage", "Os campos 'Capacidade' e 'Nº de Cadeiras' devem ser números válidos.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrar.jsp"); // Redireciona de volta ao formulário de cadastro
            dispatcher.forward(request, response);
            return; // Interrompe a execução
        }
        
        salaDAO.insertSala(s); // Insere a sala no banco
        response.sendRedirect("salas"); // Redireciona para a listagem
    }

    // Atualiza uma sala existente no banco
    private void updateSala(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException { // Adicionado ServletException
        SalaReuniao s = new SalaReuniao();

        // Captura os dados do formulário e atualiza o objeto sala
        try {
            s.setId(Integer.parseInt(request.getParameter("id")));
            s.setCapacidade(Integer.parseInt(request.getParameter("capacidade")));
            s.setNumeroCadeiras(Integer.parseInt(request.getParameter("cadeiras")));
        } catch (NumberFormatException e) {
             request.setAttribute("errorMessage", "ID da sala ou campos numéricos inválidos para atualização.");
             RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp"); // Ou para o formulário de edição
             dispatcher.forward(request, response);
             return;
        }

        s.setNome(request.getParameter("nome"));
        s.setLocalizacao(request.getParameter("localizacao"));
        s.setPossuiProjetor(request.getParameter("projetor") != null);
        s.setPossuiArCondicionado(request.getParameter("ar") != null);
        s.setTipoMesa(request.getParameter("mesa"));
        s.setRecursosAdicionais(request.getParameter("recursos"));

        salaDAO.updateSala(s); // Atualiza os dados no banco
        response.sendRedirect("salas"); // Redireciona para a listagem
    }

    // Exclui uma sala do banco pelo ID
    private void deleteSala(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException { // Adicionado ServletException
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id")); // Pega id da URL
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "ID da sala inválido para exclusão.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("erro.jsp");
            dispatcher.forward(request, response);
            return;
        }

        salaDAO.deleteSala(id); // Exclui do banco
        response.sendRedirect("salas"); // Redireciona para a listagem
    }
}
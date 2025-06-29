package com.exemplo.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável por realizar as operações CRUD no banco de dados
 * para a entidade SalaReuniao.
 */
public class SalaReuniaoDAO {

    // Parâmetros de conexão com o banco
    private String jdbcURL = "jdbc:mysql://localhost:3306/salasb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    // Comandos SQL pré-definidos
    private static final String INSERT = "INSERT INTO SalaReuniao (nome, capacidade, localizacao, possuiProjetor, possuiArCondicionado, numeroCadeiras, tipoMesa, recursosAdicionais) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM SalaReuniao";
    private static final String DELETE = "DELETE FROM SalaReuniao WHERE id=?";
    private static final String SELECT_BY_ID = "SELECT * FROM SalaReuniao WHERE id=?";
    private static final String UPDATE = "UPDATE SalaReuniao SET nome=?, capacidade=?, localizacao=?, possuiProjetor=?, possuiArCondicionado=?, numeroCadeiras=?, tipoMesa=?, recursosAdicionais=? WHERE id=?";

    // Método para estabelecer a conexão com o banco
    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carrega driver JDBC
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e);
        }
    }

    // Método para inserir uma nova sala no banco
    public void insertSala(SalaReuniao sala) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT)) {

            // Preenche os parâmetros na query
            stmt.setString(1, sala.getNome());
            stmt.setInt(2, sala.getCapacidade());
            stmt.setString(3, sala.getLocalizacao());
            stmt.setBoolean(4, sala.isPossuiProjetor());
            stmt.setBoolean(5, sala.isPossuiArCondicionado());
            stmt.setInt(6, sala.getNumeroCadeiras());
            stmt.setString(7, sala.getTipoMesa());
            stmt.setString(8, sala.getRecursosAdicionais());

            // Executa a inserção
            stmt.executeUpdate();
        }
    }

    // Retorna a lista de todas as salas cadastradas
    public List<SalaReuniao> selectAllSalas() throws SQLException {
        List<SalaReuniao> salas = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = stmt.executeQuery()) {

            // Itera sobre o ResultSet e adiciona cada sala na lista
            while (rs.next()) {
                SalaReuniao sala = new SalaReuniao();
                sala.setId(rs.getInt("id"));
                sala.setNome(rs.getString("nome"));
                sala.setCapacidade(rs.getInt("capacidade"));
                sala.setLocalizacao(rs.getString("localizacao"));
                sala.setPossuiProjetor(rs.getBoolean("possuiProjetor"));
                sala.setPossuiArCondicionado(rs.getBoolean("possuiArCondicionado"));
                sala.setNumeroCadeiras(rs.getInt("numeroCadeiras"));
                sala.setTipoMesa(rs.getString("tipoMesa"));
                sala.setRecursosAdicionais(rs.getString("recursosAdicionais"));
                salas.add(sala);
            }
        }
        return salas;
    }

    // Retorna uma sala específica pelo ID
    public SalaReuniao selectSala(int id) throws SQLException {
        SalaReuniao sala = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                sala = new SalaReuniao();
                sala.setId(rs.getInt("id"));
                sala.setNome(rs.getString("nome"));
                sala.setCapacidade(rs.getInt("capacidade"));
                sala.setLocalizacao(rs.getString("localizacao"));
                sala.setPossuiProjetor(rs.getBoolean("possuiProjetor"));
                sala.setPossuiArCondicionado(rs.getBoolean("possuiArCondicionado"));
                sala.setNumeroCadeiras(rs.getInt("numeroCadeiras"));
                sala.setTipoMesa(rs.getString("tipoMesa"));
                sala.setRecursosAdicionais(rs.getString("recursosAdicionais"));
            }
        }
        return sala;
    }

    // Atualiza os dados de uma sala no banco
    public void updateSala(SalaReuniao sala) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE)) {

            // Seta os parâmetros na query de update
            stmt.setString(1, sala.getNome());
            stmt.setInt(2, sala.getCapacidade());
            stmt.setString(3, sala.getLocalizacao());
            stmt.setBoolean(4, sala.isPossuiProjetor());
            stmt.setBoolean(5, sala.isPossuiArCondicionado());
            stmt.setInt(6, sala.getNumeroCadeiras());
            stmt.setString(7, sala.getTipoMesa());
            stmt.setString(8, sala.getRecursosAdicionais());
            stmt.setInt(9, sala.getId());

            // Executa a atualização
            stmt.executeUpdate();
        }
    }

    // Remove uma sala do banco pelo ID
    public void deleteSala(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}

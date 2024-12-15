package acesso_aos_dados;

import negocio.Conta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConexaoBanco {
    private static final String URL = "jdbc:postgresql://localhost:5432/meubanco";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "senha";

    public Connection conectar() throws SQLException {
        try {
            // Tentar carregar o driver explicitamente
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado", e);
        }
    }

    public void salvarConta(Conta conta) {
        String sql = "INSERT INTO conta (numero, saldo) VALUES (?, ?)";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, conta.getNumero());
            stmt.setDouble(2, conta.getSaldo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarSaldo(Conta conta) {
        String sql = "UPDATE conta SET saldo = ? WHERE numero = ?";
        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, conta.getSaldo());
            stmt.setString(2, conta.getNumero());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

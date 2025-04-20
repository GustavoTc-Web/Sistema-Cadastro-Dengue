import java.sql.*;

public class Conexao {

    public static String buscarCasosPorRegiao(String regiao) {
        String url = "jdbc:mysql://localhost:3306/aps";
        String usuario = "root";
        String senha = "mysql";

        String query = "SELECT casos FROM doencas WHERE regiao = ?";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, regiao);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return String.valueOf(rs.getInt("casos"));
            } else {
                return "Nenhum dado encontrado para a região";
            }

        } catch (SQLException e) {
            return "Erro de conexão: " + e.getMessage();
        }
    }

    public static String buscarObitosPorRegiao(String regiao) {
        String url = "jdbc:mysql://localhost:3306/aps";
        String usuario = "root";
        String senha = "mysql";

        String query = "SELECT obitos FROM doencas WHERE regiao = ?";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha)) {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, regiao);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return String.valueOf(rs.getInt("obitos"));
            } else {
                return "Nenhum dado encontrado para a região";
            }

        } catch (SQLException e) {
            return "Erro de conexão: " + e.getMessage();
        }
    }
}

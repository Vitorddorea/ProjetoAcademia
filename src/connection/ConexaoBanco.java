package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String URL_POSTGRES =
            "jdbc:postgresql://localhost:5432/academiaDB";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "root";

    public static Connection conectar() {
        try {
            Connection con = DriverManager.getConnection(
                    URL_POSTGRES,
                    USUARIO,
                    SENHA
            );
            
            return con;

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Erro ao conectar ao banco de dados: " + e.getMessage(),
                    e
            );
        }
    }
}
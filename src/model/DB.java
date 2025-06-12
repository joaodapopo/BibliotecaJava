package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca"; // nome do seu banco
    private static final String USER = "root"; // seu usuário do MySQL
    private static final String PASS = "2009";     // sua senha do MySQL (coloque se tiver)

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

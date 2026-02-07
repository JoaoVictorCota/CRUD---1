package br.com.joaocota;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/produtosdb";
    private static final String USUARIO = "root";
    private static final String SENHA = "Poli2302@";

    public static Connection obterConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}


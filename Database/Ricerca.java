package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ricerca {


    static public String ricercaCorpo( long idChat){
        String nomeCorpo = "";

        Connection connection = Database.connect();
        String sqlNomeCorpo = "SELECT NOME FROM CORPO WHERE ID = " + idChat + ";";
        System.out.println(idChat);
        // RICERCA ID
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlNomeCorpo);
            nomeCorpo = rs.getString("NOME");
        } catch (SQLException e) {
            System.err.println(e);
        }
        Database.closeConnection(connection);
        return nomeCorpo;
    }
}

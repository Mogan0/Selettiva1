package Database;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import javax.xml.crypto.Data;
import java.sql.*;

public class Database {
    static private long amministratore = 22416164;

    /** RICORDARMI DI CAMBIARE */
    //static private String url_database = "jdbc:sqlite:D:\\Desktop\\Mogano\\Bot\\SelettivaBotv2\\Selettiva\\Database\\prova.db";

    static private String url_database = "jdbc:sqlite:/home/ProgrammaSelettiva/prova.db";

    // CONNESSIONE DATABASE User.db
    static private Connection conn = null; // CONNESSIONE

    public static Connection connect()  {
        try {
            conn = DriverManager.getConnection(url_database);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException ex) {
            SendMessage messageERROR = new SendMessage().setChatId(amministratore).setText(ex.getMessage());
            System.out.println(messageERROR);
        }
        return conn;
    }

    public static void closeConnection(Connection conn){
        try {
            if (conn!=null)
                conn.close();
        } catch (SQLException ex){

            System.out.println(ex.getMessage());
        }
        System.out.println("Connection to SQLite has been close.");
    }

    public static void executeQuery (String query) {
        Connection conn = Database.connect();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery(query);
        } catch (SQLException e ) {
            System.err.println(e);
        }
        Database.closeConnection(conn);
    }

    public static String resultSetGrado(long idUtente){
        String query = "SELECT GRADO FROM VIGILI WHERE ID = " + idUtente +";";
        Connection conn = Database.connect();
        String grado = "";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            grado = rs.getString("GRADO");
        } catch (SQLException e ) {
            System.err.println(e);
        }
        Database.closeConnection(conn);
        return grado;
    }
}

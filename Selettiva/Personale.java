package Selettiva;

import Database.Database;
import Utente.Utente;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Personale {

    private static ArrayList<Object> list;


    static public void Personale (CallbackQuery callbackQuery) {

        String id = callbackQuery.getId();

        // Funzione lista
        String nome = "" ;
        String grado = "";
         list = new ArrayList<>();
        Connection conn = Database.connect();
        String query = "SELECT nome, grado FROM VIGILI WHERE ARRIVO = " + 1 + ";";
        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                nome = rs.getString("nome");
                list.add(nome);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        Database.closeConnection(conn);
        AnswerCallbackQuery message = new AnswerCallbackQuery().setText("Personale in ARRIVO:\n\n" + list ).setCallbackQueryId(id).setShowAlert(true);
        Utente.executeMessage(message);
        }


    }



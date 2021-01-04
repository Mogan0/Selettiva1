package Selettiva;

import Database.Database;
import Keyboard.Keyboard;
import Utente.Utente;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Arrivo {
  // static Gui gui = new Gui();

    static public void arrivoExecute (CallbackQuery callbackQuery) {
        long idUtente = callbackQuery.getFrom().getId();
        long idChat = callbackQuery.getMessage().getChatId();
        String id = callbackQuery.getId();

        String sqlQueryArrivo = "UPDATE VIGILI SET ARRIVO = " + 1 + ", NON_ARRIVO = " + 0 + " WHERE ID = " + idUtente + ";";
        AnswerCallbackQuery message = new AnswerCallbackQuery().setText("Pulsante ARRIVO schiacciato").setCallbackQueryId(id).setShowAlert(false) ;
        Database.executeQuery(sqlQueryArrivo);
        Utente.executeMessage(message);
        EditMessageText editMessageText = new EditMessageText().setChatId(idChat).setMessageId(Utente.idMessage(idChat));

        editMessageText.setText("\uD83D\uDC69\uD83C\uDFFC\u200D\uD83D\uDE92  *" + Arrivo.getVigili() + "*\n\n" +
                "\uD83D\uDE92  *" + Arrivo.getAutisti() + "*\n\n" +
                "⭐️  *" + Arrivo.getGraduato() + "*\n\n" +
                "\uD83D\uDD14 totale *"  + Arrivo.getTotal() + "*" );

        editMessageText.enableMarkdown(true);
        Keyboard.inlineKeyboardMainMessageSelettiva(editMessageText);
        Utente.executeMessage(editMessageText);
      //  gui.arrivo();

    }

    public static int getVigili(){
        String query = "SELECT ARRIVO FROM VIGILI WHERE ARRIVO = "+ 1 +" AND GRADO = 'vigile' ;";
        Connection conn = Database.connect();
        int arrivo = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                arrivo = arrivo+1;
            }
        } catch (SQLException e ) {
            System.err.println(e);
        }
        Database.closeConnection(conn);
        return arrivo;
    }

    public static int getAutisti(){
        String query = "SELECT ARRIVO FROM VIGILI WHERE ARRIVO = "+ 1 +" AND GRADO = 'autista';";
        Connection conn = Database.connect();
        int arrivo = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                arrivo = arrivo+1;
            }
        } catch (SQLException e ) {
            System.err.println(e);
        }
        Database.closeConnection(conn);
        return arrivo;
    }

    public static int getGraduato(){
        String query = "SELECT ARRIVO FROM VIGILI WHERE ARRIVO = "+ 1 +" AND GRADO = 'graduato';";
        Connection conn = Database.connect();
        int arrivo = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                arrivo = arrivo+1;
            }
        } catch (SQLException e ) {
            System.err.println(e);
        }
        Database.closeConnection(conn);
        return arrivo;
    }

    public static int getTotal(){
        String query = "SELECT ARRIVO FROM VIGILI WHERE ARRIVO = "+ 1 +";";
        Connection conn = Database.connect();
        int arrivo = 0;
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                arrivo = arrivo+1;
            }
        } catch (SQLException e ) {
            System.err.println(e);
        }
        Database.closeConnection(conn);
        return arrivo;
    }
}
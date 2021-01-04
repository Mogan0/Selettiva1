package Selettiva;

import Database.Database;
import Keyboard.Keyboard;
import Utente.Utente;
import org.telegram.telegrambots.meta.api.methods.pinnedmessages.PinChatMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Selettiva {

    static public void mainMessageSelettiva (long chatId) {
        SendMessage sendMessage = new SendMessage().setChatId(chatId) ;
        sendMessage.setText("\uD83D\uDC69\uD83C\uDFFC\u200D\uD83D\uDE92  *" + Arrivo.getVigili() + "*\n\n" +
                "\uD83D\uDE92  *" + Arrivo.getAutisti() + "*\n\n" +
                "⭐️  *" + Arrivo.getGraduato() + "*\n\n" +
                "\uD83D\uDD14 totale *"  + Arrivo.getTotal() + "*" );
        sendMessage.enableMarkdown(true);
        Keyboard.inlineKeyboardMainMessageSelettiva(sendMessage);
        Utente.executeMessage(sendMessage);
    }

    static public void resetDati(long chatId){
        Connection connection = Database.connect();
        String sqlNomeCorpo = "UPDATE VIGILI SET arrivo = " + 0 + " where arrivo = " + 1 + ";";

        // RICERCA ID
        try {
            Statement stmt = connection.createStatement();
            stmt.executeQuery(sqlNomeCorpo);
        } catch (SQLException e) {
            System.err.println(e);
        }
        Database.closeConnection(connection);
    }




}

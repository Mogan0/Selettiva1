package Utente;

import Database.Database;
import SelettivaBot.SelettivaBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.pinnedmessages.PinChatMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utente {

    public static void executeMessage (AnswerCallbackQuery answerCallbackQuery ) {
        SelettivaBot selettivaBot = new SelettivaBot();
        try {
            selettivaBot.execute(answerCallbackQuery);
        } catch (TelegramApiException e ) {
            System.err.println(e);
        }
    }

    public static void executeMessage (SendMessage sendMessage ) {
        SelettivaBot selettivaBot = new SelettivaBot();
        int messageId = 0 ;
        String chatID = sendMessage.getChatId();
        try {
            messageId = selettivaBot.execute(sendMessage).getMessageId();
        } catch (TelegramApiException e ) {
            System.err.println(e);
        }
        Connection conn = Database.connect();
        // SAVE ID MESSAGE FOR EDIT
        String queryID = "UPDATE CORPO SET ID_MESSAGE = " + messageId + " WHERE ID = " + sendMessage.getChatId() + " ;" ;
        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery(queryID);
        } catch (SQLException e) {
            System.err.println(e);
        }
        Database.closeConnection(conn);
        // PIN MESSAGE
        PinChatMessage pinChatMessage = new PinChatMessage().setChatId(chatID).setMessageId(messageId);
        try {
           selettivaBot.execute(pinChatMessage);
        } catch (TelegramApiException e ) {
            System.err.println(e);
        }
        // DELETE PIN MESSAGE
        DeleteMessage deleteMessage = new DeleteMessage().setMessageId(messageId+1).setChatId(chatID);
        try {
            selettivaBot.execute(deleteMessage);
        } catch (TelegramApiException e ) {
            System.err.println(e);
        }
    }

    public static void executeMessage (EditMessageText editMessageText ) {
        SelettivaBot selettivaBot = new SelettivaBot();
        try {
            selettivaBot.execute(editMessageText);
        } catch (TelegramApiException e ) {
            System.err.println(e);
        }
    }

    public static int idMessage(long idChat){
        Connection connection = Database.connect();
        String query = "SELECT ID_MESSAGE FROM CORPO WHERE ID = " + idChat + ";";
        int id_message = 0;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            id_message = rs.getInt("ID_MESSAGE");
        } catch (SQLException e) {
            System.err.println(e);
        }
        Database.closeConnection(connection);
        return id_message;
    }

}
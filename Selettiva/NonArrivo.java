package Selettiva;

import Database.Database;
import Keyboard.Keyboard;
import Utente.Utente;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public class NonArrivo {

    static public void nonArrivoExecute (CallbackQuery callbackQuery) {
        long idUtente = callbackQuery.getFrom().getId();
        long idChat = callbackQuery.getMessage().getChatId();
        String id = callbackQuery.getId();

        String sqlQueryArrivo = "UPDATE VIGILI SET ARRIVO = " + 0 + ", NON_ARRIVO = " + 1 + " WHERE ID = " + idUtente + ";";
        AnswerCallbackQuery message = new AnswerCallbackQuery().setText("Pulsante NON ARRIVO schiacciato").setCallbackQueryId(id).setShowAlert(false) ;
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

    }
}

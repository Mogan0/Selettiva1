package Keyboard;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class Keyboard {

    static public SendMessage inlineKeyboardMainMessageSelettiva (SendMessage sendMessage) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> linea1 = new ArrayList<>();
        List<InlineKeyboardButton> linea2 = new ArrayList<>();
        List<InlineKeyboardButton> linea3 = new ArrayList<>();
        List<InlineKeyboardButton> linea4 = new ArrayList<>();

        linea1.add(new InlineKeyboardButton().setText("✅ Arrivo").setCallbackData("ARRIVO") ); // LONG TO STRING ID
        linea1.add(new InlineKeyboardButton().setText("⛔️ Non arrivo").setCallbackData("NONARRIVO"));
        linea3.add(new InlineKeyboardButton().setText("Personale in arrivo").setCallbackData("PERSONALE"));


        rowsInline.add(linea1);
        rowsInline.add(linea2);
        rowsInline.add(linea3);

        markupInline.setKeyboard(rowsInline);
        sendMessage.setReplyMarkup(markupInline);
        return sendMessage;
    }

    static public EditMessageText inlineKeyboardMainMessageSelettiva (EditMessageText editMessageText) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> linea1 = new ArrayList<>();
        List<InlineKeyboardButton> linea2 = new ArrayList<>();
        List<InlineKeyboardButton> linea3 = new ArrayList<>();
        List<InlineKeyboardButton> linea4 = new ArrayList<>();

        linea1.add(new InlineKeyboardButton().setText("✅ Arrivo").setCallbackData("ARRIVO") ); // LONG TO STRING ID
        linea1.add(new InlineKeyboardButton().setText("⛔️ Non arrivo").setCallbackData("NONARRIVO"));
        linea3.add(new InlineKeyboardButton().setText("Personale in arrivo").setCallbackData("PERSONALE"));


        rowsInline.add(linea1);
        rowsInline.add(linea2);
        rowsInline.add(linea3);

        markupInline.setKeyboard(rowsInline);
        editMessageText.setReplyMarkup(markupInline);
        return editMessageText;
    }
}

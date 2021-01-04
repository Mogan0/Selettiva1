package SelettivaBot;

import Database.Ricerca;
import Selettiva.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.net.URI;
import java.util.Date;


public class SelettivaBot extends TelegramLongPollingBot {

    private long idAmministratore = 22416164 ;

    /** GRUPPO SELETTIVE ROVERETO **/
    private long chatSelettiveRovereto = Long.parseLong("-1001321943362");
    /** GRUPPO SELETTIVE MAGAZZINO **/
    private  long idMagazzino = 66519897 ;


    // ------------UPDATE-----------
    public void onUpdateReceived(Update update) {


        /** SE IL MESSAGGIO E' IL TESTO **/
        if (update.hasMessage() && update.getMessage().hasText()) {
            // recupero dati
            long idUtente = update.getMessage().getFrom().getId();
            //System.out.println(idUtente);
            long idChat = update.getMessage().getChatId();
            String textMessage = update.getMessage().getText();
            //System.out.println(idChat);

            // if per verificare la selettiva
            /** CAMBIARE IDCHA */
            if ((idUtente == idMagazzino || idUtente == idAmministratore)) {
                if (textMessage.equals("Selettiva per " + Ricerca.ricercaCorpo(idChat))) {
                    Selettiva.resetDati(idChat);
                    Selettiva.mainMessageSelettiva(idChat);
                    System.out.println("mainMessageSelettiva" + idChat);
                    URI uri = null;

                } else {
                    switch (textMessage) {
                        case "/status":
                            // ORA
                            new Date();
                            Date dat = new Date();
                            int hh = dat.getHours();
                            int mn = dat.getMinutes();
                            int ss = dat.getSeconds();
                            String ora_corr = hh+1 + ":" + mn + "." + ss;
                            SendMessage message = new SendMessage();
                            message.setText("Online -- " + "ora : " + ora_corr);
                            message.setChatId(idAmministratore);
                            //ps aux | grep SelettivaBot.jar
                            try {
                                execute(message);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            } break ;

                        case "/stop":
                            SendMessage messageExit = new SendMessage().setText("CHIUSURA PROGRAMMA IN CORSO").setChatId(idAmministratore);
                            try {
                                execute(messageExit);
                            } catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                            System.exit(0);
                            break;
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {

                    String callBackQueryData = update.getCallbackQuery().getData();
                    switch (callBackQueryData) {
                        case "ARRIVO":
                            Arrivo.arrivoExecute(update.getCallbackQuery());
                            break;
                        case "NONARRIVO":
                            NonArrivo.nonArrivoExecute(update.getCallbackQuery());
                            break;
                        case "PERSONALE":
                            Personale.Personale(update.getCallbackQuery());
                            break;
                    }
                }
            }





    /** TEST **/
/*
    public String getBotUsername() {
        return "MoganoTest_bot";
    }

    // TOKEN
    public String getBotToken() {
        return "981086877:AAFBWOBb7lpbXXszL3R-QXPvJ4wyNxfo6LI";
    }


    /** DATI DI RIFERIMENTO BOT **/
    public String getBotUsername() {
        return "selettivabot";
    }
    public String getBotToken() {
        return "582433746:AAEcfXlt9uIU38oPIzW7uA2G-S2sH9dpGWI";
    }




}

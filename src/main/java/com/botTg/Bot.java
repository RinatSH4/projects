package com.botTg;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Bot extends TelegramLongPollingBot {

    private final String BOT_NAME = "RinatSHTest_bot";
    private final String BOT_TOKEN = "6363834152:AAHIdwh899cNKQC1h9UJzprOva8v3UhiP0M";

    private Horoscope horoscope;
    private ReplyKeyboardMarkup replyKeyboardMarkup;
    private Date date;

    Bot() {
        horoscope = new Horoscope();
        initKeyboard();
        date = new Date();
    }
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {

            //Извлекаем из объекта сообщение пользователя
            Message inMess = update.getMessage();

            //Достаем из inMess id чата пользователя
            String chatId = inMess.getChatId().toString();
            System.out.println("ID чата: " + chatId);

            //Получаем текст сообщения пользователя, отправляем
            // в написанный нами обработчик
            String responce = parseMessage(inMess.getText(), chatId);
            System.out.println("Текст пользователя: " + inMess.getText());

            //Создаем объект класса SendMessage
            // - наш будущий ответ пользователю
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(responce);
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            System.out.println("Ответ пользователю: " + responce);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String parseMessage(String textMsg, String chatId) {
        String responce;
        if(textMsg.equals("/start") || textMsg.equals("Перезапустить бота")) {
            responce = "";
            sendPhoto(chatId);
        }
        else if (textMsg.contains("Водолей"))
            responce = horoscope.parseURL("https://besthoro.ru/vodolej/tochnyj-goroskop-na-segodnya-vodolej.html");
        else if (textMsg.contains("Рыбы"))
            responce = horoscope.parseURL("https://besthoro.ru/ryby/tochnyj-goroskop-na-segodnya-ryby.html");
        else if (textMsg.contains("Овен"))
            responce = horoscope.parseURL("https://besthoro.ru/oven/tochnyj-goroskop-na-segodnya-oven.html");
        else if (textMsg.contains("Телец"))
            responce = horoscope.parseURL("https://besthoro.ru/telec/tochnyj-goroskop-na-segodnya-telec.html");
        else if (textMsg.contains("Близнецы"))
            responce = horoscope.parseURL("https://besthoro.ru/bliznecy/tochnyj-goroskop-na-segodnya-bliznecy.html");
        else if (textMsg.contains("Рак"))
            responce = horoscope.parseURL("https://besthoro.ru/rak/tochnyj-goroskop-na-segodnya-rak.html");
        else if (textMsg.contains("Лев"))
            responce = horoscope.parseURL("https://besthoro.ru/lev/tochnyj-goroskop-na-segodnya-lev.html");
        else if (textMsg.contains("Дева"))
            responce = horoscope.parseURL("https://besthoro.ru/deva/tochnyj-goroskop-na-segodnya-deva.html");
        else if (textMsg.contains("Весы"))
            responce = horoscope.parseURL("https://besthoro.ru/vesy/tochnyj-goroskop-na-segodnya-vesy.html");
        else if (textMsg.contains("Скорпион"))
            responce = horoscope.parseURL("https://besthoro.ru/skorpion/tochnyj-goroskop-na-segodnya-skorpion.html");
        else if (textMsg.contains("Стрелец"))
            responce = horoscope.parseURL("https://besthoro.ru/strelec/tochnyj-goroskop-na-segodnya-strelec.html");
        else if (textMsg.contains("Козерог"))
            responce = horoscope.parseURL("https://besthoro.ru/kozerog/tochnyj-goroskop-na-segodnya-kozerog.html");
        else
            responce = "Неизвестная команда";

        return responce;
    }


    void initKeyboard() {
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(true); //скрываем после использования

        //создаем список с рядами кнопок
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();

        //Создаем один ряд кнопок и добавляем его в список
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardRow keyboardRow1 = new KeyboardRow();
        KeyboardRow keyboardRow2 = new KeyboardRow();
        KeyboardRow keyboardRow3 = new KeyboardRow();

        keyboardRows.add(keyboardRow);
        //Добавляем кнопки с текстом
        keyboardRow.add(new KeyboardButton("Водолей ♒"));
        keyboardRow.add(new KeyboardButton("Рыбы ♓"));
        keyboardRow.add(new KeyboardButton("Овен ♈"));
        keyboardRow.add(new KeyboardButton("Телец ♉"));

        keyboardRows.add(keyboardRow1);
        keyboardRow1.add(new KeyboardButton("Близнецы ♊"));
        keyboardRow1.add(new KeyboardButton("Рак ♋"));
        keyboardRow1.add(new KeyboardButton("Лев ♌"));
        keyboardRow1.add(new KeyboardButton("Дева ♍"));

        keyboardRows.add(keyboardRow2);
        keyboardRow2.add(new KeyboardButton("Весы ♎"));
        keyboardRow2.add(new KeyboardButton("Скорпион ♏"));
        keyboardRow2.add(new KeyboardButton("Стрелец ♐"));
        keyboardRow2.add(new KeyboardButton("Козерог ♑"));

        keyboardRows.add(keyboardRow3);
        keyboardRow3.add(new KeyboardButton("Перезапустить бота"));

        //добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

    static String getTemperature() {
        try {
            String url = "https://www.gismeteo.ru/weather-engels-5034/?ysclid=lptjhk4si3325579449";
            Document doc = Jsoup.connect(url).get();
            Elements element = doc.getElementsByClass("weather-value");
            Elements element1 = doc.getElementsByClass("measure");
            String e1 = element.text().substring(0, element.text().indexOf(' ')+1);
            String e2 = element1.text().substring(0, element.text().indexOf(' ')+1);;
            String temperature = "\nПогода в Энгельсе: " + e1 + "\nпо ощущению: " + e2;
            return temperature;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void sendPhoto(String chatId) {
        SendPhoto send = new SendPhoto();
        send.setChatId(chatId);
        send.setPhoto(new InputFile("https://u.9111s.ru/uploads/202103/25/e2f07c9a3fa64f1c96c0b79716f1e4db.jpg"));
        send.setCaption("Это гороскоп на каждый день, " +
                "жми на свой знак и узнавай что тебя ждет сегодня! \nСегодня: " + date +
                "\n\n" + getTemperature());
        try {
            execute(send);
        } catch (TelegramApiException e) {
            System.out.println(e);
        }
    }
}

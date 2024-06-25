package com.app.service;

import com.app.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class BotLogic extends TelegramLongPollingBot {
    private final BotConfig config;

    public BotLogic(BotConfig config) {
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();
            int messageId = update.getMessage().getMessageId();
            String message = update.getMessage().getText();

            switch(message) {
                case "/start":
                    welcomeMessage(chatId);
                    break;
                case "/yandex_music":
                    System.out.println("Yandex Music was enabled");
                    break;
                case "/spotify":
                    System.out.println("Spotify was enabled");
                    break;
                case "/apple_music":
                    System.out.println("Apple Music was enabled");
                    break;
                case "/youtube":
                    System.out.println("YouTube was enabled");
                    break;
                default:
                    break;
            }
        }
    }

    private void welcomeMessage(long chatId) {
        final String welcome = """
                Приветствую тебя, пользователь!
                Я - сервис, которому ты можешь отправить ссылку на трек в стриминговом сервисе и получить ссылки на этот трек во всех других стриминговых сервисах
                Ознакомься с меню ниже для начала использования
                Комфортной тебе работы!""";
        sendMessage(chatId, welcome);
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

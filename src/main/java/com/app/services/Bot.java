package com.app.services;

import com.app.config.BotConfig;
import com.app.enums.GetTrackLinksMethod;
import com.app.enums.StreamServiceType;
import com.app.exceptions.TrackNotFoundException;
import com.app.factories.StreamServiceAPIFactory;
import com.app.services.interfaces.validators.Validator;
import com.app.utils.TypeOfLinkUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private DataBase dataBase;

    @Autowired
    private StreamServiceAPIManager apiManager;

    @Autowired
    private Validator validator;

    @Autowired
    private StreamServiceAPIFactory streamServiceFactory;

    private final BotConfig config;

    private boolean streamLinkAcceptFlag;

    public Bot(BotConfig config) {
        this.config = config;
        this.streamLinkAcceptFlag = false;
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
        String welcomeMessage = """
                Приветствую тебя, пользователь!
                Я - сервис, которому ты можешь отправить ссылку на трек в стриминговом сервисе и получить ссылки на этот трек во всех других стриминговых сервисах
                Ознакомься с меню ниже для начала использования
                Комфортной тебе работы!""",
                linkWelcomeMessage = "Отправьте мне ссылку на песню:",
                errorMessage = "Ой, что-то пошло не так, кажется :/",
                backToMenuMessage = "Вы ввели некорректную ссылку, возвращаю Вас в главное меню подумать над своим поведением! >:|";

        if (update.hasMessage()) {
            long chatId = update.getMessage().getChatId();
            String message = update.getMessage().getText();

            if (streamLinkAcceptFlag) {
                if (validator.isValid(message)) {
                    try {
                        StreamServiceType linkType = TypeOfLinkUtility.getTypeOfLink(message);

                        String trackName = getTrackNameByLink(message, linkType),
                        artistName = getArtistNameByLink(message, linkType);
                        List<String> listOfLinks = getLinks(trackName, artistName,
                                insertGetMethod(trackName, artistName));

                        sendMessage(chatId, createResultMessage(listOfLinks));
                    } catch (TrackNotFoundException e) {
                        sendMessage(chatId, errorMessage);
                        e.printStackTrace();
                    }
                } else sendMessage(chatId, backToMenuMessage);
                streamLinkAcceptFlag = false;
            } else {
                switch (message) {
                    case "/start":
                        sendMessage(chatId, welcomeMessage);
                        break;
                    case "/link":
                        streamLinkAcceptFlag = true;
                        sendMessage(chatId, linkWelcomeMessage);
                        break;
                    default:
                        sendMessage(chatId, errorMessage);
                        break;
                }
            }
        }
    }

    /**
     * Проверка валидности ссылки
     * HTTP запрос по ссылке
     * Получение названия песни и имени исполнителя
     * Проверка базы данных на наличие ссылок:
     * 1. Строка по песне в БД существует
     *      Возвращение названия песни и всех ссылок из БД
     * 2. Строка по песне в БД не существует
     *      HTTP запросы ко всем сервисам и запись всех ссылок в БД
     *      Возвращение названия песни и всех ссылок из БД
     */

    private String getTrackNameByLink(String validLink, StreamServiceType linkType) throws TrackNotFoundException {
        try {
            return streamServiceFactory.getMusicService(linkType).getTrackName(validLink);
        } catch (NoSuchElementException e) {
            throw new TrackNotFoundException(e);
        }
    }
    private String getArtistNameByLink(String validLink, StreamServiceType typeLink) throws TrackNotFoundException {
        try {
            return streamServiceFactory.getMusicService(typeLink).getArtistName(validLink);
        } catch (NoSuchElementException e) {
            throw new TrackNotFoundException(e);
        }
    }

    private List<String> getLinks(String trackName, String artistName, GetTrackLinksMethod getMethod) {
        return switch (getMethod) {
            case GET_TRACK_LINKS_FROM_DATABASE -> dataBase.getLinks(trackName, artistName);
            case GET_TRACK_LINKS_FROM_API -> apiManager.getLinks(trackName, artistName);
        };
    }

    private GetTrackLinksMethod insertGetMethod(String trackName, String artistName) {
        try {
            return dataBase.containsTrack(trackName, artistName) ?
                    GetTrackLinksMethod.GET_TRACK_LINKS_FROM_DATABASE :
                    GetTrackLinksMethod.GET_TRACK_LINKS_FROM_API;
        } catch (TrackNotFoundException e) {
            return GetTrackLinksMethod.GET_TRACK_LINKS_FROM_API;
        }
    }

    private String createResultMessage(List<String> links) {
        String errorMessage = "К сожалению, я ничего не смог найти на других платформах, извините Т-Т";
        StringBuilder resultMessage = new StringBuilder("Вот ваши ссылки!\n");

        if (links.isEmpty()) return errorMessage;
        else {
            for (String link : links)
                resultMessage.append(link).append('\n');
            return resultMessage.toString();
        }
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

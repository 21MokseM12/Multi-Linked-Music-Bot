package com.app.utils;

import com.app.enums.StreamServiceType;

public final class LinkValidator {

    private LinkValidator() {}

    public static boolean isValidLink(String link, StreamServiceType linkType) {
        return switch(linkType) {
            case YANDEX_MUSIC_LINK -> isValidYandexMusicLink(link);
            case APPLE_MUSIC_LINK -> isValidAppleMusicLink(link);
            case YOUTUBE_MUSIC_LINK -> isValidYouTubeMusicLink(link);
            case INVALID_LINK -> false;
        };
    }

    private static boolean isValidYandexMusicLink(String link) {
        return false;
    }

    private static boolean isValidAppleMusicLink(String link) {
        return false;
    }

    private static boolean isValidYouTubeMusicLink(String link) {
        return false;
    }
}

package com.app.util;

import com.app.enums.StreamServiceType;

public class TypeOfLinkUtility {
    private TypeOfLinkUtility() {}

    public static StreamServiceType getTypeOfLink(String validLink) {
        validLink = validLink.replace("//", "/");
        String[] splittedLink = validLink.split("/");
        return switch (splittedLink[1]) {
            case "music.yandex.ru" -> StreamServiceType.YANDEX_MUSIC_LINK;
            case "music.apple.com" -> StreamServiceType.APPLE_MUSIC_LINK;
            case "www.youtube.com" -> StreamServiceType.YOUTUBE_LINK;
            default -> StreamServiceType.NOT_LINK;
        };
    }
}

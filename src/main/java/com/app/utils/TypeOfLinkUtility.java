package com.app.utils;

import com.app.enums.StreamServiceType;

public final class TypeOfLinkUtility {
    private TypeOfLinkUtility() {}

    public static StreamServiceType getTypeOfLink(String link) {
        String[] splittedLink = link.replace("//", "/").replace("?", "/").split("/");
        if (splittedLink.length < 2) return StreamServiceType.INVALID_LINK;

        return switch (splittedLink[1]) {
            case "music.yandex.ru" -> StreamServiceType.YANDEX_MUSIC_LINK;
            case "music.apple.com" -> StreamServiceType.APPLE_MUSIC_LINK;
            case "www.youtube.com" -> StreamServiceType.YOUTUBE_MUSIC_LINK;
            default -> StreamServiceType.INVALID_LINK;
        };
    }
}

package com.app.utils;

import com.app.enums.StreamServiceType;

public final class TypeOfLinkUtility {
    private TypeOfLinkUtility() {}

    public static StreamServiceType getTypeOfLink(String link) {
        String[] splittedLink = link.replace("//", "/").replace("?", "/").split("/");
        if (splittedLink.length < 2) return StreamServiceType.INVALID_LINK;

        return switch (splittedLink[1]) {
            case "open.spotify.com" -> StreamServiceType.SPOTIFY_MUSIC_LINK;
            case "music.yandex.ru" -> StreamServiceType.YANDEX_MUSIC_LINK;
            case "music.apple.com" -> StreamServiceType.APPLE_MUSIC_LINK;
            case "music.youtube.com" -> StreamServiceType.YOUTUBE_MUSIC_LINK;
            case "soundcloud.com" -> StreamServiceType.SOUNDCLOUD_MUSIC_LINK;
            case "www.shazam.com" -> StreamServiceType.SHAZAM_MUSIC_LINK;
            default -> StreamServiceType.INVALID_LINK;
        };
    }
}

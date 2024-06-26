package com.app.utils;

import com.app.enums.StreamServiceType;

public final class LinkValidator {

    private LinkValidator() {}

    public static boolean isValidLink(String link, StreamServiceType linkType) {
        return switch(linkType) {
            case YANDEX_MUSIC_LINK -> isValidYandexMusicLink(link);
            case SPOTIFY_MUSIC_LINK -> isValidSpotifyMusicLink(link);
            case APPLE_MUSIC_LINK -> isValidAppleMusicLink(link);
            case YOUTUBE_MUSIC_LINK -> isValidYouTubeMusicLink(link);
            case GOOGLE_PLAY_MUSIC_LINK -> isValidGooglePlayMusicLink(link);
            case SOUNDCLOUD_MUSIC_LINK -> isValidSoundCloudMusicLink(link);
            case SHAZAM_MUSIC_LINK -> isValidShazamMusicLink(link);
            case INVALID_LINK -> false;
        };
    }

    private static boolean isValidYandexMusicLink(String link) {
        return false;
    }

    private static boolean isValidSpotifyMusicLink(String link) {
        return false;
    }

    private static boolean isValidAppleMusicLink(String link) {
        return false;
    }

    private static boolean isValidYouTubeMusicLink(String link) {
        return false;
    }

    private static boolean isValidGooglePlayMusicLink(String link) {
        return false;
    }

    private static boolean isValidSoundCloudMusicLink(String link) {
        return false;
    }

    private static boolean isValidShazamMusicLink(String link) {
        return false;
    }
}

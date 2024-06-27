package com.app.services.implementations.validators;

import com.app.services.interfaces.validators.Validator;
import com.app.utils.TypeOfLinkUtility;
import org.springframework.stereotype.Service;

@Service
public final class LinkValidator implements Validator {

    public LinkValidator() {}

    @Override
    public boolean isValid(String link) {
        String[] splitLink = link.replace("//", "/").split("/");

        return switch(TypeOfLinkUtility.getTypeOfLink(link)) {
            case SPOTIFY_MUSIC_LINK -> isValidSpotifyMusicLink(splitLink);
            case YANDEX_MUSIC_LINK -> isValidYandexMusicLink(splitLink);
            case APPLE_MUSIC_LINK -> isValidAppleMusicLink(splitLink);
            case YOUTUBE_MUSIC_LINK -> isValidYouTubeMusicLink(splitLink);
            case SOUNDCLOUD_MUSIC_LINK -> isValidSoundCloudMusicLink(splitLink);
            case SHAZAM_MUSIC_LINK -> isValidShazamMusicLink(splitLink);
            case INVALID_LINK -> false;
        };
    }

    // https://open.spotify.com/track/3uCth4TIWyeQDnj3YbAVQB
    private static boolean isValidSpotifyMusicLink(String[] splitLink) {
        if (splitLink.length < 3) return false;
        return splitLink[0].equals("https:") &&
                splitLink[1].equals("open.spotify.com") &&
                splitLink[2].equals("track");
    }

    // https://music.yandex.ru/album/4712278/track/37232256
    private static boolean isValidYandexMusicLink(String[] splitLink) {
        if (splitLink.length < 6) return false;
        return splitLink[0].equals("https:") &&
                splitLink[1].equals("music.yandex.ru") &&
                splitLink[2].equals("album") &&
                splitLink[4].equals("track");
    }

    // https://music.apple.com/ru/album/патрон/1560672735?i=1560672743
    private static boolean isValidAppleMusicLink(String[] splitLink) {
        if (splitLink.length < 6) return false;
        return splitLink[0].equals("https:") &&
                splitLink[1].equals("music.apple.com") &&
                splitLink[3].equals("album");
    }

    // https://music.youtube.com/playlist?list=OLAK5uy_k_3fod9H3jP2IfhHPeqpmLbqPj5FvTiYQ
    private static boolean isValidYouTubeMusicLink(String[] splitLink) {
        if (splitLink.length < 3) return false;
        return splitLink[0].equals("https:") &&
                splitLink[1].equals("music.youtube.com");
    }

    // https://soundcloud.com/miyagi_black/minor
    private static boolean isValidSoundCloudMusicLink(String[] splitLink) {
        if (splitLink.length < 4) return false;
        return splitLink[0].equals("https:") &&
                splitLink[1].equals("soundcloud.com");
    }

    // https://www.shazam.com/ru-ru/song/1560672743/патрон
    private static boolean isValidShazamMusicLink(String[] splitLink) {
        if (splitLink.length < 6) return false;
        return splitLink[0].equals("https:") &&
                splitLink[1].equals("www.shazam.com") &&
                splitLink[3].equals("song");
    }
}

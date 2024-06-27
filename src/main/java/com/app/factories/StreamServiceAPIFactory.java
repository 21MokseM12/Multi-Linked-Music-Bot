package com.app.factories;

import com.app.enums.StreamServiceType;
import com.app.exceptions.InvalidStreamServiceAPIException;
import com.app.services.implementations.api.*;
import com.app.services.interfaces.api.MusicStreamServiceAPI;

public class StreamServiceAPIFactory {
    public static MusicStreamServiceAPI get(StreamServiceType linkType) throws InvalidStreamServiceAPIException{
        return switch (linkType) {
            case SPOTIFY_MUSIC_LINK -> new SpotifyMusicStreamServiceAPI();
//            case YANDEX_MUSIC_LINK -> new YandexMusicStreamServiceAPI();
//            case APPLE_MUSIC_LINK -> new AppleMusicStreamServiceAPI();
//            case YOUTUBE_MUSIC_LINK -> new YouTubeMusicStreamServiceAPI();
//            case SOUNDCLOUD_MUSIC_LINK -> new SoundCloudMusicStreamServiceAPI();
//            case SHAZAM_MUSIC_LINK -> new ShazamMusicStreamServiceAPI();
            case YANDEX_MUSIC_LINK -> throw new InvalidStreamServiceAPIException("Stream music service API not found");
            case APPLE_MUSIC_LINK -> throw new InvalidStreamServiceAPIException("Stream music service API not found");
            case YOUTUBE_MUSIC_LINK -> throw new InvalidStreamServiceAPIException("Stream music service API not found");
            case SOUNDCLOUD_MUSIC_LINK -> throw new InvalidStreamServiceAPIException("Stream music service API not found");
            case SHAZAM_MUSIC_LINK -> throw new InvalidStreamServiceAPIException("Stream music service API not found");
            case INVALID_LINK -> throw new InvalidStreamServiceAPIException("Stream music service API not found");
        };
    }
}
